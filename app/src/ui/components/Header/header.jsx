import './header.scss';
import React, { useEffect, useState } from 'react'
import { useGlobalUser } from '../../../context/index'
import { useApi } from '../../../hooks/api'
import { Link } from 'react-router-dom';
import profile from '../../../images/profileHeader.png'
import envelope from '../../../images/envelope.png'
import lupa from '../../../images/lupa.png'

    
export function Header() {
    const api = useApi();
    const [userData, setUserData] = useState()
    const [user, setUser] = useGlobalUser()
    const [username, setUsername] = useState("")

    function handleSearch() {
        console.log(username)
    }
    
    useEffect(() => {
        async function listarDadosUsuario() {
            const response = await api.listarDadosUsuario()
            if (response.status === 200) {
                setUserData(response.data)
            } else if (response.status === 400) {
                alert('bugou pa caralho')
            } 
        }
        listarDadosUsuario()
    }, [])

    function onChangeUserSearch(event){
        setUsername(event.target.value);
    }

    function isLogged() {
        if(user) {
            return (
                <>      
                    <div className="container__wrapper--links">
                        <input type="text" className="container__wrapper--input" onChange={onChangeUserSearch} placeholder="Busque aqui" />
                        <button className="button lupa" onCLick={handleSearch}><img src={lupa} width="25" /></button>
                    </div>
                    <div className="container__wrapper--links">
                        {userData ?  <Link to={`/profile/${userData.email}`}><img src={profile} width="25" /></Link> : null} 
                    </div>
                    <div className="container__wrapper--links">
                        {userData ?  <Link to={`/friendship`}><img src={envelope} width="25" /></Link> : null} 
                    </div>
                </>
            );
        }
    }

    return (
        <div className="header">
            <div className="container">
                <Link to='/home' className="container__link--name">
                    <div>
                        <label className="container__social-name">
                            toAllMusicians
                        </label>
                    </div>
                </Link>
                <div className="container__wrapper">
                   {isLogged()}
                </div>
            </div>
        </div>
    );
}