import './header.scss';
import React, { useEffect, useState } from 'react'
import { useGlobalUser, useGlobalUserInfo, useGlobalUserSearch } from '../../../context/index'
import { useApi } from '../../../hooks/api'
import { Link, useHistory } from 'react-router-dom';
import profile from '../../../images/profileHeader.png'
import envelope from '../../../images/envelope.png'
import { Nav, Navbar, Form, FormControl, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

export function Header() {
    const api = useApi();
    const [user, setUser] = useGlobalUser()
    const [userSearch, setUserSearch] = useGlobalUserSearch()
    const [userInfo, setUserInfo] = useGlobalUserInfo()
    const history = useHistory()
    const [username, setUsername] = useState("")

    useEffect(() => {
        async function listarDadosUsuario() {
            if(user) {
              const response = await api.listarDadosUsuario()
              if (response.status === 200) {
                  setUserInfo(response.data)
              } else if (response.status === 400) {
                  alert('bugou pa caralho')
              }
            }
        }
        listarDadosUsuario()
    }, [])

    function onChangeUserSearch(event){
        setUserSearch(event.target.value);
    }

    function handleSearchUser() {
        if(userSearch) {
            history.push("/search")
        }
    }

    function isLogged() {
        if(user) {
            return (
                <>
                    <Form inline className="form__search">
                        <FormControl type="text" placeholder="Digite um nome" className="mr-sm-2" id="searchUser" onChange={onChangeUserSearch} />
                        <Button variant="outline-light" className="form__btn" onClick={handleSearchUser}>Procurar</Button>
                    </Form>
                    {userInfo ?  <Nav.Link><Link to={`/profile/${userInfo.email}`}><img src={profile} width="25" /><label className="burger__mobile">Perfil</label></Link></Nav.Link> : null}
                    {userInfo ?  <Nav.Link><Link to={`/friendship`}><img src={envelope} width="25" /><label className="burger__mobile">Solicitações</label></Link></Nav.Link> : null}
                </>
            );
        }
    }

    return (
        <Navbar collapseOnSelect expand="lg" className="bg-navbar" variant="dark">
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
