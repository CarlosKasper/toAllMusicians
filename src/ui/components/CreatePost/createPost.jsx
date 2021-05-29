import './createPost.scss';
import React, { useState } from 'react'

export function CreatePost() {
    const [newPost, setNewPost] = useState("")

    function handleSubmit() {
        console.log('jasd')
    }
    
    function handlePost(event){
        setNewPost(event.target.value);
    }


    return (
        <div className="createPost">
            <div className="container">
                <div className="container__input">
                    <textarea type="text" className="container__input--text" placeholder="Digite algo sobre música..." onChange={handlePost}></textarea>
                </div>
                <div className="container__submit">
                    <input type="button" className="container__button" onClick={handleSubmit} value="Compartilhar" />
                </div>
            </div>
        </div>
    );
}