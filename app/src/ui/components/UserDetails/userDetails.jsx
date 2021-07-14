/* eslint-disable react/prop-types */
import './userDetails.scss';
import React from 'react';
import { useApi } from '../../../hooks/api';

export function UserDetails({ userData, postLength, userFriends }) {
	const api = useApi();
	const feed = document.getElementById('feed');
	const friends = document.getElementById('friends');

	async function addPhoto(event) {
		const file = event.target.files[0];
		const image = new FormData();
		image.append('image', file);
		await api.uploadImagePerfil(image);
	}

	function showFriends() {
		feed.style.display = 'none';
		friends.style.display = 'block';
	}

	function showPosts() {
		feed.style.display = 'block';
		friends.style.display = 'none';
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
						<span className="hiddenFileInput">
							<input type="file" name="theFile" onChange={addPhoto} />
						</span>
					)}
				</div>
				<div className="container-prof__info">
					<div className="container-prof__name">
						<label className="profile-name">
							{userData.nome}({userData.apelido})
						</label>
					</div>
					<div>
						<label className="profile-instrument">
							{userData.instrumento[0].toUpperCase() +
								userData.instrumento.slice(1).toLowerCase()}
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
			</div>
		</div>
	);
}
