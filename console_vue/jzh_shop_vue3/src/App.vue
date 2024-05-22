
<script setup>
//Failed to resolve import "vue-router" from "src/App.vue". Does the file exist?
// npm install vue-router@next --save
import { useRoute } from 'vue-router'

const route = useRoute()
</script>

<!--<template>-->
<!--  <header>-->
<!--    <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125" />-->

<!--    <div class="wrapper">-->
<!--      <HelloWorld msg="You did it!" />-->
<!--    </div>-->
<!--  </header>-->

<!--  <main>-->
<!--    <TheWelcome />-->
<!--  </main>-->
<!--</template>-->

<template>
  <div>
    <h2>主页222</h2>
    <div>主页33333</div>
    <!-- 使用 router-link 组件来导航. -->
    <hr />
    <!-- 路由出口 -->
    <router-view></router-view>
  </div>
</template>


<style scoped>
header {
  line-height: 1.5;
}

<template>
  <div id="app">
    <h1>router demo</h1>
    <hr />
    <!-- 路由出口 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {}
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

