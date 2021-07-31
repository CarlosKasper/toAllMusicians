import { useState, useEffect } from 'react';
import { useGlobalUserSearch } from '../../../context';
import { useApi } from '../../../hooks/api';
import { Header, SearchProfile } from '../../components';
import Swal from 'sweetalert2';

export function SearchScreen() {
	const [userSearch] = useGlobalUserSearch();
	const api = useApi();
	const [users, setUsers] = useState();

	useEffect(() => {
		async function searchUser() {
			const response = await api.searchUser(userSearch);
			if (response.status === 200 && response.data.length > 0) {
				setUsers(response.data);
			} else if (response.data.length == 0) {
				Swal.fire({
					icon: 'info',
					title: 'Nenhuma resultado encontrado!',
					confirmButtonText: `Voltar para o feed`,
					confirmButtonColor: '#1A71D9',
				})
			}
		}

		searchUser();
	}, [userSearch]);

	async function handleAddFriend(nome, email) {
		const response = await api.addFriend(email);
		if (response.status === 400) {
			Swal.fire({
				icon: 'warning',
				title: `Não foi possível adicionar ${nome}!`,
				text: `Você já possui um relacionamento(Aceito ou Pendente).`,
				confirmButtonText: `Voltar para o feed`,
				confirmButtonColor: '#1A71D9',
			});
		} else if(response.status === 201) {
      Swal.fire({
				icon: 'info',
				title: `Pedido enviado!`,
        text: `${nome} recebeu seu convite de amizade!`,
				confirmButtonText: `OK`,
				confirmButtonColor: '#1A71D9',
			})
    }
	}

	return (
		<>
			<Header />
			<div className="searchProfile">
				{users ? (
					<label className="searchProfile__result">
						Resultado da pesquisa:
					</label>
				) : (
					<label className="searchProfile__result">
						Nenhum resultado encontrado para a sua pesquisa.
					</label>
				)}
				{users
					? users.map((users) => (
							<SearchProfile
								key="profile"
								userProfile={users}
								addFriend={handleAddFriend}
							/>
					  ))
					: null}
			</div>
		</>
	);
}
