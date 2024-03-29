/* eslint-disable react/prop-types */
import './feedList.scss';
import { useApi } from '../../../hooks/api';
import { useGlobalFeed, useGlobalUserInfo } from '../../../context/index';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import ThumbUpAltIcon from '@material-ui/icons/ThumbUpAlt';
import ChatBubbleOutlineIcon from '@material-ui/icons/ChatBubbleOutline';
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import { CommentaryPost } from '../index';
import Swal from 'sweetalert2';
import { isEnterPress, toCapitalize } from '../../common';
import profile from '../../../images/profile.png';
import toallmusicians from '../../../images/toallmusicians.png';

export function FeedList({ post, like, commentary, likePost, unlikePost }) {
	const api = useApi();
	const [newCommentary, setNewCommentary] = useState();
	const [userInfo] = useGlobalUserInfo();
	const [feed, setFeed] = useGlobalFeed(false);

	function handleLike() {
		like.map((likes) => {
			if (userInfo.email === likes.musico.email) {
				unlikePost(likes.id, post.id);
				return;
			}
		});

		likePost(post.id);
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
		if (newCommentary) {
			const response = await api.newCommentary(post.id, newCommentary);
			if (response.status === 201) {
				setNewCommentary('');
				setFeed(!feed);
			}
		} else {
			Swal.fire({
				icon: 'warning',
				title: 'Ocorreu um erro',
				text: 'Você deve digitar um comentário para compartilhar.',
				showDenyButton: false,
				confirmButtonText: `Ok`,
				confirmButtonColor: '#1A71D9',
			});
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
		const response = await api.hideUserPost(post.id);
		if (response.status === 200) {
			setFeed(!feed);
		}
	}

	function submitListening(event) {
		isEnterPress(event.keyCode) ? sendCommentary() : null;
	}

	return (
		<div className="feedList">
			<div className="feedList__info">
				<Link className="link" to={`/profile/${post.musico.email}`}>
					<div className="feedList__image">
						{post.musico.imagem ? (
							<img
								className="profile-image"
								src={post.musico.imagem.url}
								alt="Foto de perfil"
							/>
						) : (
							<img className="profile--without-pic" src={profile} />
						)}
					</div>
				</Link>
				<div className="feedList__wrapper--feed">
					<div className="feedList__user">
						<div>
							<b>
								{post.musico.nome}{' '}
								{post.musico.apelido ? '(' + post.musico.apelido + ')' : ''}
							</b>
						</div>
						<div>
							<b>{toCapitalize(post.privacidade)}</b>
						</div>
					</div>
					<div className="feedList__instrument">
						<b>{toCapitalize(post.instrumento)}</b>
						{userInfo && userInfo.email === post.musico.email ? (
							<HighlightOffIcon
								className="highlightOffIcon"
								onClick={hidePost}
							/>
						) : null}
					</div>
				</div>
			</div>
			<div className="feedList__content">
				<label className="feedList__description"> {post.titulo} </label>
			</div>
			{post.imagem ? (
				<div className="feedList__container-image">
					<img
						className="feedList__post-image"
						src={post.imagem  ? post.imagem.url : toallmusicians}
					/>
				</div>
			) : null}
			<div className="feedList__content">
				<label className="feedList__description feedList__description--likes">
					{like ? like.length : '0'} Curtidas
				</label>
			</div>
			<div className="feedList__content feedList__options">
				<div className="feedList__likeable">
					<div className="feedList__interation">
						<ThumbUpAltIcon
							className="like-comment-icon"
							onClick={handleLike}
						/>
					</div>
					<label className="feedList__like-comment">Curtir</label>
				</div>
				<div className="feedList__interation" onClick={sendCommentary}>
					<ChatBubbleOutlineIcon className="like-comment-icon" />
					<label className="feedList__like-comment">Comentar</label>
				</div>
			</div>
			{commentary
				? commentary.map((comentary) => (
						<CommentaryPost
							key="commentaryPost"
							commentaryContent={comentary}
							deleteCommentary={deleteCommentary}
						/>
				  ))
				: null}

			<div className="feedList__input">
        {userInfo && userInfo.imagem  ? (
            <img
              className="profile-image"
              src={userInfo.imagem.url}
              alt="Foto de perfil"
            />
          ) : (
            <img className="profile--without-pic-commentary" src={profile} />
          )}
				<input
					className="feedList__input-commentary"
					type="text"
					onChange={handleCommentary}
					value={newCommentary}
					placeholder="Escreva um comentário..."
					onKeyDown={(e) => submitListening(e)}
				/>
			</div>
		</div>
	);
}
