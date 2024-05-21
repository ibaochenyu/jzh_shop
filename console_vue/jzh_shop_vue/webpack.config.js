var path = require('path')
var webpack = require('webpack')

module.exports = {
  entry: './src/main.js',//指定 Webpack 打包的入口文件。
  output: {
    path: path.resolve(__dirname, './dist'),//path：打包后文件的输出目录。
    publicPath: '/dist/',//publicPath：静态资源的公共路径。
    filename: 'build.js'//filename：输出文件的名称。
  },
  module: {
    rules: [
      {
        test: /\.css$/,//处理 CSS 文件的规则。
        use: [
          'vue-style-loader',
          'css-loader'
        ],
      },      {
        test: /\.vue$/,//处理 Vue 文件的规则。
        loader: 'vue-loader',
        options: {
          loaders: {
          }
          // other vue-loader options go here
        }
      },
      {
        test: /\.js$/,//处理 JavaScript 文件的规则，并使用 Babel 转译。
        loader: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.(png|jpg|gif|svg)$/,
        loader: 'file-loader',
        options: {
          name: '[name].[ext]?[hash]'
        }
      }
    ]
  },
  resolve: {
    alias: {//alias：为模块设置别名。
      'vue$': 'vue/dist/vue.esm.js'
    },
    extensions: ['*', '.js', '.vue', '.json']//extensions：自动解析这些扩展名的文件。
  },
  devServer: {
    historyApiFallback: true,//historyApiFallback：单页面应用路由。
    noInfo: true,//noInfo：不显示捆绑信息。
    overlay: true//overlay：在浏览器中显示错误和警告的覆盖层。
  },
  performance: {
    hints: false//配置性能提示。
  },
  devtool: '#eval-source-map'
}

if (process.env.NODE_ENV === 'production') {
  module.exports.devtool = '#source-map' //设置 devtool 为 #source-map 以便更好的调试。
  // http://vue-loader.vuejs.org/en/workflow/production.html
  module.exports.plugins = (module.exports.plugins || []).concat([//使用 DefinePlugin 定义环境变量。
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: '"production"'
      }
    }),
    new webpack.optimize.UglifyJsPlugin({//使用 UglifyJsPlugin 压缩 JavaScript。
      sourceMap: true,
      compress: {
        warnings: false
      }
    }),
    new webpack.LoaderOptionsPlugin({//使用 LoaderOptionsPlugin 优化加载选项。
      minimize: true
    })
  ])
}
