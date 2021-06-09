import './feedList.scss';

export function FeedList({postId, postUser, postPrivacity, postDescription, postInstrument, curtirPost}) {

    function handleCurtir() {
        curtirPost(postId)
    }


    return (
        <div className="feedList">
            <div className="container">
                <div className="container__info">
                    <div className="container__picture">
                    </div>
                    <div className="container__wrapper">
                        <div className="container__user">
                            <div>
                                {postUser}
                            </div>
                            <div>
                                {postPrivacity}
                            </div>
                        </div>
                    <div className="container__instrument">
                        {postInstrument}
                    </div>
                    </div>
                </div>
                <div className="container__content">
                    <label className="container__description"> {postDescription} </label>
                </div>
                <div className="container__image">
                </div>
                <div className="container__content">
                    <label className="container__likes"> 1 Curtidas</label>
                </div>
                <div className="container__button">
                    <input type="button" value="Curtir" className="container__like" onClick={handleCurtir}/>
                </div>
            </div>
        </div>
    );
}