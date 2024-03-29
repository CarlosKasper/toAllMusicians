import './register.scss';
import React, { useState } from 'react';
import { useApi } from '../../../hooks/api';
import { Link, useHistory } from 'react-router-dom';
import Select from 'react-select';
import toallmusicians from '../../../images/toallmusicians.png';
import Swal from 'sweetalert2';
import { isEnterPress } from '../../common';

export function RegisterBox() {
	const api = useApi();
	const history = useHistory();
	const [username, setUsername] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [nickname, setNickname] = useState('');
	const [birthday, setBirthday] = useState('');
	const [instrument, setInstrument] = useState('');

	async function registroUsuario() {
		const response = await api.registroUsuario(
			username,
			email,
			password,
			nickname,
			birthday,
			instrument
		);
		if (response.status === 201) {
			history.push('/');
		} else {
			Swal.fire({
				icon: 'warning',
				title: 'Erro',
				text: 'Occoreu um erro inesperado!',
				showDenyButton: false,
				confirmButtonText: `Tentar novamente`,
				confirmButtonColor: '#1A71D9',
			});
		}
	}

	function handleSubmit(event) {
		event.preventDefault();
		registroUsuario();
	}

	function onChangeUsername(event) {
		setUsername(event.target.value);
	}

	function onChangePassword(event) {
		setPassword(event.target.value);
	}

	function onChangeEmail(event) {
		setEmail(event.target.value);
	}

	function onChangeNickname(event) {
		setNickname(event.target.value);
	}

	function onChangeBirthDay(event) {
		setBirthday(
			event.target.value.substr(0, 10).split('-').reverse().join('/')
		);
	}

	function onChangeInstrument(event) {
		setInstrument(event.value);
	}

	const options = [
		{ value: 'GUITARRA', label: 'Guitarra' },
		{ value: 'VIOLAO', label: 'Violão' },
		{ value: 'BATERIA', label: 'Bateria' },
		{ value: 'SAXOFONE', label: 'Saxofone' },
		{ value: 'TROMBONE', label: 'Trombone' },
		{ value: 'TECLADO', label: 'Teclado' },
		{ value: 'GAITADEFOLE', label: 'Gaita de Fole' },
	];

	const customStyles = {
		control: (base) => ({
			...base,
			height: 50,
			border: `1px solid gray`,
		}),
	};

	function submitListening(event) {
		isEnterPress(event.keyCode) ? registroUsuario() : null;
	}

	return (
		<div className="body register">
			<div className="container-register">
				<div className="container-register__brand">
					<img
						src={toallmusicians}
						className="container-register__brand--logo"
						alt="Logo"
					/>
				</div>
				<div className="container-register__wrapper">
					<div className="container-register__login">
						<input
							className="container-register__login--input"
							type="email"
							placeholder="Nome"
							onChange={onChangeUsername}
							onKeyDown={(e) => submitListening(e)}
						></input>
					</div>
					<div className="container-register__login">
						<input
							className="container-register__login--input"
							type="text"
							placeholder="Email"
							onChange={onChangeEmail}
							onKeyDown={(e) => submitListening(e)}
						></input>
					</div>
					<div className="container-register__login">
						<input
							className="container-register__login--input"
							type="password"
							placeholder="Senha"
							onChange={onChangePassword}
							onKeyDown={(e) => submitListening(e)}
						></input>
					</div>
					<div className="container-register__login">
						<input
							className="container-register__login--input"
							type="text"
							placeholder="Apelido(opcional)"
							onChange={onChangeNickname}
							onKeyDown={(e) => submitListening(e)}
						></input>
					</div>
					<div className="container-register__login">
						<input
							className="container-register__login--input"
							type="date"
							placeholder="dd/mm/yyyy"
							onChange={onChangeBirthDay}
							onKeyDown={(e) => submitListening(e)}
						></input>
					</div>
					<div className="container-register__login">
						<Select
							className="post-select"
							onChange={onChangeInstrument}
							options={options}
							styles={customStyles}
							placeholder="Instrumento"
							onKeyDown={(e) => submitListening(e)}
							isSearchable={false}
						/>
					</div>
					<div className="container-register__login">
						<input
							className="container-register__login--button"
							type="button"
							value="Cadastrar"
							onClick={handleSubmit}
						></input>
					</div>
					<div className="container-register__divisor"></div>
					<Link to={`/`} className="container-register__register">
						<input
							className="container-register__login--button container-register__register--button"
							type="button"
							value="Acessar conta"
						></input>
					</Link>
				</div>
			</div>
			<div className="container-register__informations">
				<div className="informations-center">
					<div className="container-register__divisor"></div>
					<div className="creator">
						<label className="creator__info">toAllMusicians 2021</label>
						<label className="creator__info">Made by Carlos Kasper</label>
					</div>
				</div>
			</div>
		</div>
	);
}
