/* eslint-disable react/prop-types */
import './friendSolicitation.scss';
import profile from '../../../images/profile.png';
import { Link } from 'react-router-dom';
import Swal from 'sweetalert2';
import { toCapitalize } from '../../common';

export function FriendSolicitation({
	userSolicitations,
	acceptAsFriend,
	denniedAsFriend,
}) {
	function handleAccept() {
		acceptAsFriend(userSolicitations.idFriendship);
	}

	function handleDennied() {
		denniedAsFriend(userSolicitations.idFriendship);
	}

	function solicitationRequest() {
		Swal.fire({
			icon: 'info',
			text: 'Solicitação de amizade',
			showDenyButton: true,
			confirmButtonText: `Aceitar amizade`,
			denyButtonText: `Recusar amizade`,
			denyButtonColor: '#d33',
			confirmButtonColor: '#1A71D9',
		}).then((result) => {
			if (result.isConfirmed) {
				handleAccept();
			} else if (result.isDenied) {
				handleDennied();
			}
		});
	}

	return (
		<div className="friendSolicitation__info">
			<Link to={`/profile/${userSolicitations.musico.email}`}>
				<div className="friendSolicitation__image">
					{userSolicitations.musico.imagem ? (
						<img
							className="profile-image"
							src={userSolicitations.musico.imagem.url}
							alt="Foto de perfil"
						/>
					) : (
						<img className="profile--without-pic" src={profile}/>
					)}
				</div>
			</Link>
			<div className="friendSolicitation__about">
				<div className="friendSolicitation__wrapper">
					<div className="friendSolicitation__user">
						<div>
							<b>{toCapitalize(userSolicitations.musico.nome)}</b>
							<b>{userSolicitations.musico.apelido && '(' + toCapitalize(userSolicitations.musico.apelido) + ')'}</b>
						</div>
					</div>
					<div className="friendSolicitation__instrument">
						<b>{toCapitalize(userSolicitations.musico.instrumento)}</b>
					</div>
				</div>
				<div
					className="friendSolicitation__response"
					onClick={solicitationRequest}
				>
					<img className="add-image" src={profile} alt="Adicionar como amigo" />
				</div>
			</div>
		</div>
	);
}
