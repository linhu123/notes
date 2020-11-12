# 起步
# 使用webpack管理资源文件（图片...）
## 加载 CSS
为了从 JavaScript 模块中 import 一个 CSS 文件,
你需要在 module 配置中 安装并添加 style-loader 和 css-loader：
```
npm install --save-dev style-loader css-loader
```
---
webpack.config.js  add content
webpack 根据正则表达式，来确定应该查找哪些文件，并将其提供给指定的 loader。  
在这种情况下，以 .css 结尾的全部文件，都将被提供给 style-loader  和 css-loader。
```
+   module: {
+     rules: [
+       {
+         test: /\.css$/,
+         use: [
+           'style-loader',
+           'css-loader'
+         ]
+       }
+     ]
+   }
```
## 加载图片
使用 file-loader，我们可以轻松地将这些内容混合到 CSS 中：
```
npm install --save-dev file-loader

+       {
+         test: /\.(png|svg|jpg|gif)$/,
+         use: [
+           'file-loader'
+         ]
+       }

```
## 加载字体
file-loader 和 url-loader 可以接收并加载任何文件，然后将其输出到构建目录。  
这就是说，我们可以将它们用于任何类型的文件，包括字体。  
让我们更新 webpack.config.js 来处理字体文件：
```
+       {
+         test: /\.(woff|woff2|eot|ttf|otf)$/,
+         use: [
+           'file-loader'
+         ]
+       }
```
## 加载数据
略
## 全局资源
略
## 回退处理
略

