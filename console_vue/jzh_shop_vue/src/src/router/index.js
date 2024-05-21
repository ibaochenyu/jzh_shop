import Home from "./components/Home";
import About from "./components/About";

const routes = [
    {
        path: "/home",
        name: "home",
        component: Home
    },
    {
        path: "/about",
        name: "about",
        component: About
    },
    {
        path: "/",
        component: Home
    }
];
export default routes;
// https://blog.csdn.net/jpgzhu/article/details/109065133
