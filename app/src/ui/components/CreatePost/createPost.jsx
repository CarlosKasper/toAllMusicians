import './createPost.scss';
import { useApi } from '../../../hooks/api';
import React, { useState } from 'react';
import Select from 'react-select';
import { useGlobalFeed } from '../../../context/index';
import { useEffect } from 'react';
import PhotoCamera from '@material-ui/icons/PhotoCamera';
import IconButton from '@material-ui/core/IconButton';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

export function CreatePost() {
	const api = useApi();
	const [title, setTitle] = useState('');
	const [instrument, setInstrument] = useState('');
	const [privacity, setPrivacity] = useState('');
	const [feed, setFeed] = useGlobalFeed(false);
	const [allPosts, setAllPosts] = useState(0);
	const [imagePreview, setImagePreview] = useState(null);
	const [imageResult, setImageResult] = useState(null);
	const [published, setPublished] = useState(false);

	useEffect(() => {
		async function listAllPosts() {
			const response = await api.listAllPosts();
			if (response.status === 200) {
				response.data.content.map((biggestId) => {
					if (biggestId.id > allPosts) {
						setAllPosts(biggestId.id);
					}
				});
			}
		}

		listAllPosts();
	}, [published]);

	async function publicarPost() {
		const response = await api.criarPost(title, privacity, instrument);
		if (response.status === 201) {
			setPublished(!published);
			addPhoto();
		} else if (response.status === 400) {
			alert('tem algo de errado amigao');
		}
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
	];

	const optionsPrivacity = [
		{ value: 'PUBLICO', label: 'Público' },
		{ value: 'PRIVADO', label: 'Privado' },
	];

	async function addPhoto(event) {
		if (imagePreview && imageResult) {
			await api.uploadPostImage(allPosts, imageResult);
			setTitle('');
			setImageResult(null);
			setImagePreview(null);
			setFeed(!feed);
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
