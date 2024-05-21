
import Home from "../components/Home";
import About from "../components/About";


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

const router = new VueRouter({
    mode: "history",
    routes
});



export { routes }

export default router


// export default routes;
//
// // https://blog.csdn.net/jpgzhu/article/details/109065133
