import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'

import router from './router'



//////////////
// createApp(App).mount('#app')
const app = createApp(App)
//app.use(router).mount('#app')
//app.use(router).mount('#app')

//////////////////////////
// Failed to resolve component: el-button I
/* 引入 ElementPlus */
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

////
//需要npm install axios --save
//import axios from 'axios'
//////////////////////////////
//摘自mall4v
import Avue from '@smallwei/avue'             // api: https://avue.top
import '@smallwei/avue/lib/index.css'
//app.use(router).use(ElementPlus).use(Avue).mount('#app')
app.use(router);
app.use(ElementPlus);
app.use(Avue);
//app.use(Avue,{axios});
app.mount('#app');
//////////////////////////
import httpRequest from '@/utils/httpRequest'
// 挂载全局
app.$http = httpRequest // ajax请求方法      //这句话本质是Vue.prototype.$http = axios
// app.isAuth = isAuth     // 权限方法

