import Vue from 'vue'
import App from './App.vue'


//new Vue({ ... })：创建一个新的 Vue 实例。
new Vue({
  //el: '#app'：指定 Vue 实例挂载的 DOM 元素。这意味着 Vue 实例将管理这个元素及其子元素。
  //控制条调试：<div id="app">
  el: '#app',
  ////render: h => h(App)：render 函数用来渲染一个 Vue 组件，这里使用的是箭头函数的简写形式。h 是一个函数，用来生成虚拟 DOM（VNode），它将 App 组件渲染为虚拟 DOM。
  render: h => h(App)

})
