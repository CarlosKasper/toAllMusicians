import './feedList.scss';
import { useApi } from '../../../hooks/api'
import { useGlobalFeed, useGlobalUserInfo } from '../../../context/index'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import like from '../../../images/like.png'
import comentary from '../../../images/comentary.png'
import { CommentaryPost } from "../index";

export function FeedList({feedContent, likePost, unlikePost}) {
    const api = useApi();    
    const [comentaryPost, setComentaryPost] = useState()
    const [userInfo, setUserInfo] = useGlobalUserInfo()
    const [likes, setLikes] = useState()
    const [feed, setFeed] = useGlobalFeed(false)

    useEffect(() => {
        async function listarComentario() {
            const response = await api.listarComentario(feedContent.id)
            if (response.status === 200) {
                setComentaryPost(response.data)
            }
        }
    
        async function listarCurtida() {
            const response = await api.listarCurtida(feedContent.id)
            if (response.status === 200) {
                setLikes(response.data)
            }
        }

        listarComentario()
        listarCurtida()
    }, [api, feed])

    function handleLike() {
        likes.content.map((likes) => 
            {if(userInfo.email === likes.musico.email && feedContent.id === likes.post.id) {
                unlikePost(likes.id, likes.post.id)
                return;
            }}
        ) 
        
        likePost(feedContent.id)
    }

    async function deleteCommentary(postId, commentaryId) {
        const response = await api.deleteCommentary(postId, commentaryId)
        if (response.status === 200) {
            setFeed(!feed)
        }
    }

    return (
        <div className="feedList">
            <div className="container">
                <Link className="link" to={`/profile/${feedContent.musico.email}`}>
                    <div className="container__info">
                        <div className="container__image">
                            {feedContent.musico.imagem ? <img className="profile-image" src={feedContent.musico.imagem.url} alt="Foto de perfil" /> 
                            :
                                <span className="hiddenFileInput">
                                    <input   name="theFile" disabled/>
                                </span>
                            }
                        </div>
                        <div className="container__wrapper">
                            <div className="container__user">
                                <div>
                                    {feedContent.musico.nome}
                                </div>
                                <div>
                                    {feedContent.privacidade}
                                </div>
                            </div>
                        <div className="container__instrument">
                                {feedContent.instrumento}
                        </div>
                        </div>
                    </div>
                </Link>
                <hr/>
                <div className="container__content">
                    <label className="container__description"> {feedContent.titulo} </label>
                </div>
                <hr/>
                <div className="container__content">
                    <label className="container__likes"> {likes ? likes.content.length : '0'} {likes && likes.content.length > 1 ? 'Curtidas' : 'Curtida'} </label>
                </div>
                <div className="container__interation">
                    <div onClick={handleLike}>
                        <img src={like} width="50px"/>
                    </div>
                    <div id="comentary">
                        <img src={comentary} width="45px"/>
                    </div>
                </div>
                <hr></hr>
                {comentaryPost ? 
                comentaryPost.content.map((comentary) => 
                    <CommentaryPost 
                        commentaryContent={comentary}
                        deleteCommentary={deleteCommentary}/>
                )
                : null}
            </div>
        </div>
    );
}