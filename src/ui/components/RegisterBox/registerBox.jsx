import './register.scss';
import React, { useState } from 'react'

export function RegisterBox() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [repeatPassword, setRepeatPassword] = useState("")
    const [nickname, setNickname] = useState("")

    function handleSubmit (event) {
        event.preventDefault()
        // funcao api pra register
        
        if (repeatPassword !== password) {
            alert('acerta essa porra')
        }
        console.log(username, password);
    }
    
    function onChangeUsername(event){
        setUsername(event.target.value);
    }

    function onChangePassword(event){
        setPassword(event.target.value);
    }

    function onChangeRepeatPassword(event){
        setRepeatPassword(event.target.value);
    }

    function onChangeNickname(event){
        setNickname(event.target.value);
    }

    return (
        <div className="body register">
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
                        <input className="container__login--input" type="password" placeholder="Digite sua senha novamente" onChange={onChangeRepeatPassword}></input>
                    </div>
                    <div className="container__login">
                        <input className="container__login--input" type="text" placeholder="Digite seu apelido(opcional)" onChange={onChangeNickname}></input>
                    </div>
                    <div className="container__login">
                        <input className="container__login--button" type="button" value="Cadastrar" onClick={handleSubmit}></input>
                    </div>
                    <div className="container__login">
                        <label className="container__register">Já possui login? <a className="container__login--link" href="/" >Login</a></label>
                    </div>
                </div>
            </div>
        </div>
    );
}