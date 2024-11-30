import { Route, Routes } from "react-router-dom";
import routes from "./routes";

const Router = () => {
    const pageRoutes = routes.map(({ path, title, element }) => {
        return <Route key={title} path={`/${path}`} element={element} />;
    });

    return <Routes>{pageRoutes}</Routes>;
};

export default Router;