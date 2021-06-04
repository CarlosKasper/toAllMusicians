import createGlobalState from 'react-create-global-state'

const stringifyUser = localStorage.getItem('user')

const user = stringifyUser && JSON.parse(stringifyUser)

const [useGlobalUser, UserProvider] = createGlobalState(user)

export { useGlobalUser, UserProvider }