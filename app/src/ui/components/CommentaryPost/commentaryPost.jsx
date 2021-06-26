import './commentaryPost.scss';
import { useApi } from '../../../hooks/api'
import { useGlobalUserInfo } from '../../../context/index'
import React, { useState } from 'react'
import { Link } from 'react-router-dom';

export function CommentaryPost({commentaryContent, deleteCommentary}) {
    const api = useApi();    
    const [userInfo] = useGlobalUserInfo()

    function handleComentary() {
        deleteCommentary(commentaryContent.post.id, commentaryContent.id)
    }

    return (
        <div>
            <div className="container__info">
                <Link className="link" to={`/profile/${commentaryContent.musico.email}`}>
                    <div className="container__image container__image--comentary">
                        {commentaryContent.musico.imagem ? <img className="profile-image" src={commentaryContent.musico.imagem.url} alt="Foto de perfil" /> 
                        :
                            <span className="hiddenFileInput">
                                <input   name="theFile" disabled/>
                            </span>
                        }
                    </div>
                </Link>
                <div className="comentary">
                    <div className="container__wrapper">
                        <div className="container__user">
                            <div>
                                {commentaryContent.musico.nome}
                            </div>
                        </div>
                        <div className="container__instrument">
                                {commentaryContent.musico.instrumento}
                        </div>
                    </div>
                    <div className=" container__content ">
                        <label className="container__description container__description--comentary"> {commentaryContent.comentario} </label>
                    </div>
                </div>
                {userInfo.email === commentaryContent.musico.email ? 
                    <div onClick={handleComentary}>
                        DEL
                    </div> 
                : null}
            </div>
        </div>
    );
}