import createGlobalState from 'react-create-global-state';

const stringifyUserInfo = localStorage.getItem('userInfo');

const userInfo = stringifyUserInfo && JSON.parse(stringifyUserInfo);

const [useGlobalUserInfo, UserInfoProvider] = createGlobalState(userInfo);

export { useGlobalUserInfo, UserInfoProvider };
