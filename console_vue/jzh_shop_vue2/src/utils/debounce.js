// 防抖 防止表单重复提交
export const Debounce = (fn, t) => {
  // 定义延迟时间，默认为300毫秒
  let delay = t || 300
  // 定义一个计时器变量
  let timer
  // 返回一个新的函数，这个函数是防抖后的函数
  return function () {
    // 保存当前的函数调用参数
    let args = arguments
    // 如果已有计时器，清除计时器
    if (timer) {
      clearTimeout(timer)
    }

    // 如果没有计时器，表示可以立即调用
    let callNow = !timer

    // 设置一个新的计时器，延迟执行
    timer = setTimeout(() => {
      // 在延迟时间到后，将计时器置为空
      timer = null
    }, delay)

    // 如果可以立即调用，执行函数
    if (callNow) fn.apply(this, args)
  }
}
