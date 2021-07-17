import './login.scss';
import React, { useState } from 'react';
import { useApi } from '../../../hooks/api';
import { Link, useHistory } from 'react-router-dom';
import toallmusicians from '../../../images/toallmusicians.png';
import Swal from 'sweetalert2';

export function LoginBox() {
	const [username, setUsername] = useState('');
	const [password, setPassword] = useState('');
	const history = useHistory();
	const api = useApi();
	console.log(username, password);
	async function gerarToken() {
		const response = await api.gerarToken('carlos@gmail.com', '123');
		if (response.status === 200) {
			history.push('/home');
		} else {
      Swal.fire({
        icon: 'warning',
        title: 'Falha no login',
        text: 'Seu email ou senha são inválidos!',
        showDenyButton: false,
        confirmButtonText: `Tentar novamente`,
        confirmButtonColor: '#1A71D9',
      })
		}
	}

	function handleSubmit(event) {
		event.preventDefault();
		gerarToken();
	}

	function onChangeUsername(event) {
		setUsername(event.target.value);
	}

	function onChangePassword(event) {
		setPassword(event.target.value);
	}

	return (
		<div className="body login">
			<div className="container">
				<div className="container__brand">
					<img
						src={toallmusicians}
						className="container__brand--logo"
						alt="Logo"
					/>
				</div>
				<div className="container__wrapper">
					<div className="container__login">
						<input
							className="container__login--input"
							type="email"
							placeholder="Email"
							onChange={onChangeUsername}
						></input>
					</div>
					<div className="container__login">
						<input
							className="container__login--input"
							type="password"
							placeholder="Senha"
							onChange={onChangePassword}
						></input>
					</div>
					<div className="container__login">
						<input
							className="container__login--button"
							type="button"
							value="Entrar"
							onClick={handleSubmit}
						></input>
					</div>
					<div className="container__divisor"></div>
					<Link to={`/register`} className="container__register">
						<input
							className="container__login--button container__register--button"
							type="button"
							value="Criar nova conta"
						></input>
					</Link>
				</div>
			</div>
			<div className="container__informations">
				<div className="informations-center">
					<div className="container__divisor"></div>
					<div className="creator">
						<label className="creator__info">toAllMusicians 2021</label>
						<label className="creator__info">Made by Carlos Kasper</label>
					</div>
				</div>
			</div>
		</div>
	);
}
