import Vue from 'vue'
import Home from "../components/Home";
import About from "../components/About";
import HotSearch from "../views/modules/shop/hotSearch.vue";
import Router from 'vue-router'

Vue.use(Router)
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
        path: "/hotSearch",
        name: "hotSearch",
        component: HotSearch
    },
    {
        path: "/",
        component: Home
    }
];

// https://blog.csdn.net/jpgzhu/article/details/109065133
// https://blog.csdn.net/m0_46309087/article/details/134983584
//const router = new VueRouter({
const router = new Router({
    mode: "history",
    routes: routes
});

// export default routes;
// // https://blog.csdn.net/jpgzhu/article/details/109065133


// export { routes }

export default router

// new Vue({
//     router,
//     render: h => h(App)
// }).$mount("#app");
