import './register.scss';
import React, { useState } from 'react'
import { useApi } from '../../../hooks/api'
import { Link, useHistory } from 'react-router-dom';
import Select from 'react-select';

export function RegisterBox() {
    const api = useApi();
    const history = useHistory()
    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
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
            registroUsuario()
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

    const customStyles = {
        control: base => ({
          ...base,
          height: 50,
          border: `1px solid gray`
        })
      };

    return (
        <div className="body register">
            <div className="container">
                <div className="container__brand">
                    <label className="brandName">to All Musicians</label>
                </div>
                <div className="container__wrapper">
                    <div className="container__login">
                        <input className="container__login--input" type="email" placeholder="Email" onChange={onChangeUsername}></input>
                    </div>
                    <div className="container__login">
                        <input className="container__login--input" type="text" placeholder="Nome" onChange={onChangeUsername}></input>
                    </div>
                    <div className="container__login">
                        <input className="container__login--input" type="password" placeholder="Senha" onChange={onChangePassword}></input>
                    </div>
                    <div className="container__login">
                        <input className="container__login--input" type="text" placeholder="Apelido(opcional)" onChange={onChangeNickname}></input>
                    </div>
                    <div className="container__login">
                        <input className="container__login--input" type="date"  onChange={onChangeBirthDay}></input>
                    </div>
                    <div className="container__login">
                    <Select
                        className="post-select"
                        onChange={onChangeInstrument}
                        options={options}
                        styles={customStyles}
                        placeholder="Instrumento"
                    />
                    </div>
                    <div className="container__login">
                        <input className="container__login--button" type="button" value="Cadastrar" onClick={handleSubmit}></input>
                    </div>
                    <div className="container__divisor"></div>
                    <Link to={`/`} className="container__register">
                        <input className="container__login--button container__register--button" type="button" value="Acessa conta"></input>
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
