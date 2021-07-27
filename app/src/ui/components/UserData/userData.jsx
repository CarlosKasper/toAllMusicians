/* eslint-disable react/prop-types */
import './userData.scss';
import Swal from 'sweetalert2';
import { useState } from 'react';
import Select from 'react-select';
import { isEnterPress, toCapitalize } from '../../common';

export function UserData({ userData, updateUserData }) {
	const [newEmail, setNewEmail] = useState(userData.email);
	const [newName, setNewName] = useState(userData.nome);
	const [newNickname, setNewNickname] = useState(userData.apelido);
	const [newInstrument, setNewInstrument] = useState(userData.instrumento);

	function handleUpdateData() {
		Swal.fire({
			icon: 'warning',
			title: 'Tem certeza?',
			text: 'Você irá atualizar os dados do seu perfil',
			showDenyButton: true,
			confirmButtonText: `Alterar dados`,
			denyButtonText: `Cancelar`,
			denyButtonColor: '#d33',
			confirmButtonColor: '#1A71D9',
		}).then((result) => {
			if (
				result.isConfirmed &&
				(newName !== userData.nome ||
					newEmail !== userData.email ||
					newNickname !== userData.apelido ||
					newInstrument !== userData.instrumento)
			) {
				updateUserData(newName, newEmail, newNickname, newInstrument);
			} else {
				Swal.fire({
					title: 'Alteração cancelada!',
					text: 'As suas informações não foram alteradas.',
					confirmButtonText: `Ok`,
					confirmButtonColor: '#1A71D9',
					icon: 'error',
				});
			}
		});
	}

	function handleNewEmail(e) {
		setNewEmail(e.target.value);
	}

	function handleNewName(e) {
		setNewName(e.target.value);
	}

	function handleNewNickname(e) {
		setNewNickname(e.target.value);
	}

	function handleNewInstrument(e) {
		setNewInstrument(e.value);
	}

	const optionsInsrument = [
		{ value: 'GUITARRA', label: 'Guitarra' },
		{ value: 'VIOLAO', label: 'Violão' },
		{ value: 'BATERIA', label: 'Bateria' },
		{ value: 'SAXOFONE', label: 'Saxofone' },
		{ value: 'TROMBONE', label: 'Trombone' },
		{ value: 'TECLADO', label: 'Teclado' },
		{ value: 'GAITADEFOLE', label: 'Gaita de Fole' },
	];

	function submitListening(event) {
		setTimeout(() => {
      isEnterPress(event.keyCode) ? handleUpdateData() : null
		}, 100);
	}

	return (
		<div className="friendsProfile">
			<label className="userData-updateable">
				Seus dados podem ser alterados por aqui:
			</label>
			<div>
				<input
					className="userData-input"
					type="email"
					value={newEmail}
					onChange={handleNewEmail}
					onKeyDown={(e) => submitListening(e)}
				/>
				<input
					className="userData-input"
					type="text"
					value={newName}
					onChange={handleNewName}
					onKeyDown={(e) => submitListening(e)}
				/>
				<input
					className="userData-input"
					type="text"
					value={newNickname}
					onChange={handleNewNickname}
					onKeyDown={(e) => submitListening(e)}
				/>
				<div>
					<Select
						className="userData-select"
						onChange={handleNewInstrument}
						options={optionsInsrument}
						placeholder={toCapitalize(
							userData.instrumento[0],
							userData.instrumento
						)}
					/>
				</div>
				<input
					type="button"
					className="userData-button"
					onClick={handleUpdateData}
					value="Atualizar Perfil"
				/>
			</div>
		</div>
	);
}
