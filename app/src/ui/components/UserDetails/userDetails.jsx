import './userDetails.scss';
import React from 'react'

export function UserDetails({userData}) {

    function handlePicture(event) {
        console.log(event.files.name)
    }

    return (
        <div className="profile">
            <div className="container">  
                <div className="container__image">
                    {userData.url ? <img src={userData.url} alt="WIN-20200813-19-34-19-Pro" width="150" height="150" />: 
                    <span className="hiddenFileInput">
                        <input type="file" name="theFile" onChange={handlePicture}/>
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