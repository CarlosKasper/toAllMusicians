
import { Switch, Route, Redirect } from 'react-router-dom'
import { useGlobalUser } from './context';
import '././ui/components/index.scss'
import { 
  LoginScreen, 
  RegisterScreen, 
  HomeScreen, 
  FriendScreen, 
  ProfileScreen } from './ui/screens';

function App() {
  const [user] = useGlobalUser()
  
  return (
    <div>
      <Switch>
        <Route path="/" exact>
          <LoginScreen />
        </Route>
          <Route path="/register" exact>
          <RegisterScreen />
        </Route>
        <Route path="/home" exact>
          {!user ? <LoginScreen/> : <HomeScreen />}
        </Route>
        <Route path="/friendship" exact>
          {!user ? <LoginScreen/> : <FriendScreen />}
        </Route>
        <Route path="/profile/:email" exact>
          {!user ? <LoginScreen/> : <ProfileScreen />}
        </Route>
      </Switch>
    </div>
  );
}

export default App;
