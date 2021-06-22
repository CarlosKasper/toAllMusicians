import './login.scss';
import React, { useState } from 'react'
import { useApi } from '../../../hooks/api'
import { useHistory } from 'react-router-dom'

export function LoginBox() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const history = useHistory()
    const api = useApi();

    async function gerarToken() {
			const response = await api.gerarToken("carlos@gmail.com", "123")
			if (response.status === 200) {
				history.push("/home")
			} else if (response.status === 400) {
				alert("ai erremo")
			}
    }

    function handleSubmit (event) {
			event.preventDefault()
			gerarToken()
    }
    
    function onChangeUsername(event){
			setUsername(event.target.value);
    }

    function onChangePassword(event){
			setPassword(event.target.value);
    }

    return (
			<div className="body login">
				<div className="container">
					<div className="container__wrapper">
						<div>
							<label className="container__text">Login</label>
						</div>
						<div className="container__login">
							<input className="container__login--input" type="email" placeholder="Digite seu email" onChange={onChangeUsername}></input>
						</div>
						<div className="container__login">
							<input className="container__login--input" type="password" placeholder="Digite sua senha" onChange={onChangePassword}></input>
						</div>
						<div className="container__login">
							<input className="container__login--button" type="button" value="Entrar" onClick={handleSubmit}></input>
						</div>
						<div className="container__login">
							<label className="container__register">Não tem login? <a className="container__login--link" href="/register" >Cadastro</a></label>
						</div>
					</div>
				</div>
			</div>
    );
}