import './createPost.scss';
import { useApi } from '../../../hooks/api';
import React, { useState } from 'react';
import Select from 'react-select';
import { useGlobalFeed } from '../../../context/index';
import PhotoCamera from '@material-ui/icons/PhotoCamera';
import IconButton from '@material-ui/core/IconButton';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import Swal from 'sweetalert2';

export function CreatePost() {
	const api = useApi();
	const [title, setTitle] = useState('');
	const [instrument, setInstrument] = useState('');
	const [privacity, setPrivacity] = useState('');
	const [feed, setFeed] = useGlobalFeed(false);
	const [imagePreview, setImagePreview] = useState(null);
	const [imageResult, setImageResult] = useState(null);

	async function publicarPost() {
		let response = null;
		if (privacity && instrument && (title || imageResult)) {
			response = await api.criarPost(title, privacity, instrument);
			if (response.status === 201) {
				if (imageResult) {
					addPhoto(event, response.data.id);
				} else {
					updateFeedAndRemoveInfos();
				}
			} else if (response.status === 400) {
				Swal.fire({
					icon: 'warning',
					title: 'Erro',
					text: 'Occoreu algo de errado na publicação',
					showDenyButton: false,
					confirmButtonText: `Voltar`,
					confirmButtonColor: '#1A71D9',
				});
			}
		} else {
			Swal.fire({
				icon: 'warning',
				title: 'Erro',
				text: 'Faltou informações para fazer a publicação!',
				showDenyButton: false,
				confirmButtonText: `Voltar`,
				confirmButtonColor: '#1A71D9',
			});
		}
	}

	function updateFeedAndRemoveInfos() {
		setTitle('');
		setImageResult(null);
		setImagePreview(null);
		setFeed(!feed);
	}

	function handlePost(event) {
		setTitle(event.target.value);
	}

	function onChangeInstrument(event) {
		setInstrument(event.value);
	}

	function onChangePrivacity(event) {
		setPrivacity(event.value);
	}

	const optionsInsrument = [
		{ value: 'GUITARRA', label: 'Guitarra' },
		{ value: 'VIOLAO', label: 'Violão' },
		{ value: 'BATERIA', label: 'Bateria' },
		{ value: 'SAXOFONE', label: 'Saxofone' },
		{ value: 'TROMBONE', label: 'Trombone' },
		{ value: 'TECLADO', label: 'Teclado' },
		{ value: 'GAITADEFOLE', label: 'Gaita de Fole' },
	];

	const optionsPrivacity = [
		{ value: 'PUBLICO', label: 'Público' },
		{ value: 'PRIVADO', label: 'Privado' },
	];

	async function addPhoto(event, postId) {
		if (imagePreview && imageResult) {
			await api.uploadPostImage(postId, imageResult);
			updateFeedAndRemoveInfos();
		} else if (event) {
			let file = event.target.files[0];
			let image = new FormData();
			image.append('image', file);
			setImageResult(image);
			setImagePreview(URL.createObjectURL(file));
			event.target.value = null;
		}
	}

	function removeImage() {
		setImageResult(null);
		setImagePreview(null);
	}

	return (
		<div className="createPost">
			<div className="createPost__input">
				<textarea
					type="text"
					className="createPost__input--text"
					placeholder="Digite algo sobre música..."
					onChange={handlePost}
					value={title}
				></textarea>
			</div>
			<input
				accept="image/*"
				id="icon-button-file"
				type="file"
				style={{ display: 'none' }}
				onChange={addPhoto}
			/>
			<label htmlFor="icon-button-file" className="image-select">
				<IconButton
					color="primary"
					aria-label="upload picture"
					component="span"
				>
					<PhotoCamera />
				</IconButton>
				Adicionar Imagem
			</label>
			{imagePreview ? (
				<div className="createPost__image">
					<img
						className="image-preview"
						src={imagePreview}
						alt="preview da imagem"
					/>
					<DeleteForeverIcon className="delete-preview" onClick={removeImage} />
				</div>
			) : null}
			<div className="createPost__selects">
				<Select
					className="post-select"
					onChange={onChangeInstrument}
					options={optionsInsrument}
					placeholder="Seu instrumento"
				/>
				<Select
					className="post-select"
					onChange={onChangePrivacity}
					options={optionsPrivacity}
					placeholder="Privacidade"
				/>
			</div>
			<div className="createPost__submit">
				<input
					type="button"
					className="createPost__submit--button"
					onClick={publicarPost}
					value="Compartilhar"
				/>
			</div>
		</div>
	);
}
