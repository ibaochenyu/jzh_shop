import Vue from 'vue'
import App from './App.vue'
import router from './router'
//Can’t resolve ‘vue-router’
//npm install vue-router


//import VueRouter from "vue-router";
// import router from "./router";

//Vue.use(VueRouter);
// Vue.use(router);


////////////
import ElementUI from 'element-ui'
//import '@/element-ui-theme/style.css'
// import '@/element-ui-theme/style.css'
import Avue from '@smallwei/avue'             // api: https://avue.top
import '@smallwei/avue/lib/index.css'
Vue.use(Avue)
Vue.use(ElementUI)

// unknown custom element: <el-card> - did you register the component correctly? For recursive components, make sure to provide the "name" option.
//原因：没有在main.js引入elementUI
////////


import httpRequest from '../src/utils/httpRequest'
// 挂载全局
Vue.prototype.$http = httpRequest//否则hotSearch.vue中this.$http.adornUrl('/admin/hotSearch/page')无法使用

Vue.config.productionTip = false;


//new Vue({ ... })：创建一个新的 Vue 实例。
new Vue({
  //el: '#app'：指定 Vue 实例挂载的 DOM 元素。这意味着 Vue 实例将管理这个元素及其子元素。
  //控制条调试：<div id="app">
  el: '#app',
  router,
  ////render: h => h(App)：render 函数用来渲染一个 Vue 组件，这里使用的是箭头函数的简写形式。h 是一个函数，用来生成虚拟 DOM（VNode），它将 App 组件渲染为虚拟 DOM。
  render: h => h(App)

})
