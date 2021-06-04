
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
          <HomeScreen />
        </Route>
        <Route path="/friendship" exact>
          <FriendScreen />
        </Route>
        <Route path="/profile" exact>
          <ProfileScreen />
        </Route>
      </Switch>
    </div>
  );
}

export default App;
