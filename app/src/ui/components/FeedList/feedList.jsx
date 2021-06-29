import './feedList.scss';
import { useApi } from '../../../hooks/api'
import { useGlobalFeed, useGlobalUserInfo } from '../../../context/index'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import like from '../../../images/like.png'
import { CommentaryPost } from "../index";

export function FeedList({feedContent, likePost, unlikePost}) {
    const api = useApi();
    const [commentaryPost, setcommentaryPost] = useState()
    const [newCommentary, setNewCommentary] = useState()
    const [userInfo, setUserInfo] = useGlobalUserInfo()
    const [likes, setLikes] = useState()
    const [feed, setFeed] = useGlobalFeed(false)

    useEffect(() => {
        async function listarComentario() {
            const response = await api.listarComentario(feedContent.id)
            if (response.status === 200) {
                setcommentaryPost(response.data)
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

    function handleCommentary(e) {
        setNewCommentary(e.target.value)
    }

    async function sendCommentary() {
        const response = await api.newCommentary(feedContent.id, newCommentary)
        if (response.status === 201) {
            setNewCommentary("")
            setFeed(!feed)

        }
    }

    return (
      <div className="feedList">
        <Link className="link" to={`/profile/${feedContent.musico.email}`}>
            <div className="feedList__info">
                <div className="feedList__image">
                    {feedContent.musico.imagem ? <img className="profile-image" src={feedContent.musico.imagem.url} alt="Foto de perfil" />
                    :
                        <span className="hiddenFileInput">
                            <input   name="theFile" disabled/>
                        </span>
                    }
                </div>
                <div className="feedList__wrapper--feed">
                    <div className="feedList__user">
                        <div>
                            <b>{feedContent.musico.nome}</b>
                        </div>
                        <div>
                            <b>{feedContent.privacidade[0].toUpperCase() + feedContent.privacidade.slice(1).toLowerCase()}</b>
                        </div>
                    </div>
                <div className="feedList__instrument">
                        <b>{feedContent.instrumento[0].toUpperCase() + feedContent.instrumento.slice(1).toLowerCase()}</b>
                </div>
                </div>
            </div>
        </Link>
        <div className="feedList__content">
            <label className="feedList__description"> {feedContent.titulo} </label>
        </div>
        {feedContent.imagem ?
          <div className="feedList__container-image">
            <img className="feedList__post-image" src={feedContent.imagem ? feedContent.imagem.url :null} />
          </div>
        : null}
        <div className="feedList__content">
            <div className="feedList__interation">
              <img src={like} width="50px" onClick={handleLike}/>
            </div>
            <label className="feedList__likes"> {likes ? likes.content.length : '0'} {likes && likes.content.length > 1 ? 'Curtidas' : 'Curtida'} </label>
        </div>
        {commentaryPost ?
        commentaryPost.content.map((comentary) =>
            <CommentaryPost
                commentaryContent={comentary}
                deleteCommentary={deleteCommentary}/>
        )
        : null}
        <div className="feedList__input">
            <input className="feedList__input-commentary" type="text" onChange={handleCommentary} value={newCommentary}/>
            <input type="button" className="feedList__input-confirm" value="Comentar" onClick={sendCommentary}/>
        </div>
      </div>
    );
}
