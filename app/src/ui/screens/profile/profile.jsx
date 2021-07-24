import {
	FeedList,
	Header,
	UserDetails,
	FriendsProfile,
	UserData,
} from '../../components';
import { useApi } from '../../../hooks/api';
import { useEffect, useState } from 'react';
import { useGlobalFeed, useGlobalUserInfo } from '../../../context/index';
import { useParams } from 'react-router';
import Swal from 'sweetalert2';

export function ProfileScreen() {
	const api = useApi();
	const [userData, setUserData] = useState();
	const [postsUser, setPostsUser] = useState();
	const [userFriends, setUserFriends] = useState();
	const [feed, setFeed] = useGlobalFeed(false);
	const [userInfo] = useGlobalUserInfo(false);
	let { email } = useParams();

	useEffect(() => {
		async function exibirDadosDoPefilDoUsuario() {
			const response = await api.exibirDadosDoPefilDoUsuario(email);
			if (response.status === 200) {
				setUserData(response.data);
			} else if (response.status === 400) {
				alert('bugou pa caralho');
			}
		}

		async function listFriends() {
			const response = await api.listFriends(email);
			if (response.status === 200) {
				setUserFriends(response.data);
			} else if (response.status === 400) {
				Swal.fire({
					icon: 'warning',
					title: 'Falha ao listar as amizades',
					showDenyButton: false,
					confirmButtonText: `Tentar novamente`,
					confirmButtonColor: '#1A71D9',
				});
			}
		}

		async function listarPostsUsuario() {
			if (userInfo) {
				const response = await api.listarPostsUsuario(email, userInfo.email);
				if (response.status === 200) {
					setPostsUser(response.data);
				} else if (response.status === 400) {
					Swal.fire({
						icon: 'warning',
						title: 'Falha ao listar publicações',
						showDenyButton: false,
						confirmButtonText: `Tentar novamente`,
						confirmButtonColor: '#1A71D9',
					});
				}
			}
		}

		listarPostsUsuario();
		listFriends();
		exibirDadosDoPefilDoUsuario();
	}, [feed, email]);

	async function likePost(idPost) {
		const response = await api.likePost(idPost);
		if (response.status === 201) {
			setFeed(!feed);
		}
	}

	async function unlikePost(idLike, idPost) {
		const response = await api.unlikePost(idLike, idPost);
		if (response.status === 200) {
			setFeed(!feed);
		}
	}

	async function deletedFriend(userEmail) {
		const response = await api.deletedFriend(userEmail);
		if (response.status === 400) {
			Swal.fire({
				icon: 'warning',
				title: 'Falha ao tentar remover amizade',
				showDenyButton: false,
				confirmButtonText: `Tentar novamente`,
				confirmButtonColor: '#1A71D9',
			});
		}

		setFeed(!feed);
	}

	async function updateUserData() {
		const response = await api.updateUserData();
		if (response.status === 200) {
			Swal.fire({
				icon: 'icon',
				title: 'As suas informações foram atualizadas com sucesso!',
				showDenyButton: false,
				confirmButtonText: `Concluir`,
				confirmButtonColor: '#1A71D9',
			});
		} else {
			Swal.fire({
				icon: 'warning',
				title: 'Falha ao tentar atualizar as suas informações.',
				showDenyButton: false,
				confirmButtonText: `Tentar novamente`,
				confirmButtonColor: '#1A71D9',
			});
		}
	}

	return (
		<>
			<Header />
			{userData && userFriends && postsUser ? (
				<UserDetails
					userData={userData}
					postLength={postsUser.length}
					userFriends={userFriends}
				/>
			) : null}
			<div id="feed">
				{postsUser ? (
					postsUser.map((postsUser) => (
						<FeedList
							key="feedList"
							post={postsUser.post}
							like={postsUser.curtidaList}
							commentary={postsUser.comentarioList}
							likePost={likePost}
							unlikePost={unlikePost}
						/>
					))
				) : (
					<label className="friends-message">
						Você ainda não possui publicações adicionados.
					</label>
				)}
			</div>
			<div id="friends">
				{userFriends ? (
					userFriends.map((userFriends, index) => (
						<FriendsProfile
							key={index}
							userFriends={userFriends}
							deletedFriend={deletedFriend}
							profileEmail={email}
						/>
					))
				) : (
					<label className="friends-message">
						Você ainda não possui amigos adicionados.
					</label>
				)}
			</div>
			<div id="profileData" className="profileData">
				{userData ? (
					<UserData userData={userData} updateUserData={updateUserData} />
				) : null}
			</div>
		</>
	);
}
