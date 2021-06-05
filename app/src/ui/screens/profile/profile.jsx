import { FeedList, Header, UserDetails } from "../../components";
import { useApi } from '../../../hooks/api'
import { useEffect, useState } from "react";
import { useParams } from "react-router";

    
export function ProfileScreen() {
    const api = useApi();
    const [userData, setUserData] = useState()
    const [postsUser, setPostsUser] = useState()
    let { email } = useParams();

    useEffect(() => {
        async function listarDadosUsuario() {
            const response = await api.listarDadosUsuario()
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
        listarDadosUsuario()
    }, [])

    return (
        <>
            <Header/>
            {userData ? <UserDetails userData={userData} /> : null}
            {postsUser ? 
            postsUser.map((feedContent) => 
                <FeedList 
                    postUser={feedContent.musico.nome}
                    postPrivacity={feedContent.privacidade}
                    postDescription={feedContent.titulo}
                    postInstrument={feedContent.instrumento}
                />
            )
            : null}
        </>
    );
}