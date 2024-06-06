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
app.use(router).use(ElementPlus).mount('#app')
//////////////////////////

