/* eslint-disable react/prop-types */
import './searchProfile.scss';
import add from '../../../images/add.png';
import { Link } from 'react-router-dom';
import { useGlobalUserInfo } from '../../../context';
import profile from '../../../images/profile.png'

export function SearchProfile({ userProfile, addFriend }) {
	const [userInfo] = useGlobalUserInfo();
	function handleAdd() {
		addFriend(userProfile.nome, userProfile.email);
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
							<img className="profile--without-pic" src={profile}/>
						)}
					</div>
				</Link>
				<div className="searchProfile__about">
					<div className="searchProfile__wrapper">
						<div className="searchProfile__user">
							<div>
							  {userProfile.nome} {userProfile.apelido ? '(' + userProfile.apelido + ')' : ''}
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
