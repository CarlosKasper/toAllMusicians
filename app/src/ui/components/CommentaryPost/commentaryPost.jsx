import './commentaryPost.scss';
import { useApi } from '../../../hooks/api'
import { useGlobalUserInfo } from '../../../context/index'
import React, { useState } from 'react'
import { Link } from 'react-router-dom';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

export function CommentaryPost({commentaryContent, deleteCommentary}) {
    const api = useApi();
    const [userInfo] = useGlobalUserInfo()

    function handleComentary() {
        deleteCommentary(commentaryContent.post.id, commentaryContent.id)
    }

    return (
        <div className="commentaryPost">
            <div className="commentaryPost__info">
                <Link className="link" to={`/profile/${commentaryContent.musico.email}`}>
                    <div className="commentaryPost__image commentaryPost__image--comentary">
                        {commentaryContent.musico.imagem ? <img className="profile-image" src={commentaryContent.musico.imagem.url} alt="Foto de perfil" />
                        :
                            <span className="hiddenFileInput">
                                <input   name="theFile" disabled/>
                            </span>
                        }
                    </div>
                </Link>
                <div className="comentary">
                    <div className="commentaryPost__wrapper">
                      <div>
                        <b>{commentaryContent.musico.nome[0].toUpperCase() + commentaryContent.musico.nome.slice(1).toLowerCase()}</b>
                      </div>
                      <div>
                        <b>{commentaryContent.musico.instrumento[0].toUpperCase() + commentaryContent.musico.instrumento.slice(1).toLowerCase()}</b>
                      </div>
                    </div>
                    <div className=" commentaryPost__content">
                        <label className="commentaryPost__description commentaryPost__description--comentary"> {commentaryContent.comentario} </label>
                    </div>
                </div>
                {userInfo.email === commentaryContent.musico.email ?
                  <DeleteForeverIcon className="deleteCommentary" onClick={handleComentary}/>
                : null}
            </div>
        </div>
    );
}
