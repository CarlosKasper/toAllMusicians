import { Header, LoginBox } from "../../components";

export function LoginScreen() {

    return (
        <>
            <Header logged={false}/>
            <LoginBox />
        </>
    );
}