import './userDetails.scss';
import React, { useState } from 'react'
import { useApi } from '../../../hooks/api'

export function UserDetails({ userData, postLength, userFriends }) {
    const api = useApi();
    const feed = document.getElementById("feed");
    const friends = document.getElementById("friends");

    const [imagePreview, setImagePreview] = useState(null);

    async function addPhoto(event) {
        let file = event.target.files[0];
        let image = new FormData();
        image.append('image', file);
        console.log(image)
        setImagePreview(URL.createObjectURL(file));

        const response = await api.uploadImagePerfil(image);
    }

    function showFriends() { 
        feed.style.display ='none'
        friends.style.display ='block'
    }

    function showPosts() {
        feed.style.display ='block'
        friends.style.display ='none'
    }

    return (
        <div className="profile">
            <div className="container">  
                <div className="container__image">
                    {userData.imagem ? <img className="profile-image" src={userData.imagem.url} alt="foto do usuario"/> 
                    : 
                    <span className="hiddenFileInput">
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
           <div className="user-info">
                <div className="information" onClick={showFriends}>
                    Amigos {userFriends.length}
                </div>
                <div className="information" onClick={showPosts}>
                    Post {postLength}
                </div>
           </div>
        </div>
    );
}