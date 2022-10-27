### 3-2-2 spring boot项目基本框架

#### 搭建spring boot项目

##### maven安装（略）

##### IDEA安装（略）

##### IDEA全局设置（设置本地maven）

File --> settings --> Build tools --> Maven

##### 创建项目

file --> new --> project --> spring initializer

##### 项目目录结构

main/java：java代码主目录

main/resources：配置文件和页面文件

main/resources/static：静态文件（没有jsp程序处理），如js,html,css等

main/resources/templates：动态文件，如jsp等

test/java：测试代码主目录

标记目录（sources、resources、test）

spring boot启动类：XXXXApplication类，其他代码在该类所在包下

#### Srping boot项目简单开发

##### 开发hello world

- application.properties文件：项目配置

- controller（@RestController）
  - hello world
  - map：前端得到的是map的JSON形式
  - object：
  - 把返回值作为JSON对象传到前端。
- application.yml
  - 效果等同于application.properties
  - 用缩进来代表"."
  - 与application.properties文件配置相冲突时，取properties文件中的值


##### 访问页面

- 编写页面（@Controller）
- 保存
- 页面跳转

##### 知识点

- @RestController和@Controller的区别
  - RestController返回JSON对象
  - Controller返回页面（后缀的html,jsp可以省略）
  - 访问static下的静态页面：直接将static目录当成url的根目录使用
- @RequestMapping（“”）
  - 匹配URL中URI（http://localhost:8080/home/home.html）
    - URL：http://localhost:8080/home/home.html
    - URI：/home/home.html
  - 修饰类和修饰方法的差别
    - 修饰类时，该类下的所有方法的URI都应该加上类修饰符中的前缀

#### web项目MVC模型

M（Model）- V（View） - C（Controller）

Model：业务模型，实际就是实体类（bean/entity/model/vo）

- 私有属性必须小写开头
- 私有属性必须通过公共的getter和setter来读写

View：视图，实际就是页面

- 有动态内容
- 视图的输出结果是一个html文件（由html静态内容和server产生的动态内容拼接而成）

Controller：控制器， 处理页面请求（请求的路由）

- 接收页面请求及参数
- 调用实际service完成业务（controller不实际完成业务）
- 组装执行结果
- 页面跳转

Mapper：持久化接口（数据访问接口）

##### java web目录结构

bean/entity/vo/model：实体类所在包

service：service接口及实现类所在包

controller：控制器（controller）所在包

mapper：mapper接口所在包

dao：访问数据库类文件所在包，现在基本已不使用

/resources/mapper：mapper.xml文件所在位置(SQL语句)

##### web 请求-响应流程

- 页面发起请求：html、js等，分为get和post（put和delete不常用）
- srping框架分发到Controller：根据配置（@RequestMapping）
- 返回结果（无server情况下）
- spring框架将结果包装成JSON或HTML
- 有 server情况下：
  - Controller调用service处理：service的初始化由@AutoWired注解完成
  - service调用Mapper接口完成数据库调用：Mapper由@AutoWired注解完成初始化
  - Mapper实际类：由mybatis框架据xml文件生成于Mapper接口对应的实际类
  - mapper.xml文件：配置实际执行sql语句


##### 注意

-  java bean规范：类属性首字母小写
- AutoWire注解：变量名称和类名称相同（仅首字母小写）
- mapper.xml：
  - 方法名和Mapper中一致
  - 返回值的组装（注意java bean规范）
  - 类和命名空间的包路径
  - resultMap

#### HTTP协议及调试

HyperText Transfer Protocol

给浏览器访问服务器资源的一种方式

文本协议

##### 协议构成

###### head

重要的head

- host：服务端的域名+端口
- Content-Type：plain/text，服务端告诉浏览器当前的资源类型，浏览器可根据资源类型选择渲染的方式
- Content-Length：body长度
- User-Agent：代表浏览器（内核）的种类
- Accept：请求发送方能够处理的资源类型

###### body

- 一般为空，只有在POST请求时才有值。

##### GET和POST方法

GET：获取数据（包体为空），不用于提交数据（提交的数据会显示在URL地址栏，不安全）

POST：提交数据（请求包的包体中有数据）

##### 参数传递

GET：url?参数名1=参数值1&参数名2=参数值2（不安全）

POST：包体中

参数名1=参数值1&参数名2=参数值2

服务端接收：保证参数名称和前端的参数名称一致就可以

