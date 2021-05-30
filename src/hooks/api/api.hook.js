import { useAxios } from './use-axios.hook'
import { useCallback } from 'react'
import { useGlobalUser } from '../../context/index'

export function useApi() {

  const [user, setUser] = useGlobalUser()
  const token = user
  const axios = useAxios(
    'http://localhost:8090/',
    { Authorization: 'Bearer ' + token }
  )

  const autenticarLogin = useAxios(
    'http://localhost:8090/',
    {     
        params: {},
        withCredentials: true,
        auth: {
            username: '05aaa927-46fe-415d-abfa-2f56d7ede1e7',
            password: '295e8b13-3659-4395-89cd-d47290cf4935'
        }
    }
  )

  const cadastroUsuario = useAxios(
    'http://localhost:8090/'
  )

  async function gerarToken(login, senha) {

    let bodyFormData = new FormData()

    bodyFormData.set('grant_type', 'password');
    bodyFormData.set('scope', 'any');
    bodyFormData.set('username', login);
    bodyFormData.set('password', senha);

    return autenticarLogin({
      method: 'post',
      url: '/oauth/token',
      data: bodyFormData,
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
      .then(function (response) {
        setUser(response.data.access_token);
        return response.data
      })
      .catch(function (error) {
        return error.response
      })
  }

  async function registroUsuario(nome, email, senha, apelido, dataNascimento, instrumento ) {
    try {
      const response = await cadastroUsuario.post(
        '/usuario/cadastro', {
        nome,
        email,
        apelido,
        dataNascimento,
        senha,
        instrumento
      }
      )
      return response
    } catch (error) {
      return error.response
    }
  }

  return useCallback({
    gerarToken,
    registroUsuario
  }, [])
}