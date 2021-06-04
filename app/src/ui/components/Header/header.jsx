import './header.scss';
import React, { useState } from 'react'

export function Header(logged) {
    const [username, setUsername] = useState("")
    
    function onChangeUserSearch(event){
        setUsername(event.target.value);
    }

    function isLogged() {
        if(logged.logged) {
            return (
                <>      
                    <div className="container__wrapper--links">
                        <input type="text" className="container__wrapper--input" onChange={onChangeUserSearch} placeholder="Busque aqui"></input>
                    </div>
                    <div className="container__wrapper--links">
                        <a href="/profile">Profile</a>
                    </div>
                    <div className="container__wrapper--links">
                        <a href="/friendship">Friendship</a>
                    </div>
                </>
            );
        }
    }

    return (
        <div className="header">
            <div className="container">
                <div>
                    <label className="container__social-name">
                        toAllMusicians
                    </label>
                </div>
                <div className="container__wrapper">
                   {isLogged()}
                </div>
            </div>
        </div>
    );
}