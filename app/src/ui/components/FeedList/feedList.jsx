import './feedList.scss';
import { useApi } from '../../../hooks/api'
import { useGlobalFeed } from '../../../context/index'
import React, { useEffect, useState } from 'react'
import like from '../../../images/like.png'
import comentary from '../../../images/comentary.png'

export function FeedList({postId, postUser, postPrivacity, postDescription, postInstrument, curtirPost}) {
    const api = useApi();    
    const [comentaryPost, setComentaryPost] = useState()
    const [likePost, setLikePost] = useState()
    const [feed, setFeed] = useGlobalFeed(false)

    function handleCurtir() {
        curtirPost(postId)
    }

    function handleComentar() {
        //curtirPost(postId)
    }

    useEffect(() => {
        async function listarComentario() {
            const response = await api.listarComentario(postId)
            if (response.status === 200) {
                setComentaryPost(response.data)
            }
        }
    
        async function listarCurtida() {
            const response = await api.listarCurtida(postId)
            if (response.status === 200) {
                setLikePost(response.data)
            }
        }

        listarComentario()
        listarCurtida()
    }, [api, feed])

    return (
        <div className="feedList">
            <div className="container">
                <div className="container__info">
                    <div className="container__picture">
                    </div>
                    <div className="container__wrapper">
                        <div className="container__user">
                            <div>
                                {postUser}
                            </div>
                            <div>
                                {postPrivacity}
                            </div>
                        </div>
                    <div className="container__instrument">
                        {postInstrument}
                    </div>
                    </div>
                </div>
                <hr/>
                <div className="container__content">
                    <label className="container__description"> {postDescription} </label>
                </div>
                <div className="container__image">
                </div>
                <hr/>
                <div className="container__content">
                    <label className="container__likes"> {likePost ? likePost.content.length : '0'} {likePost && likePost.content.length > 1 ? 'Curtidas' : 'Curtida'} </label>
                </div>
                <div className="container__interation">
                    <div onClick={handleCurtir}>
                        <img src={like} width="50px"/>
                    </div>
                    <div onClick={handleComentar}>
                        <img src={comentary} width="45px"/>
                    </div>
                </div>
            </div>
        </div>
    );
}