/* eslint-disable react/prop-types */
import './userData.scss';
import Swal from 'sweetalert2';
import { useState } from 'react';
import Select from 'react-select';

export function UserData({ userData, updateUserData }) {
  const [newEmail, setNewEmail] = useState();
  const [newName, setNewName] = useState();
  const [newNickname, setNewNickname] = useState();
  const [newInstrument, setNewInstrument] = useState();

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
			if (result.isConfirmed) {
				updateUserData();
			} else if (result.isDenied) {
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
    setNewEmail(e.target.value)
    console.log(newEmail)
  }

  function handleNewName(e) {
    setNewName(e.target.value)
    console.log(newName)
  }

  function handleNewNickname(e) {
    setNewNickname(e.target.value)
    console.log(newNickname)
  }

  function handleNewInstrument(e) {
    setNewInstrument(e.value)
    console.log(newInstrument)
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

	return (
		<div className="friendsProfile">
      <label className="userData-updateable">Seus dados podem ser alterados por aqui:</label>
			<div>
        <input className="userData-input" type="text" placeholder={userData.email} onChange={handleNewEmail} />
        <input className="userData-input" type="text" placeholder={userData.nome} onChange={handleNewName} />
        <input className="userData-input" type="text" placeholder={userData.apelido} onChange={handleNewNickname} />
        <Select
          className="userData-select"
          onChange={handleNewInstrument}
          options={optionsInsrument}
          defaultInputValue={userData.instrument}
        />
				<input type="button" className="userData-button" onClick={handleUpdateData} value='Atualizar Perfil' />
			</div>
		</div>
	);
}
