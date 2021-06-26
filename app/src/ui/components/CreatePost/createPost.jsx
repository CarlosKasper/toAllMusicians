import './createPost.scss';
import { useApi } from '../../../hooks/api'
import React, { useState } from 'react'
import Select from 'react-select';
import { useGlobalFeed } from '../../../context/index'
import { useEffect } from 'react';

export function CreatePost() {
    const api = useApi();
    const [title, setTitle] = useState("")
    const [instrument, setInstrument] = useState("")
    const [privacity, setPrivacity] = useState("")
    const [feed, setFeed] = useGlobalFeed(false)
    const [allPosts, setAllPosts] = useGlobalFeed(0)
    const [imagePreview, setImagePreview] = useState(null);
    const [teste, setTeste] = useState(null);
    const [imageResult, setImageResult] = useState();
    const [published, setPublished] = useState(false);

    useEffect(() => {
        async function listAllPosts() {
            const response = await api.listAllPosts()
            if (response.status === 200) {
                    response.data.content.map((biggestId) => 
                        {if(biggestId.id > allPosts) {
                            setAllPosts(biggestId.id)
                        }}
                    )
            }
        }

        listAllPosts()
    }, [published])
    
    async function publicarPost() {
        const response = await api.criarPost(title, privacity, instrument)
        if (response.status === 201) {
            addPhoto()
            setFeed(!feed)
            setPublished(!published)
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
        { value: 'VIOLAO', label: 'Violão' },
    ];

    const optionsPrivacity = [
        { value: 'PUBLICO', label: 'Público' },
        { value: 'PRIVADO', label: 'Privado' }
    ];

    async function addPhoto(event) {        
        if(imagePreview !== null) {
            const response = await api.uploadPostImage(allPosts, imageResult);
            setTitle('')
            setImageResult(null)
            setImagePreview(null)
        } else if(event) {
            let file = event.target.files[0];
            let image = new FormData();
            image.append('image', file);
            setImageResult(image)
            setImagePreview(URL.createObjectURL(file));
            event.target.value = null;
        }
    }


    return (
        <div className="createPost">
            <div className="container">
                <div className="container__input">
                    <textarea type="text" className="container__input--text" placeholder="Digite algo sobre música..." onChange={handlePost} value={title}></textarea>
                </div>
                    <span className="hiddenFileInput">
                        <input type="file" name="theFile" onChange={addPhoto}/>
                    </span>
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