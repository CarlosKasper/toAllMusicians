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

    async function likePost(idPost) {
        const response = await api.likePost(idPost)
        if (response.status === 201) {
            setFeed(!feed)
        }
    }

    async function unlikePost(idLike, idPost) {
        const response = await api.unlikePost(idLike, idPost)
        if (response.status === 200) {
            setFeed(!feed)
        }
    }
    
    return (
        <>
            <Header logged={true}/>
            <CreatePost />
            {feedContent ? 
            feedContent.map((feedC) => 
                <FeedList 
                    feedContent={feedC}
                    likePost={likePost}
                    unlikePost={unlikePost}
                />
            )
            : null}
        </>
    );
}