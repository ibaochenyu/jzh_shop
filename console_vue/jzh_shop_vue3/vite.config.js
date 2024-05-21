import { fileURLToPath, URL } from 'node:url' //fileURLToPath 和 URL: 将文件 URL 转换为路径，确保路径解析正确，尤其在不同操作系统下。

import { defineConfig } from 'vite' ///defineConfig: 提供类型提示和配置验证，确保配置文件结构正确。
import vue from '@vitejs/plugin-vue'//vue 插件: 让 Vite 支持 Vue 3，处理 .vue 文件，支持热更新等特性。

// https://vitejs.dev/config/
export default defineConfig({//使用 export default defineConfig({ ... }) 语法导出 Vite 的默认配置。
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {//通过设置 @ 指向 ./src，可以在导入模块时使用 @ 代替相对路径，简化模块导入路径的书写。
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
