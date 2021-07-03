import { Header, FriendSolicitation } from "../../components";
import { useApi } from '../../../hooks/api'
import { useEffect } from "react";
import { useState } from "react";

export function FriendScreen() {
    const api = useApi();
    const [solicitations, setSolicitations] = useState()
    const [relationship, setRelationship] = useState()

    useEffect(() => {
        async function listarSolicitacoes() {
            const response = await api.listarSolicitacoes()
            if (response.status === 200) {
                setSolicitations(response.data.content)
            }
        }

        listarSolicitacoes()
    }, [relationship])

    async function acceptAsFriend(relationshipId) {
        const response = await api.acceptAsFriend(relationshipId)
        if (response.status === 201) {
            setRelationship(!relationship)
        }
    }

    async function denniedAsFriend(relationshipId) {
        const response = await api.denniedAsFriend(relationshipId)
        if (response.status === 201) {
            setRelationship(!relationship)
        }
    }

    return (
        <>
            <Header/>
            <div className="friendSolicitation">
              <label className="friendSolicitation__result"> Solicitações de amizades pendentes:</label>
              {solicitations ?
                  solicitations.map((solicitations) =>
                  <FriendSolicitation
                      userSolicitations={solicitations}
                      acceptAsFriend={acceptAsFriend}
                      denniedAsFriend={denniedAsFriend}/>
                  )
              : null
              }
            </div>
        </>
    );
}
