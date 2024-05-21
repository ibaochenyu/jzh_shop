import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'

//240521增加
import router from './router'

//createApp(App).mount('#app')
createApp(App).use(router).mount('#app')
