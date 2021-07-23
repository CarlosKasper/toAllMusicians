import { Header, FriendSolicitation } from '../../components';
import { useApi } from '../../../hooks/api';
import { useEffect } from 'react';
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import Swal from 'sweetalert2';

export function FriendScreen() {
	const api = useApi();
	const history = useHistory();
	const [solicitations, setSolicitations] = useState();
	const [relationship, setRelationship] = useState();

	useEffect(() => {
		async function listarSolicitacoes() {
			const response = await api.listarSolicitacoes();
			if (response.status === 200 && !!response.data.length) {
				setSolicitations(response.data);
			} else {
				swalNotFoundSolicitations();
			}
		}

		listarSolicitacoes();
	}, [relationship]);

	async function acceptAsFriend(relationshipId) {
		const response = await api.acceptAsFriend(relationshipId);
		if (response.status === 201) {
			setRelationship(!relationship);
		}
	}

	async function denniedAsFriend(relationshipId) {
		const response = await api.denniedAsFriend(relationshipId);
		if (response.status === 201) {
			setRelationship(!relationship);
		}
	}

	function swalNotFoundSolicitations() {
		return Swal.fire({
			icon: 'info',
			title: 'Nenhuma solicitação foi encontrada!',
			confirmButtonText: `Voltar para o feed`,
			confirmButtonColor: '#1A71D9',
		}).then((result) => {
			if (result.isConfirmed) {
				history.push('/home');
			}
		});
	}

	return (
		<>
			<Header />
			<div className="friendSolicitation">
				{solicitations && solicitations.length ? (
					<label className="friendSolicitation__result">
						Solicitações de amizades pendentes:
					</label>
				) : (
					<label className="friendSolicitation__result">
						Você não possui nenhuma solicitação pendente.
					</label>
				)}
				{solicitations && solicitations.length
					? solicitations.map((solicitations, index) => (
							<FriendSolicitation
								key={index}
								userSolicitations={solicitations}
								acceptAsFriend={acceptAsFriend}
								denniedAsFriend={denniedAsFriend}
							/>
					  ))
					: null}
			</div>
		</>
	);
}
