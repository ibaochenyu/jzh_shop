// import Vue from 'vue'
// import App from './App.vue'
import router from '@/router'



/**
 * 清除登录信息
 */
export function clearLoginInfo () {// isAddDynamicMenuRoutes 自定义的变量
  // App.cookie.delete('Authorization')
  router.options.isAddDynamicMenuRoutes = false
}
