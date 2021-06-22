import './searchProfile.scss';
import { useApi } from '../../../hooks/api'
import { useState } from 'react';
import { useGlobalUser } from '../../../context';

export function SearchProfile({userProfile}) {
    const [user, setUser] = useGlobalUser()
    const api = useApi();
    console.log(user)
    return (
        <div className="searchProfile">
            <div className="searchProfile__info">
                <div className="searchProfile__image">
                    {userProfile.imagem ? <img src={userProfile.imagem.url} alt="Foto de perfil" /> 
                    : 
                    <span className="hiddenFileInput">
                        <input type="file" name="theFile" disabled/>
                    </span>}
                </div>
                <div className="searchProfile__wrapper">
                    <div className="searchProfile__user">
                        <div>
                            {userProfile.nome} ({userProfile.apelido})
                        </div>
                    </div>
                <div className="searchProfile__instrument">
                    {userProfile.instrumento}
                </div>
                </div>
            </div>
        </div>
    );
}