import { CreatePost, Header } from "../../components";

export function HomeScreen() {
    return (
        <>
            <Header logged={true}/>
            <CreatePost />
        </>
    );
}