import { useAxios } from './use-axios.hook';
import { useCallback } from 'react';
import { useGlobalUser } from '../../context/index';

export function useApi() {
	const [user, setUser] = useGlobalUser();
	const token = user;
	const axios = useAxios('http://localhost:8090/', {
		Authorization: 'Bearer ' + token,
	});

	const autenticarLogin = useAxios('http://localhost:8090/', {
		Authorization: 'Basic bWV1LWNsaWVudC1pZDptZXUtc2VjcmV0LWlk',
	});

	const cadastroUsuario = useAxios('http://localhost:8090/');

	async function gerarToken(login, senha) {
		let bodyFormData = new FormData();

		bodyFormData.set('grant_type', 'password');
		bodyFormData.set('scope', 'any');
		bodyFormData.set('username', login);
		bodyFormData.set('password', senha);

		return autenticarLogin({
			method: 'post',
			url: '/oauth/token',
			data: bodyFormData,
			headers: {
				'Content-Type': 'multipart/form-data',
			},
		})
			.then(function (response) {
				setUser(response.data.access_token);
				return response;
			})
			.catch(function (error) {
				return error.response;
			});
	}

	async function registroUsuario(
		nome,
		email,
		senha,
		apelido,
		dataNascimento,
		instrumento
	) {
		try {
			const response = await cadastroUsuario.post('/usuario/cadastro', {
				nome,
				email,
				apelido,
				dataNascimento,
				senha,
				instrumento,
			});
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function criarPost(titulo, privacidade, instrumento) {
		try {
			const response = await axios.post(`/post/publicar`, {
				titulo: titulo,
				privacidade: privacidade,
				instrumento: instrumento,
			});
			return response;
		} catch (error) {
			return error.response.data;
		}
	}

	async function listarPostsAmigos() {
		try {
			const response = await axios.get('/post/listar');
			return response;
		} catch (error) {
			return error.response.data;
		}
	}

	async function hideUserPost(idPost) {
		try {
			const response = await axios.put(`/post/${idPost}`);
			return response;
		} catch (error) {
			return error.response.data;
		}
	}

	async function listarDadosUsuario() {
		try {
			const response = await axios.get('/usuario');
			return response;
		} catch (error) {
			return error.response.data;
		}
	}

	async function exibirDadosDoPefilDoUsuario(email) {
		try {
			const response = await axios.get(`/usuario/buscar/${email}`);
			return response;
		} catch (error) {
			return error.response.data;
		}
	}

	async function listarPostsUsuario(email, emailLogado) {
		try {
			let response = null;
			emailLogado == email
				? (response = await axios.get(`/post/`))
				: (response = await axios.get(`/post/${email}`));
			return response;
		} catch (error) {
			return error.response.data;
		}
	}

	async function likePost(idPost) {
		try {
			const response = await axios.post(`/curtida/${idPost}`);
			return response;
		} catch (error) {
			return error.response.data;
		}
	}

	async function unlikePost(idLike, idPost) {
		try {
			const response = await axios.delete(
				`/curtida/deletar/${idPost}/${idLike}`
			);
			return response;
		} catch (error) {
			return error.response.data;
		}
	}

	async function newCommentary(idPost, newCommentary) {
		try {
			const response = await axios.post(`/comentario/${idPost}`, {
				comentario: newCommentary,
			});
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function listarComentario(idPublicacao) {
		try {
			const response = await axios.get(`/comentario/listar/${idPublicacao}`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function deleteCommentary(idPost, idCommentary) {
		try {
			const response = await axios.delete(
				`/comentario/deletar/${idPost}/${idCommentary}`
			);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function listarCurtida(idPublicacao) {
		try {
			const response = await axios.get(`/curtida/listar/${idPublicacao}`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function searchUser(userName) {
		try {
			const response = await axios.get(`/usuario/buscar/musico/${userName}`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function addFriend(email) {
		try {
			const response = await axios.post(`/amizade/${email}`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function listarSolicitacoes() {
		try {
			const response = await axios.get(`/amizade/solicitacoes`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function acceptAsFriend(relationshipId) {
		try {
			const response = await axios.post(`/amizade/aceitar/${relationshipId}`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function denniedAsFriend(relationshipId) {
		try {
			const response = await axios.post(`/amizade/recusar/${relationshipId}`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function listFriends(email) {
		try {
			const response = await axios.get(`/usuario/relacionamentos/${email}`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function deletedFriend(userEmail) {
		try {
			const response = await axios.delete(`/amizade/remover/${userEmail}`);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function uploadImagePerfil(image) {
		try {
			const response = await axios.post(`/imagem/upload/profile`, image);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	async function uploadPostImage(postId, image) {
		try {
			const response = await axios.post(`/imagem/upload/post/${postId}`, image);
			return response;
		} catch (error) {
			return error.response;
		}
	}

  async function updateUserData(nome, email, apelido, dataNascimento, senha, instrumento) {
		try {
			const response = await axios.post(`/usuario/atualizar`, nome, email, apelido, dataNascimento, senha, instrumento);
			return response;
		} catch (error) {
			return error.response;
		}
	}

	return useCallback(
		{
			gerarToken,
			registroUsuario,
			criarPost,
			listarPostsAmigos,
			listarDadosUsuario,
			listarPostsUsuario,
			likePost,
			unlikePost,
			listarCurtida,
			newCommentary,
			listarComentario,
			deleteCommentary,
			searchUser,
			addFriend,
			exibirDadosDoPefilDoUsuario,
			listarSolicitacoes,
			acceptAsFriend,
			denniedAsFriend,
			listFriends,
			deletedFriend,
			uploadImagePerfil,
			uploadPostImage,
			hideUserPost,
      updateUserData
		},
		[]
	);
}
