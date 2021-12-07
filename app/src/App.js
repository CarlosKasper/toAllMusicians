import { Switch, Route } from 'react-router-dom';
import { useGlobalUser } from './context';
import '../src/ui/components/index.scss';
import '../src/sccs/index.scss';
import {
	LoginScreen,
	RegisterScreen,
	HomeScreen,
	FriendScreen,
	ProfileScreen,
	SearchScreen,
} from './ui/screens';
import { Provider } from 'react-redux';
import store from './store';

function App() {
	const [user] = useGlobalUser();

	return (
		<div>
      <meta httpEquiv="Content-Security-Policy" content="upgrade-insecure-requests"/>
			<Switch>
				<Provider store={store}>
					<Route path="/" exact>
						<LoginScreen />
					</Route>
					<Route path="/register" exact>
						<RegisterScreen />
					</Route>
					<Route path="/home" exact>
						{!user ? <LoginScreen /> : <HomeScreen />}
					</Route>
					<Route path="/friendship" exact>
						{!user ? <LoginScreen /> : <FriendScreen />}
					</Route>
					<Route path="/profile/:email" exact>
						{!user ? <LoginScreen /> : <ProfileScreen />}
					</Route>
					<Route path="/search" exact>
						{!user ? <LoginScreen /> : <SearchScreen />}
					</Route>
				</Provider>
			</Switch>
		</div>
	);
}

export default App;
