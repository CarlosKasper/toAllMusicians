import { CreatePost, FeedList, Header } from "../../components";
import { useGlobalFeed } from '../../../context/index'
import React, { useEffect, useState } from 'react'
import { useApi } from '../../../hooks/api'

export function HomeScreen() {
    const api = useApi();    
    const [feed, setFeed] = useGlobalFeed(false)
    const [feedContent, setFeedContent] = useState()

    useEffect(() => {
        async function listarPostsAmigos() {
            const response = await api.listarPostsAmigos()
            if (response.status === 200) {
                setFeedContent(response.data.content)
            } else if (response.status === 400) {
                alert('perdemo nos post')
            } 
        }


        listarPostsAmigos()
    }, [api, feed])

    async function curtirPost(idPost) {
        const response = await api.curtirPost(idPost)
        console.log(response)
        if (response.status === 200) {
            setFeed(!feed)
        } else {
            // preciso descobrir como pegar o id da curtida
            //const response = await api.descurtirPost(idPost)
        } 
    }
    
    return (
        <>
            <Header logged={true}/>
            <CreatePost />
            {feedContent ? 
            feedContent.map((feedContent) => 
                <FeedList 
                    postId={feedContent.id}
                    postUser={feedContent.musico.nome}
                    postPrivacity={feedContent.privacidade}
                    postDescription={feedContent.titulo}
                    postInstrument={feedContent.instrumento}
                    curtirPost={curtirPost}
                />
            )
            : null}
        </>
    );
}