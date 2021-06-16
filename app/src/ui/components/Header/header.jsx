import './header.scss';
import React, { useEffect, useState } from 'react'
import { useGlobalUser } from '../../../context/index'
import { useApi } from '../../../hooks/api'
import { Link } from 'react-router-dom';
import profile from '../../../images/profileHeader.png'
import envelope from '../../../images/envelope.png'
import lupa from '../../../images/lupa.png'
import { Nav, Navbar, Form, FormControl, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
    
export function Header() {
    const api = useApi();
    const [userData, setUserData] = useState()
    const [user, setUser] = useGlobalUser()
    const [username, setUsername] = useState("")

    function handleSearch() {
        console.log(username)
    }
    
    useEffect(() => {
        async function listarDadosUsuario() {
            const response = await api.listarDadosUsuario()
            if (response.status === 200) {
                setUserData(response.data)
            } else if (response.status === 400) {
                alert('bugou pa caralho')
            } 
        }
        listarDadosUsuario()
    }, [])

    function onChangeUserSearch(event){
        setUsername(event.target.value);
    }

    function isLogged() {
        if(user) {
            return (
                <>       
                    <Form inline className="form__search">
                        <FormControl type="text" placeholder="Digite um nome" className="mr-sm-2" />
                        <Button variant="outline-secondary" className="form__btn">Procurar</Button>
                    </Form>
                    {userData ?  <Nav.Link><Link to={`/profile/${userData.email}`}><img src={profile} width="25" /><label className="burger__mobile">Perfil</label></Link></Nav.Link> : null} 
                    {userData ?  <Nav.Link><Link to={`/friendship`}><img src={envelope} width="25" /><label className="burger__mobile">Solicitações</label></Link></Nav.Link> : null} 
                </>
            );
        }
    }

    return (
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
        <Navbar.Brand>
            <Link to='/home' class="brand">
                toAllMusicians
            </Link>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto"></Nav>
            <Nav>
                {isLogged()}
            </Nav>
        </Navbar.Collapse>
      </Navbar>
    );
}