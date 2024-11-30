import App from "@/App";
import Login from "../Login";
import Register from "../Register";

const routes = [
    {
        path: "/",
        element: <App />,
        title: "home"
    },
    {
        path: "/login",
        element: <Login />,
        title: "login"
    },
    {
        path: "/register",
        element: <Register />,
        title: "register"
    }
];

export default routes;