/* eslint-disable react/prop-types */
import './friendsProfile.scss';
import deleteFriend from '../../../images/deleteFriend.png';
import { Link } from 'react-router-dom';
import { useGlobalUserInfo } from '../../../context';
import Swal from 'sweetalert2';

export function FriendsProfile({ userFriends, deletedFriend, profileEmail }) {
	const [userInfo] = useGlobalUserInfo();

	function handleRemoveFriend() {
		Swal.fire({
			icon: 'warning',
			title: 'Tem certeza?',
			text: 'Você está excluindo esta amizade',
			showDenyButton: true,
			confirmButtonText: `Excluir amizade`,
			denyButtonText: `Cancelar`,
			denyButtonColor: '#d33',
			confirmButtonColor: '#1A71D9',
		}).then((result) => {
			if (result.isConfirmed) {
				deletedFriend(userFriends.email);
			} else if (result.isDenied) {
				Swal.fire({
					title: 'Exclusão cancelada!',
					text: 'A sua amizade não foi excluida.',
					confirmButtonText: `Ok`,
					confirmButtonColor: '#1A71D9',
					icon: 'error',
				});
			}
		});
	}

	return (
		<div className="friendsProfile">
			<div className="friendsProfile__info">
				<Link to={`/profile/${userFriends.email}`}>
					<div className="friendsProfile__image">
						{userFriends.imagem ? (
							<img
								className="profile-image"
								src={userFriends.imagem.url}
								alt="Foto de perfil"
							/>
						) : (
							<span className="hiddenFileInput">
								<input name="theFile" disabled />
							</span>
						)}
					</div>
				</Link>
				<div className="friendsProfile__about">
					<div className="friendsProfile__wrapper">
						<div className="friendsProfile__user">
							<div>
								<b>
									{userFriends.nome} ({userFriends.apelido})
								</b>
							</div>
						</div>
						<div className="friendsProfile__instrument">
							<b>
								{userFriends.instrumento[0].toUpperCase() +
									userFriends.instrumento.slice(1).toLowerCase()}
							</b>
						</div>
					</div>
					{profileEmail === userInfo.email ? (
						<div
							className="friendsProfile__deleteFriend"
							onClick={handleRemoveFriend}
						>
							<img
								className="deleteFriend-image"
								src={deleteFriend}
								alt="Remover amigo"
							/>
						</div>
					) : null}
				</div>
			</div>
		</div>
	);
}
