/* eslint-disable react/prop-types */
import './feedList.scss';
import { useApi } from '../../../hooks/api';
import { useGlobalFeed, useGlobalUserInfo } from '../../../context/index';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import ThumbUpAltIcon from '@material-ui/icons/ThumbUpAlt';
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import { CommentaryPost } from '../index';
import Swal from 'sweetalert2';

export function FeedList({ feedContent, likePost, unlikePost }) {
	const api = useApi();
	const [commentaryPost, setcommentaryPost] = useState();
	const [newCommentary, setNewCommentary] = useState();
	const [userInfo] = useGlobalUserInfo();
	const [likes, setLikes] = useState();
	const [feed, setFeed] = useGlobalFeed(false);

	useEffect(() => {
		async function listarComentario() {
			const response = await api.listarComentario(feedContent.id);
			if (response.status === 200) {
				setcommentaryPost(response.data);
			}
		}

		async function listarCurtida() {
			const response = await api.listarCurtida(feedContent.id);
			if (response.status === 200) {
				setLikes(response.data);
			}
		}

		listarComentario();
		listarCurtida();
	}, [api, feed]);

	function handleLike() {
		likes.content.map((likes) => {
			if (
				userInfo.email === likes.musico.email &&
				feedContent.id === likes.post.id
			) {
				unlikePost(likes.id, likes.post.id);
				return;
			}
		});

		likePost(feedContent.id);
	}

	async function deleteCommentary(postId, commentaryId) {
		const response = await api.deleteCommentary(postId, commentaryId);
		if (response.status === 200) {
			setFeed(!feed);
		}
	}

	function handleCommentary(e) {
		setNewCommentary(e.target.value);
	}

	async function sendCommentary() {
		const response = await api.newCommentary(feedContent.id, newCommentary);
		if (response.status === 201) {
			setNewCommentary('');
			setFeed(!feed);
		}
	}

	function hidePost() {
		Swal.fire({
			icon: 'warning',
			title: 'Tem certeza?',
			text: 'Não será possível recuperar a sua publicação.',
			showDenyButton: true,
			confirmButtonText: `Deletar Publicação`,
			denyButtonText: `Cancelar`,
			denyButtonColor: '#d33',
			confirmButtonColor: '#1A71D9',
		}).then((result) => {
			if (result.isConfirmed) {
				hidePostApi();
			} else if (result.isDenied) {
				Swal.fire({
					title: 'Alteração cancelada!',
					text: 'A sua publicação não foi deletada.',
					confirmButtonText: `Ok`,
					confirmButtonColor: '#1A71D9',
					icon: 'error',
				});
			}
		});
	}

	async function hidePostApi() {
		const response = await api.hideUserPost(feedContent.id);
		if (response.status === 200) {
			setFeed(!feed);
		}
	}

	return (
		<div className="feedList">
			<div className="feedList__info">
				<Link className="link" to={`/profile/${feedContent.musico.email}`}>
					<div className="feedList__image">
						{feedContent.musico.imagem ? (
							<img
								className="profile-image"
								src={feedContent.musico.imagem.url}
								alt="Foto de perfil"
							/>
						) : (
							<span className="hiddenFileInput">
								<input name="theFile" disabled />
							</span>
						)}
					</div>
				</Link>
				<div className="feedList__wrapper--feed">
					<div className="feedList__user">
						<div>
							<b>{feedContent.musico.nome}</b>
						</div>
						<div>
							<b>
								{feedContent.privacidade[0].toUpperCase() +
									feedContent.privacidade.slice(1).toLowerCase()}
							</b>
						</div>
					</div>
					<div className="feedList__instrument">
						<b>
							{feedContent.instrumento[0].toUpperCase() +
								feedContent.instrumento.slice(1).toLowerCase()}
						</b>
						{userInfo.email === feedContent.musico.email ? (
							<HighlightOffIcon
								className="highlightOffIcon"
								onClick={hidePost}
							/>
						) : null}
					</div>
				</div>
			</div>
			<div className="feedList__content">
				<label className="feedList__description"> {feedContent.titulo} </label>
			</div>
			{feedContent.imagem ? (
				<div className="feedList__container-image">
					<img
						className="feedList__post-image"
						src={feedContent.imagem ? feedContent.imagem.url : null}
					/>
				</div>
			) : null}
			<div className="feedList__content">
				<div className="feedList__interation">
					<ThumbUpAltIcon className="thumbUpAltIcon" onClick={handleLike} />
				</div>
				<label className="feedList__likes">
					{likes ? likes.content.length : '0'} Curtidas
				</label>
			</div>
			{commentaryPost
				? commentaryPost.content.map((comentary) => (
						<CommentaryPost
							key="commentaryPost"
							commentaryContent={comentary}
							deleteCommentary={deleteCommentary}
						/>
				  ))
				: null}
			<div className="feedList__input">
				<input
					className="feedList__input-commentary"
					type="text"
					onChange={handleCommentary}
					value={newCommentary}
				/>
				<input
					type="button"
					className="feedList__input-confirm"
					value="Comentar"
					onClick={sendCommentary}
				/>
			</div>
		</div>
	);
}
