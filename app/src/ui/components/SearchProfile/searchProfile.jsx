/* eslint-disable react/prop-types */
import './searchProfile.scss';
import add from '../../../images/add.png';
import { Link } from 'react-router-dom';
import { useGlobalUserInfo } from '../../../context';

export function SearchProfile({ userProfile, addFriend }) {
	const [userInfo] = useGlobalUserInfo();

	function handleAdd() {
		addFriend(userProfile.email);
	}

	return (
		<div className="searchProfile__container">
			<div className="searchProfile__info">
				<Link to={`/profile/${userProfile.email}`}>
					<div className="searchProfile__image">
						{userProfile.imagem ? (
							<img
								className="profile-image"
								src={userProfile.imagem.url}
								alt="Foto de perfil"
							/>
						) : (
							<span className="hiddenFileInput">
								<input name="theFile" disabled />
							</span>
						)}
					</div>
				</Link>
				<div className="searchProfile__about">
					<div className="searchProfile__wrapper">
						<div className="searchProfile__user">
							<div>
								{userProfile.nome} ({userProfile.apelido})
							</div>
						</div>
						<div className="searchProfile__instrument">
							{userProfile.instrumento}
						</div>
					</div>
					{userInfo.email !== userProfile.email ? (
						<div className="searchProfile__add" onClick={handleAdd}>
							<img className="add-image" src={add} alt="Adicionar como amigo" />
						</div>
					) : null}
				</div>
			</div>
		</div>
	);
}
