import { FeedList, Header, UserDetails } from "../../components";
import { useApi } from '../../../hooks/api'
import { useEffect, useState } from "react";
import { useGlobalFeed } from '../../../context/index'
import { useParams } from "react-router";

    
export function ProfileScreen() {
    const api = useApi();
    const [userData, setUserData] = useState()
    const [postsUser, setPostsUser] = useState()
    const [feed, setFeed] = useGlobalFeed(false)
    let { email } = useParams();

    useEffect(() => {
        async function exibirDadosDoPefilDoUsuario() {
            const response = await api.exibirDadosDoPefilDoUsuario(email)
            if (response.status === 200) { 
                setUserData(response.data)
            } else if (response.status === 400) {
                alert('bugou pa caralho')
            } 
        }

        async function listarPostsUsuario() {
            const response = await api.listarPostsUsuario(email)
            if (response.status === 200) {
                setPostsUser(response.data.content)
            } else if (response.status === 400) {
                alert('bugou pa caralho')
            } 
        }
        listarPostsUsuario()
        exibirDadosDoPefilDoUsuario()
    }, [feed, email])

    async function curtirPost(idPost) {
        const response = await api.curtirPost(idPost)
        if (response.status === 400) {
            alert('perdemo nos post')
        } 

        setFeed(!feed)
    }

    return (
        <>
            <Header/>
            {userData ? <UserDetails userData={userData} /> : null}
            {postsUser ? 
            postsUser.map((feedContent) => 
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