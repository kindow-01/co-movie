<p align="center">
    <img width="100px" alt="logo" src="https://gitee.com/yang-ruyuan/video-system-open/raw/master/mdImg/logo-bobo.png">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">BOBO 视频在线播放系统个人版 v1.0.0</h1>
<h4 align="center">基于 Vue/Springboot 前后端分离架构</h4>

<p align="center">
    <a href="https://gitee.com/yang-ruyuan/video-system-open/stargazers"><img src="https://gitee.com/yang-ruyuan/video-system-open/badge/star.svg?theme=dark"></a>
    <a href="https://gitee.com/yang-ruyuan/video-system-open"><img src="https://img.shields.io/badge/BOBO video-v1.0.0-brightgreen.svg"></a>
    <a href="https://gitee.com/yang-ruyuan/video-system-open/blob/master/LICENSE"><img src="https://img.shields.io/badge/license-GPL 3.0-blue"></a>
    <img src="https://img.shields.io/badge/Author-bobo-orange.svg">
    <a href="http://video-admin.yry1.ltd/"><img src="https://img.shields.io/badge/View-admin-blue"></a>
    <a href="http://video.yry1.ltd"><img src="https://img.shields.io/badge/View-user-blue"></a>
</p>



当前最新版本： 1.0.0（发布日期：000000）


## 后端技术架构
- 基础框架：Spring Boot 2.3.5.RELEASE

- 持久层框架：Mybatis-plus 3.4.3.1

- 安全框架：Apache Shiro 1.7.0，Jwt 3.11.0

- 数据库连接池：阿里巴巴Druid 1.1.22

- 缓存框架：redis

- 日志打印：logback

- 其他：fastjson，poi，Swagger-ui，quartz, lombok（简化代码）等。

- 低代码框架：Jeecg-boot 3.1.0

## 前端技术架构
- 基础框架：vue 2.5.2
  
- 其他：vuex ^2.5.0
  
- 视频播放：video.js ^8.3.0
  
- hls解码：videojs-contrib-hls ^5.15.0
  
- 播放器：vue-core-video-player ^0.1.11
  
- 播放器hls插件：@core-player/playcore-hls ^0.1.1
  
- 图片裁剪：vue-advanced-cropper@vue-2

## 目录结构

```
.
├── AdminUi ----管理端源码
│   ├── babel.config.js
│   ├── idea.config.js
│   ├── LICENSE
│   ├── package.json
│   ├── package-lock.json
│   ├── public
│   ├── README.en.md
│   ├── README.md
│   ├── src
│   ├── vue.config.js
│   └── yarn.lock
├── dir.txt
├── ffmpeg-n5.0-3-ga394d35a43-win64-lgpl-shared-5.0.zip ----ffmpeg windows 64位安装包
├── LICENSE
├── mdImg ----放一些md需要的图片 跟项目没有必要关系
│   ├── logo-bobo.png
│   └── log.png
├── offerNetUi ----用户端源码
│   ├── build
│   ├── config
│   ├── index.html
│   ├── package.json
│   ├── package-lock.json
│   ├── README.md
│   ├── src
│   ├── static
│   ├── test
│   └── vue.config.js
├── README.md
└── Server2 ----服务端源码
    ├── bobo-boot-base ----基础工具类
    ├── bobo-boot-module-system ----控制层在这
    ├── bobo-boot-parent.iml
    ├── bobo-dao ----持久层
    ├── bobo-model ----基类
    ├── bobo-server ----服务层
    ├── db ----数据库
    ├── LICENSE
    ├── pom.xml
    ├── README.en.md
    └── README.md
```

## 开发环境

- 语言：Java 8

- IDE(JAVA)： Eclipse安装lombok插件 或者 IDEA

- 依赖管理：Maven

- 数据库：MySQL5.7+

- 缓存：Redis

- node 14.*

- 视频编码分片：ffmpeg-n5.0-3-ga394d35a43-win64-lgpl-shared-5.0（源码中附安装压缩包）


## 功能描述
### 管理端
- 登录注册
- 视频合集管理
- 视频管理
- 视频分类管理
- 分类标签管理
- 视频标签管理
- 图片裁剪
- 花絮管理

### 用户端
- 登录注册
- 个人信息维护  （目前可以修改头像和昵称）
- 首页视频展示
- 今日热门（当天点击量最多的视频集合）
- 重磅热播（一周内点击量最多的视频集合）
- 为您推荐（按照发布时间倒序，没有算法）
- 观看历史（登录后可以记录视频观看历史和进度，并且可以断点续播）
- 收藏（收藏，可以断点续播）
- 精选视频（后台管理设置的精选视频在这里展示）
- 视频详情
- 分享  
- 评论、点赞
- 花絮
- 视频分类检索 
- 搜索(懒得搞了)
- 追剧(跟收藏差不多。。。)

## 快速开始
### 环境准备
- clone代码到本地（尽量避免放在中文路径之下）
- 检查`java`环境，`node`版本，`Redis`，`mysql`，没有的请自行搭建，注意node和mysql版本
- 安装`ffmpeg`，可以参考https://blog.csdn.net/m0_47449768/article/details/130102406
  linux可以参考https://blog.csdn.net/wangyongpro/article/details/104036835
  windows安装包已经在源码中提供，linux需要自行下载
- 导入源码（`Server2\db\video-system-default.sql`）中的DB脚本
- 修改`application-dev.yml`中的`mysql`、`redis`配置为你自己的
### 后端
- 确保环境准备都完成并且没问题，直接使用`idea`安装好`maven`依赖就可以直接运行
### 后台管理和用户端
- `npm i`
- `npm run dev`

### 跑不起來？
- 文末加联系方式，可提供运行教程视频以及更多资源（数据库逻辑结构、ER图、功能详细说明、功能定制化、企业版）

## 注意事项
- org.bobo.common.util.bobo.IpUtil中根据ip地址获取归属地（用于评论定位）的方法getAddress()用的是一个网络接口可能会失效

![123](https://gitee.com/yang-ruyuan/video-system/raw/master/mdImg/logo-bobo.png)

## 其他
- 微信 ： 15381158567（请注明来意）