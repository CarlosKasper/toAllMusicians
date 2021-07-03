import './friendSolicitation.scss';
import add from '../../../images/add.png'
import profile from '../../../images/profile.png'
import { Link } from 'react-router-dom';
import { useGlobalUserInfo } from '../../../context';

export function FriendSolicitation({ userSolicitations, acceptAsFriend, denniedAsFriend }) {
    const [userInfo, setUserInfo] = useGlobalUserInfo()

    function handleAccept() {
        acceptAsFriend(userSolicitations.id)
    }

    function handleDennied() {
        denniedAsFriend(userSolicitations.id)
    }

    return (
        <div className="friendSolicitation__info">
            <Link to={`/profile/${userSolicitations.musico1.email}`}>
                <div className="friendSolicitation__image">
                    {userSolicitations.musico1.imagem ? <img className="profile-image" src={userSolicitations.musico1.imagem.url} alt="Foto de perfil" />
                    :
                        <span className="hiddenFileInput">
                            <input   name="theFile" disabled/>
                        </span>
                    }
                </div>
            </Link>
            <div className="friendSolicitation__about">
                <div className="friendSolicitation__wrapper">
                    <div className="friendSolicitation__user">
                        <div>
                            {userSolicitations.musico1.nome} ({userSolicitations.musico1.apelido})
                        </div>
                    </div>
                    <div className="friendSolicitation__instrument">
                        {userSolicitations.musico1.instrumento}
                    </div>
                </div>
                <div className="friendSolicitation__response">
                  <div className="friendSolicitation__image-reponse" onClick={handleAccept}>
                      <img className="add-image" src={add} alt="Adicionar como amigo" />
                  </div>
                  <div className="friendSolicitation__image-reponse" onClick={handleDennied}>
                      <img className="add-image" src={profile} alt="Adicionar como amigo" />
                  </div>
                </div>
            </div>
        </div>
    );
}
