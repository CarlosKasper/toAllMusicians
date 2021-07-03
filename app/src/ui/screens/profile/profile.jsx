import { FeedList, Header, UserDetails, FriendsProfile } from "../../components";
import { useApi } from '../../../hooks/api'
import { useEffect, useState } from "react";
import { useGlobalFeed, useGlobalUserInfo } from '../../../context/index'
import { useParams } from "react-router";


export function ProfileScreen() {
    const api = useApi();
    const [userData, setUserData] = useState()
    const [postsUser, setPostsUser] = useState()
    const [userFriends, setUserFriends] = useState()
    const [feed, setFeed] = useGlobalFeed(false)
    const [userInfo] = useGlobalUserInfo(false)
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

        if(userInfo && email && userInfo.email == email) {
            async function exibirDadosDoPefilDoUsuarioEspecifico() {
                const response = await api.exibirDadosDoPefilDoUsuarioEspecifico()
                if (response.status === 200) {
                    setUserData(response.data)
                } else if (response.status === 400) {
                    alert('bugou pa caralho')
                }
            }
        } else {
            async function listarPostsUsuario() {
                const response = await api.listarPostsUsuario(email)
                if (response.status === 200) {
                    setPostsUser(response.data.content)
                } else if (response.status === 400) {
                    alert('bugou pa caralho')
                }
            }
        }

        async function listFriends() {
            const response = await api.listFriends(email)
            if (response.status === 200) {
                setUserFriends(response.data.content)
            } else if (response.status === 400) {
                alert('bugou pa caralho')
            }
        }

        async function listarPostsUsuario() {
          if(email) {
            const response = await api.listarPostsUsuario(email, userInfo.email)
            if (response.status === 200) {
                setPostsUser(response.data.content)
            } else if (response.status === 400) {
                alert('bugou pa caralho')
            }
          }
        }

        listarPostsUsuario()
        listFriends()
        exibirDadosDoPefilDoUsuario()
    }, [feed, email])

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

    async function deletedFriend(userEmail) {
        const response = await api.deletedFriend(userEmail)
        if (response.status === 400) {
            alert('perdemo nos post')
        }

        setFeed(!feed)
    }

    return (
        <>
            <Header/>
            {userData && userFriends && postsUser ?
                <UserDetails
                    userData={userData}
                    postLength={postsUser.length}
                    userFriends={userFriends} />
            : null}
            <div id="feed">
                {postsUser ?
                    postsUser.map((postsUser) =>
                    <FeedList
                        feedContent={postsUser}
                        likePost={likePost}
                        unlikePost={unlikePost}
                    />
                )
                : null}
            </div>
            <div id="friends">
                {userFriends ?
                    userFriends.map((userFriends) =>
                        <FriendsProfile
                            userFriends={userFriends}
                            deletedFriend={deletedFriend}
                            profileEmail={email}/>
                    )
                : null}
            </div>
        </>
    );
}
