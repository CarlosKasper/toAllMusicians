import { useState, useEffect } from "react";
import { useHistory } from 'react-router-dom';
import { useGlobalUserSearch } from "../../../context";
import { useApi } from "../../../hooks/api";
import { Header, SearchProfile } from "../../components";
import Swal from 'sweetalert2'

export function SearchScreen() {
    const [userSearch] = useGlobalUserSearch()
    const api = useApi();
    const history = useHistory()
    const [users, setUsers] = useState()

    useEffect(() => {
        async function searchUser() {
            const response = await api.searchUser(userSearch)
            if (response.status === 200 && response.data.content.length > 0) {
                setUsers(response.data.content)
            } else if (response.data.content.length == 0) {
              Swal.fire({
                icon: 'info',
                title: 'Nenhuma resultado encontrado!',
                confirmButtonText: `Voltar para o feed`,
                confirmButtonColor: '#1A71D9',
              }).then((result) => {
                if (result.isConfirmed) {
                  history.push("/home")
                }
              })
            }
        }

        searchUser()
    }, [])

    async function handleAddFriend(email) {
      const response = await api.addFriend(email)
      if (response.status === 201) {
      } else if (response.status === 400) {
        alert("ai erremo")
      }
    }

    return (
      <>
          <Header />
          <div className="searchProfile">
          {users ?
            <label className="searchProfile__result"> Resultado da pesquisa:</label>
            : <label className="searchProfile__result">Nenhum resultado encontrado para a sua pesquisa.</label>
            }
            {users ?
                users.map((users) =>
                  <SearchProfile userProfile={users} addFriend={handleAddFriend}/>
                )
                :null
            }
          </div>
      </>
    );
}
