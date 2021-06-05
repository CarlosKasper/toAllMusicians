import './register.scss';
import React, { useState } from 'react'
import { useApi } from '../../../hooks/api'
import { useHistory } from 'react-router-dom'
import Select from 'react-select';

export function RegisterBox() {
    const api = useApi();
    const history = useHistory()
    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [repeatPassword, setRepeatPassword] = useState("")
    const [nickname, setNickname] = useState("")
    const [birthday, setBirthday] = useState("")
    const [instrument, setInstrument] = useState("")

    async function registroUsuario() {
        const response = await api.registroUsuario(username, email, password, nickname, birthday, instrument)
        if (response.status === 201) {
            history.push("/")
        } else if (response.status === 400) {
            alert('paraparapara email ja existe')
        } else {
            alert('eta')
        }
    }

    function handleSubmit (event) {
        event.preventDefault()
        
        if (repeatPassword === password) {
            registroUsuario()
        }
    }
    
    function onChangeUsername(event){
        setUsername(event.target.value);
    }    
    
    function onChangeEmail(event){
        setEmail(event.target.value);
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

    function onChangeBirthDay(event) {
        setBirthday((event.target.value).substr(0, 10).split('-').reverse().join('/'))
    }

    function onChangeInstrument(event) {
        setInstrument(event.value)
    }

    const options = [
        { value: 'GUITARRA', label: 'Guitarra' },
    ];

    return (
        <div className="body register">
            <div className="container">
                <div className="container__wrapper">
                    <div>
                        <label className="container__text">Cadastrar</label>
                    </div>
                    <div className="container__login">
                        <input className="container__login--input" type="text" placeholder="Digite seu nome" onChange={onChangeUsername}></input>
                    </div>
                    <div className="container__login">
                        <input className="container__login--input" type="email" placeholder="Digite seu email" onChange={onChangeEmail}></input>
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
                        <input className="container__login--input" type="date"  onChange={onChangeBirthDay}></input>
                    </div>
                    <div className="container__login">
                    <Select
                        className="container__login--select"
                        onChange={onChangeInstrument}
                        options={options}
                        placeholder="Seu instrumento"
                    />
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