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

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
