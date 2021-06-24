import './friendsProfile.scss';
import deleteFriend from '../../../images/deleteFriend.png'
import { Link } from 'react-router-dom';
import { useGlobalUserInfo } from '../../../context';

export function FriendsProfile({userFriends, deletedFriend, profileEmail}) {
    const [userInfo] = useGlobalUserInfo()
    
    function handleRemoveFriend() {
        deletedFriend(userFriends.email)
    }

    return (
        <div className="friendsProfile">
            <div className="friendsProfile__info">
                <Link to={`/profile/${userFriends.email}`}>
                    <div className="friendsProfile__image">
                        {userFriends.imagem ? <img className="profile-image" src={userFriends.imagem.url} alt="Foto de perfil" /> 
                        :
                            <span className="hiddenFileInput">
                                <input   name="theFile" disabled/>
                            </span>
                        }
                    </div>
                </Link>
                <div className="friendsProfile__about">
                    <div className="friendsProfile__wrapper">
                        <div className="friendsProfile__user">
                            <div>
                                {userFriends.nome} ({userFriends.apelido})
                            </div>
                        </div>
                        <div className="friendsProfile__instrument">
                            {userFriends.instrumento}
                        </div>
                    </div> 
                    {profileEmail === userInfo.email ? 
                        <div className="friendsProfile__deleteFriend" onClick={handleRemoveFriend}>
                            <img className="deleteFriend-image" src={deleteFriend} alt="Remover amigo" />
                        </div>
                    : null}
                </div>
            </div>
        </div>
    );
}