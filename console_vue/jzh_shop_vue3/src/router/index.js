
import Home from "../views/Home/index.vue";
import About from "../views/About/index.vue";

// import VueRouter from "vue-router";
import { createRouter, createWebHistory } from 'vue-router'


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

// // https://blog.csdn.net/jpgzhu/article/details/109065133

