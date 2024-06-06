import Home from "../views/Home/index.vue";
import About from "../views/About/index.vue";
import hotSearch from "../views/modules/shop/hotSearch.vue";

//两个点好像是上级

// import VueRouter from "vue-router";
import { createRouter, createWebHistory } from 'vue-router'
import {defineAsyncComponent} from "vue";

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
    },
    {
        path: "/hotSearch",
        name: "hotSearch",
        component: hotSearch//https://segmentfault.com/q/1010000039684879
        // componentName:defineAsyncComponent(
        //     {
        //         loader: () => import('../views/modules/shop/hotSearch.vue')
        //     }
        //     // ()=>import('../views/modules/shop/hotSearch.vue')
        // )
    }
];
// const router = new VueRouter({
//     mode: "history",
//     routes
// })
const router = createRouter({
    history: createWebHistory(),
    routes
})
export { routes }

export default router
