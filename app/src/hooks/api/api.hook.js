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
    { Authorization: 'Basic bWV1LWNsaWVudC1pZDptZXUtc2VjcmV0LWlk' }
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
        console.log(response)
        return response
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