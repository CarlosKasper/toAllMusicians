import './userDetails.scss';
import React, { useState } from 'react'
import { useApi } from '../../../hooks/api'

export function UserDetails({ userData, postLength, userFriends }) {
    const [imagem, setImagem] = useState();
    const api = useApi();
    console.log(userData)

    return (
        <div className="profile">
            <div className="container">  
                <div className="container__image">
                    {imagem ? <img src="https://toallmusiciansbase.s3.sa-east-1.amazonaws.com/comentary.png" alt="foto do usuario"/> 
                    : 
                    <span className="hiddenFileInput">
                        <input type="file" name="theFile"/>
                    </span>
                    }
                </div>
               <div className="container__info">
                   <div className="container__name">
                        <label className="nome-perfil">{userData.nome}({userData.apelido})</label>
                   </div>
                   <div>
                        <label className="instrumento">{userData.instrumento}</label>
                   </div>
               </div>
           </div>
           <div className="user-info">
                <div className="information">
                    Amigos {userFriends.length}
                </div>
                <div className="information">
                    Post {postLength}
                </div>
           </div>
        </div>
    );
}