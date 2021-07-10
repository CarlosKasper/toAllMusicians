import createGlobalState from 'react-create-global-state';

const stringifyUserSearch = localStorage.getItem('userSearch');

const userSearch = stringifyUserSearch && JSON.parse(stringifyUserSearch);

const [useGlobalUserSearch, UserSearchProvider] = createGlobalState(userSearch);

export { useGlobalUserSearch, UserSearchProvider };
