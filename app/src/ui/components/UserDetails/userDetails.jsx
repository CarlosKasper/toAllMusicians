import './userDetails.scss';
import React, { useState } from 'react'
import { useApi } from '../../../hooks/api'

export function UserDetails({userData}) {
    const [imagem, setImagem] = useState();
    const api = useApi();
 
  
    async function addPhoto(e) {
        var file = e.target.files[0];
        var fileName = e.target.name;

    }

    return (
        <div className="profile">
            <div className="container">  
                <div className="container__image">
                    {imagem ? <img src="https://toallmusiciansbase.s3.sa-east-1.amazonaws.com/comentary.png" alt="foto do usuario"/> : <span className="hiddenFileInput">
                        <input type="file" name="theFile" onChange={addPhoto}/>
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
           <div className="friends">
               AMIGOS 123
           </div>
        </div>
    );
}