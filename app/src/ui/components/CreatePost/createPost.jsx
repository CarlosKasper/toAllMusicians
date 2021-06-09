import './createPost.scss';
import { useApi } from '../../../hooks/api'
import React, { useState } from 'react'
import Select from 'react-select';
import { useGlobalFeed } from '../../../context/index'

export function CreatePost() {
    const api = useApi();
    const [title, setTitle] = useState("")
    const [instrument, setInstrument] = useState("")
    const [privacity, setPrivacity] = useState("")
    const [feed, setFeed] = useGlobalFeed(false)

    async function publicarPost() {
        const response = await api.criarPost(title, privacity, instrument)
        if (response.status === 201) {
            setFeed(!feed)
        } else if (response.status === 400) {
            alert('tem algo de errado amigao')
        }
    }
    
    function handlePost(event){
        setTitle(event.target.value);
    }

    function onChangeInstrument(event){
        setInstrument(event.value)
    }

    function onChangePrivacity(event){
        setPrivacity(event.value)
    }

    const optionsInsrument = [
        { value: 'GUITARRA', label: 'Guitarra' },
    ];

    const optionsPrivacity = [
        { value: 'PUBLICO', label: 'Público' },
        { value: 'PRIVADO', label: 'Privado' }
    ];


    return (
        <div className="createPost">
            <div className="container">
                <div className="container__input">
                    <textarea type="text" className="container__input--text" placeholder="Digite algo sobre música..." onChange={handlePost}></textarea>
                </div>
                <div className="container__selects">
                    <Select
                            className="post-select"
                            onChange={onChangeInstrument}
                            options={optionsInsrument}
                            placeholder="Seu instrumento"
                    />
                    <Select
                        className="post-select"
                        onChange={onChangePrivacity}
                        options={optionsPrivacity}
                        placeholder="Privacidade"
                    />
                </div>
                <div className="container__submit">
                    <input type="button" className="container__submit--button" onClick={publicarPost} value="Compartilhar" />
                </div>
            </div>
        </div>
    );
}