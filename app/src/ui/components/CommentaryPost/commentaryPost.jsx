/* eslint-disable react/prop-types */
import './commentaryPost.scss';
import { useGlobalUserInfo } from '../../../context/index';
import React from 'react';
import { Link } from 'react-router-dom';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import Swal from 'sweetalert2';
import { toCapitalize } from '../../functions';

export function CommentaryPost({ commentaryContent, deleteCommentary }) {
	const [userInfo] = useGlobalUserInfo();
	function handleComentary() {
		Swal.fire({
			icon: 'warning',
			title: 'Tem certeza?',
			text: 'Não será possível recuperar o comentário.',
			showDenyButton: true,
			confirmButtonText: `Deletar Comentário`,
			denyButtonText: `Cancelar`,
			denyButtonColor: '#d33',
			confirmButtonColor: '#1A71D9',
		}).then((result) => {
			if (result.isConfirmed) {
				deleteCommentary(commentaryContent.post.id, commentaryContent.id);
			} else if (result.isDenied) {
				Swal.fire({
					title: 'Exclusão cancelada!',
					text: 'O seu comentário não foi excluído.',
					confirmButtonText: `Ok`,
					confirmButtonColor: '#1A71D9',
					icon: 'error',
				});
			}
		});
	}

	return (
		<div className="commentaryPost">
			<div className="commentaryPost__info">
				<Link
					className="link"
					to={`/profile/${commentaryContent.musico.email}`}
				>
					<div className="commentaryPost__image commentaryPost__image--comentary">
						{commentaryContent.musico.imagem ? (
							<img
								className="profile-image"
								src={commentaryContent.musico.imagem.url}
								alt="Foto de perfil"
							/>
						) : (
							<span className="hiddenFileInput">
								<input name="theFile" disabled />
							</span>
						)}
					</div>
				</Link>
				<div className="comentary">
					<div className="commentaryPost__wrapper">
						<div>
							<b>
								{toCapitalize(
									commentaryContent.musico.nome[0],
									commentaryContent.musico.nome
								)}
							</b>
						</div>
						<div>
							<b>
								{toCapitalize(
									commentaryContent.musico.instrumento[0],
									commentaryContent.musico.instrumento
								)}
							</b>
						</div>
					</div>
					<div className=" commentaryPost__content">
						<label className="commentaryPost__description commentaryPost__description--comentary">
							{commentaryContent.comentario}
						</label>
					</div>
				</div>
				{userInfo.email === commentaryContent.musico.email ? (
					<DeleteForeverIcon
						className="deleteCommentary"
						onClick={handleComentary}
					/>
				) : null}
			</div>
		</div>
	);
}
