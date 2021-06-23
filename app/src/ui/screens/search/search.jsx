import { useState } from "react";
import { useEffect } from "react";
import { useGlobalUserSearch } from "../../../context";
import { useApi } from "../../../hooks/api";
import { Header, SearchProfile } from "../../components";

export function SearchScreen() {
    const [userSearch, setUserSearch] = useGlobalUserSearch()    
    const api = useApi();
    const [users, setUsers] = useState()

    useEffect(() => {
        async function searchUser() {
            const response = await api.searchUser(userSearch)
            if (response.status === 200) {
                setUsers(response.data.content)
            } else if (response.status === 400) {
                alert('tem algo de errado amigao')
            }
        }

        searchUser()
    }, [])

    async function handleAddFriend(email) {
        const response = await api.addFriend(email)
        if (response.status === 201) {
            console.log(response)
        } else if (response.status === 400) {
            alert("ai erremo")
        }
    }   

    return (
        <>
            <Header />
            {users ? 
                users.map((users) => 
                <SearchProfile userProfile={users} addFriend={handleAddFriend}/>
                )
            : null
            }
        </>
    );
}