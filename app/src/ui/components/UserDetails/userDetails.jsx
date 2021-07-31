/* eslint-disable react/prop-types */
import './userDetails.scss';
import React from 'react';
import { useApi } from '../../../hooks/api';
import { useGlobalFeed, useGlobalUserInfo } from '../../../context';
import { toCapitalize } from '../../common';
import profile from '../../../images/profile.png'
import { useParams } from 'react-router-dom';

export function UserDetails({ userData, postLength, userFriends }) {
	const api = useApi();
	const [feed, setFeed] = useGlobalFeed(false);
	const [userInfo] = useGlobalUserInfo(false);
	const feedContent = document.getElementById('feed');
	const friends = document.getElementById('friends');
	const profileData = document.getElementById('profileData');
	let { email } = useParams();

	async function addPhoto(event) {
		const file = event.target.files[0];
		const image = new FormData();
		image.append('image', file);
		await api.uploadImagePerfil(image);
		setFeed(!feed);
	}

	function showFriends() {
		feedContent.style.display = 'none';
		profileData.style.display = 'none';
		friends.style.display = 'block';
	}

	function showPosts() {
		feedContent.style.display = 'block';
		friends.style.display = 'none';
		profileData.style.display = 'none';
	}

	function showProfileData() {
		feedContent.style.display = 'none';
		friends.style.display = 'none';
		profileData.style.display = 'block';
	}

	return (
		<div className="profile">
			<div className="container-prof">
				<div className="container-prof__image">
					{userData.imagem ? (
						<img
							className="profile-image"
							src={userData.imagem.url}
							alt="foto do usuario"
						/>
					) : (
            userInfo.email === email ?
						<span className="hiddenFileInput">
              <input type="file" name="theFile" onChange={addPhoto} />
            </span>
            :
            <img className="profile--without-pic" src={profile}/>
					)}
				</div>
				<div className="container-prof__info">
					<div className="container-prof__name">
						<label className="profile-name">
							{userData.nome} {userData.apelido ? '(' + userData.apelido + ')' : ''}
						</label>
					</div>
					<div>
						<label className="profile-instrument">
							{toCapitalize(userData.instrumento)}
						</label>
					</div>
				</div>
			</div>
			<div className="user-info">
				<div className="information" onClick={showFriends}>
					Amigos {userFriends.length}
				</div>
				<div className="information" onClick={showPosts}>
					Post {postLength}
				</div>
				<div className="information" onClick={showProfileData}>
					Editar Perfil
				</div>
			</div>
		</div>
	);
}
