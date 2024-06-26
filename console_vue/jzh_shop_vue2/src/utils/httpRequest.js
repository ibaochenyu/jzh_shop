import Vue from 'vue'
import axios from 'axios' //使用axios.create的必须
import merge from 'lodash/merge'
import { Message } from 'element-ui'//否则无法提示错误窗口
//bcy：构造const的http，之后会导出这个http
//idea中js调试，配置的参数 --remote-allow-origins=*
const http = axios.create({
  //timeout: 1000 * 30,
  timeout: 1000 * 120,
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json; charset=utf-8'
  }
})



/**
 * 请求拦截
 */
// http.interceptors.request.use(config => {
//   config.headers['Authorization'] = Vue.cookie.get('Authorization') // 请求头带上token
//   return config
// }, error => {
//   return Promise.reject(error)
// })

/**
 * 响应拦截
 *///此处写上后，自动把data.data给data。根据状态码来个提示
http.interceptors.response.use(response => {//阻拦的关键：http.interceptors.response.use。之后把这个设置好的http挂载在全局里头
  // blob 格式处理
  if (response.request.responseType === 'blob') {
    return response
  }


  const res = response.data
  // return res


  // 00000 请求成功
  if (res.code === '00000') {
    return res
  }

  // // A00001 用于直接显示提示用户的错误,内容由输入决定
  // if (res.code === 'A00001') {
  //   Message({
  //     message: res.msg || 'Error',
  //     type: 'error',
  //     duration: 1.5 * 1000
  //   })
  //   return Promise.reject(res)
  // }

  // // A00002 用于直接显示提示系统的成功,内容由输入决定
  // if (res.code === 'A00002') {
  //   Message({
  //     message: res.msg,
  //     type: 'success',
  //     duration: 1.5 * 1000
  //   })
  // }

  // // A00004 未授权
  // if (res.code === 'A00004') {
  //   clearLoginInfo()
  //   router.push({ name: 'login' })
  // }

  // A00005 服务器异常
  if (res.code === 'A00005') {
    console.error('============== 请求异常 ==============')
    console.log('接口地址: ', response.config.url.replace(process.env.VUE_APP_BASE_API, ''))
    console.log('异常信息: ', res)
    console.error('============== 请求异常 end ==========')
    Message({
      message: '服务器出了点小差，请稍后再试2222',
      type: 'error',
      duration: 1.5 * 1000,
      customClass: 'element-error-message-zindex'
    })
    return Promise.reject(res)//Promise.reject() 静态方法返回一个已拒绝（rejected）的 Promise 对象，拒绝原因为给定的参数。
  }
  if (res.code === 'SQL00001') {
    Message({
      message: 'sql修改数据出错，很可能是uniqueKey冲突--前端提示',
      type: 'error',
      duration: 1.5 * 1000,
      customClass: 'element-error-message-zindex'
    })
  }
  if (res.code === 'EXC114514') {
    Message({//Message是element-ui的
      message: '测试错误出现了。这里是前端的错误',
      type: 'error',
      duration: 1.5 * 1000,
      customClass: 'element-error-message-zindex'
    })
  }
}
, error => {
  switch (error.response.status) {
    case 400:
      Message({
        message: error.response.data,
        type: 'error',
        duration: 1500,
        customClass: 'element-error-message-zindex'
      })
      break
    case 401:
      clearLoginInfo()
      router.push({ name: 'login' })
      break
    case 405:
      Message({
        message: 'http请求方式有误',
        type: 'error',
        duration: 1500,
        customClass: 'element-error-message-zindex'
      })
      break
    case 500:
      Message({
        message: '服务器出了点小差，请稍后再试3333',
        type: 'error',
        duration: 1500,
        customClass: 'element-error-message-zindex'
      })
      break
    case 501:
      Message({
        message: '服务器不支持当前请求所需要的某个功能',
        type: 'error',
        duration: 1500,
        customClass: 'element-error-message-zindex'
      })
      break
  }
  return Promise.reject(error)
}
)

/**
 * 请求地址处理
 * @param {*} actionName action方法名称
 */
http.adornUrl = (actionName) => {//adorn 美/ əˈdɔːrn / v.装饰；使生色

  // 非生产环境 && 开启代理, 接口前缀统一使用[/proxyApi/]前缀做代理拦截!
  //return (process.env.NODE_ENV !== 'production' && process.env.OPEN_PROXY ? '/proxyApi' : process.env.VUE_APP_BASE_API) + actionName
  //mall4j
  //return 'http://127.0.0.1:8085' + actionName
  //我自己写的实验
  //return 'http://127.0.0.1:8095'+  actionName
  //jzh-shop
  return 'http://127.0.0.1:8081' + actionName
  //return 'http://localhost:8081' + actionName
}

/**
 * get请求参数处理
 * @param {*} params 参数对象
 * @param {*} openDefultParams 是否开启默认参数?
 */
http.adornParams = (params = {}, openDefultParams = true) => {
  //不添加额外的时间了，所以这里取消了

  // var defaults = {
  //   't': new Date().getTime()
  // }
  // return openDefultParams ? merge(defaults, params) : params
  //return params
  // var temp=merge(defaults, params)
  return params
}

/**
 * post请求数据处理
 * @param {*} data 数据对象
 * @param {*} openDefultdata 是否开启默认数据?
 * @param {*} contentType 数据格式
 *  json: 'application/json; charset=utf-8'
 *  form: 'application/x-www-form-urlencoded; charset=utf-8'
 */
http.adornData = (data = {}, openDefultdata = true, contentType = 'json') => {//???
  // var defaults = {
  //   't': new Date().getTime()
  // }
  // data = openDefultdata ? merge(defaults, data) : data
  // return contentType === 'json' ? JSON.stringify(data) : qs.stringify(data)
  return JSON.stringify(data);
}


export default http
