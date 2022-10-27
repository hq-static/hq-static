### 3-3-1 Backbone.js

#### 官网

backbonejs.org

backbonejs.com.cn

源码（带注释）：backbonjs.com.cn/docs/backbone.html

#### 前端的MV模型

![](img\3-3-1.svg)

##### 模型（Model）

- 负责管理数据和业务逻辑
- 负责和服务端交换数据
- 当数据变化时负责分发事件

##### 视图（View）

- 监听事件以便于修改和渲染UI
- 负责处理用户输入和交互
- 发送输入到模型

#### 代码讲解

addRole.js

- html文件中需要引用sea.js
- js文件中的最后，要seajs.use({当前js文件});
- Home = Backbone.View.extend 表示Home是从View类中继承的对象
- el属性：表示backbone可以操作body中的所有dom元素
- events：注册页面上所需要的事件。
  - 事件类型（click）
  - 绑定事件的DOM元素（.sure-btn）
  - 响应的函数
- initialize：页面加载完成后执行的初始化方法。window.onload
- 其他属性：js中，方法也被认为是属性

menu.js

#### 关于前端技术（个人见解）

- 无需从头编写，在已有代码基础上改
- 无需深入了解所有细节，达到要求即可
- 选定前端框架后，深入学习和研究
- 多读代码（应用代码、框架源码）