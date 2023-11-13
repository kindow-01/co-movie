<template>
  <div style="background-color: black;height: 100vh;">
    <div class="content">
      <div class="title">
        关于
      </div>
      <div class="duanluo">
        &nbsp;&nbsp; 01年程序员一个
      </div>

    </div>

  </div>
</template>
  
<script>
var html = `
  
<p align="center">
    <img alt="logo" src="https://oscimg.oschina.net/oscnet/up-b99b286755aef70355a7084753f89cdb7c9.png">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">BOBO 视频在线播放系统个人版 v1.0.0</h1>
<h4 align="center">基于 Vue/Springboot 前后端分离架构</h4>

<p align="center">
    <a href="https://gitee.com/y_project/RuoYi-Cloud/stargazers"><img src="https://gitee.com/y_project/RuoYi-Cloud/badge/star.svg?theme=dark"></a>
    <a href="https://gitee.com/y_project/RuoYi-Cloud"><img src="https://img.shields.io/badge/RuoYi-v3.6.2-brightgreen.svg"></a>
    <a href="https://gitee.com/y_project/RuoYi-Cloud/blob/master/LICENSE"><img src="https://img.shields.io/github/license/mashape/apistatus.svg"></a>
</p>

当前最新版本： 0.1.1（发布日期：000000）


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

## 开发环境

- 语言：Java 8

- IDE(JAVA)： Eclipse安装lombok插件 或者 IDEA

- 依赖管理：Maven

- 数据库：MySQL5.7+  &  Oracle 11g & SqlServer & postgresql & 国产等更多数据库

- 缓存：Redis

- node 12.* || 14.*

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
- 个人信息维护  
- 首页视频展示
- 今日热门（当天点击量最多的视频集合）
- 重磅热播（一周内点击量最多的视频集合）
- 为您推荐（算法待定）
- 观看历史（完成）
- 收藏（。。。）
- 精选视频
- 视频详情
- 分享  
- 评论、点赞
- 花絮
- 视频分类检索  
- 搜索(后续再搞)
- 追剧(后续再搞)


## 技术文档

- 微信 ： 15381158567（请注明来意）

## 注意事项
- org.bobo.common.util.bobo.IpUtil中根据ip地址获取归属地（用于评论定位）的方法getAddress()用的是一个网络接口可能会失效

![123](https://gitee.com/yang-ruyuan/video-system/raw/master/mdImg/log.png)


`;


export default {
  data() {
    return {
      showModal: false
    }
  },
  methods: {

  },
  computed: {

  }
}
</script>
  
<style scoped>

.content {
  padding: 1rem 4rem 0 4rem;
}
.title {
    text-align: center;
    font-size: 0.5rem;
    font-weight: bold;
  }
</style>