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

// https://blog.csdn.net/jpgzhu/article/details/109065133
const router = new VueRouter({
    mode: "history",
    routes
});

// export default routes;
// // https://blog.csdn.net/jpgzhu/article/details/109065133


export { routes }

export default router

new Vue({
    router,
    render: h => h(App)
}).$mount("#app");
