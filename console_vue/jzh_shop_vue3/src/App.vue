

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



<!--<template>-->
<!--  <div>-->
<!--    <h2>主页222</h2>-->
<!--    <div>主页33333</div>-->
<!--    &lt;!&ndash; 使用 router-link 组件来导航. &ndash;&gt;-->
<!--    <hr />-->
<!--    &lt;!&ndash; 路由出口 &ndash;&gt;-->
<!--    <router-view></router-view>-->
<!--  </div>-->
<!--</template>-->



<template>
<!--  vue2的老写法吧。警告：router-view> can no longer be used directly inside <transition> or <keep-alive>.-->
<!--  Use slot props instead:-->
  <transition name="fade">
    <router-view></router-view>
  </transition>


  <router-view v-slot="{ Component }">
    <transition name="fade">
      <keep-alive :include="['MapView']">
        <component :is="Component"
        /></keep-alive>
    </transition>
  </router-view>

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
