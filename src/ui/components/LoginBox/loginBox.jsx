import './login.scss';
import React, { useState } from 'react'

export function LoginBox() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    function handleSubmit (event) {
        event.preventDefault()
        // funcao api pra login
        console.log(username, password);
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