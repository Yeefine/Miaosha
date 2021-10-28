## Java秒杀系统高性能高并发实战项目

本项目从零实现了一个秒杀系统的一些核心功能。

通过本项目能够学习如何应对大并发、如何利用缓存、如何使用异步、以及如何编写优雅的代码

关于项目的具体实现步骤、实现细节、问题总结，请移步：[详情](https://yeefine.github.io/2021/10/28/Java%E9%AB%98%E6%80%A7%E8%83%BD%E9%AB%98%E5%B9%B6%E5%8F%91%E7%A7%92%E6%9D%80%E7%B3%BB%E7%BB%9F/)

### 技术点介绍

| 前端      | 后端       | 中间件   |
| --------- | ---------- | -------- |
| Thymeleaf | SpringBoot | RabbitMQ |
| Bootstrap | JSR303     | Redis    |
| JQuery    | MyBatis    | Druid    |

---

### 业务逻辑

### ![](https://raw.githubusercontent.com/Yeefine/picBed/master/20211028220310.png)

---

### 项目结构设计及实现功能

#### 1. 项目框架搭建

+ Spring Boot环境搭建

+ 集成Thymeleaf，Result结果封装

+ 集成Mybatis + Druid

+ 集成Jedis + Redis安装 + 通用缓存Key封装（**设计模式中的模板模式**）

#### 2. 实现登录功能

+ 数据库设计

+ 明文密码两次MD5处理

+ JSR303参数检验 + 全局异常处理器

+ 分布式Session（同步不同机器上的Session信息）


#### 3. 实现秒杀功能

+ 数据库设计

+ 商品列表页

+ 商品详情页

+ 订单详情页


#### 4. JMeter压测

+ JMeter入门

+ 自定义变量模拟多用户

+ JMeter命令行使用

+ Spring Boot打war包


#### 5. 页面优化技术

+ 页面缓存 + URL缓存 + 对象缓存（粒度划分不同）

+ 页面静态化，前后端分离

+ 静态资源优化


+ CDN优化，就近访问

#### 6. 接口优化

+ Redis预减库存减少数据库访问

+ 内存标记减少Redis访问

+ RabbitMQ队列缓冲，异步下单，增强用户体验

+ RabbitMQ安装与Spring Boot集成

+ 访问Nginx水平扩展

+ 压测

#### 7. 安全优化

+ 秒杀接口地址隐藏

+ 数学公式验证码

+ 接口限流防刷

---

### 效果展示

1. 登录界面

   ![](https://raw.githubusercontent.com/Yeefine/picBed/master/20211028220426.png)

2. 商品列表页

   ![](https://raw.githubusercontent.com/Yeefine/picBed/master/20211028220500.png)

3. 商品详情页

   ![](https://raw.githubusercontent.com/Yeefine/picBed/master/20211028220536.png)

4. 订单详情页

   ![](https://raw.githubusercontent.com/Yeefine/picBed/master/20211028220559.png)