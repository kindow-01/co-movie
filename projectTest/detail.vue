<template>
  <div class="main" ref="scrollWrapper" @scroll="handleScroll">

    <div>
      <topBar></topBar>
    </div>

    <div class="body">
<!-- 不懂    -->
      <div>
        <!-- 当前页面的URL -->
        <share @closeShare="shareShow = false;" :url="SHARE_URL + '#/detail?setId=' + setId + '&videoId=' + routeVideoId"
          :show="shareShow"></share>
      </div>
      <div class="play">
        <div class="playleft">
          <div class="videoDiv">
            <!-- <video muted loop="loop" autoplay="autoplay" width="100%"
                src="d=17&ext=.mp4"
                controls="controls"></video> -->

<!--            <vue-core-video-player style="height:6.8rem" id="boboVideo" @loadeddata="loadeddata" @timeupdate="timeupdate"-->
<!--              v-if="videoShow" :core="HLSCore" :src="getPlayUrl()" :title="videoSet.setName + ' ' + checkVideo.name"-->
<!--              autoplay />-->
                <vue-core-video-player id="vue-player"  :src="videoSource" :cover="cover_1" :title="title_1"  autoplay loop="true"/>
<!--            <div id="player">-->
<!--              <video>-->
<!--                <source src="v1.mp4" type="video/mp4">-->
<!--              </video>-->
<!--            </div>-->

          </div>
          <div class="videoBottom">
            <div class="videoBottomAction">
              <div class="actionButtonRow" v-if="videoType == 1">
<!--                点击收藏或者取消收藏-->
                <div @click=" saveOrDelCollect()" class="share videoActionButton"
                  :style="isCollect ? ' color: #00c8c8;' : ''">
                  <i class="el-icon-star-off"></i>
                  <div v-if="isCollect">已收藏</div>
                  <div v-else>收藏</div>
                </div>
<!--                点击显示分享二维码-->
                <div @click=" doShareShow()" class="share videoActionButton">
                  <i class="el-icon-share"></i>
                  <div>分享</div>
                </div>
<!--                下载路径为空-->
                <div class="share videoActionButton">
                  <i class="el-icon-download"></i>
                  <div>下载</div>
                </div>
              </div>
<!--              评论区-->
              <div class="commentDiv g_row_and_center" style="align-items: center;">
                <input class="commentInput1" v-model="commentText" placeholder="评论一下吧">
                <div class="commentButton" @click=" parentComment(commentText)">评论</div>
              </div>
            </div>
<!--            视频信息-->
            <div class="episodeTitle">
              {{ videoSet.setName }} {{ checkVideo.name }}
            </div>
            <div class="tagDiv">
              <div class="episodeTag" v-for="(tag, index) in videoSet.tags.split(',')">
                {{ tag }}
              </div>
            </div>
          </div>
        </div>
<!--        视频右侧导航栏-->
        <div class="playright">
          <!--        上半部分-->
          <div class="padding20">
            <div class="videoTitle">{{ videoSet.setName }}</div>
            <div class="introduction">
              {{ videoSet.setIntroductionLong }}
            </div>
<!--            下半部分-->
            <div class="episode">
              <div class="episodeType" @click=" changeEpisodeType('1')"
                :style="checkdEpisodeType == '1' ? 'color: #ff6022;background-color:#cdcdcd69' : ''">
<!--    #ff6022            #cdcdcd69-->
                剧集与更新
                <div v-if="checkdEpisodeType == '1'" class="sold">
                  <div class="episodesBox">
                    <div class="episodeItem" videoCheck :style="videoCheck == index ? 'color: #ff6022;background-color:#cdcdcd69' : ''"
                         @click=" clickVideoItem(videoItem, index, true)" v-for="(videoItem, index) in videoList">
                      {{ index + 1 }}
                    </div>
                  </div>
                </div>
              </div>

              <div class="episodeType" @click=" changeEpisodeType('2')"
                :style="checkdEpisodeType == '2' ? 'color: #ff6022;' : ''">
                为您推荐
                <div v-if="checkdEpisodeType == '2'" class="sold">
                  <div class="titbitsDiv">
<!--                    <div>花絮资讯（{{ gagsList ? gagsList.length : 0 }}）</div>-->
                    <div style="overflow-y:auto;height: 5.3rem; width: 3.5rem;">
                      <div class="titbitsRow" v-for="(item, index) in gagsList" :key="index" @click=" clickGagsItem(item, true, index)">
                        <div class="titbitsImg" :style="'background-size: cover;background-image: url(' + item.coverLevel + ');'"></div>
                        <div class="titbitsContent" :style="gagsCheck == index ? 'color: #ff6022;' : ''"> <span>{{ item.name }}</span></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
<!--                        剧集按钮-->
<!--            <div style="overflow-y:auto;height: 5.3rem;">-->
<!--              <div class="episodesBox">-->
<!--                <div class="episodeItem" videoCheck :style="videoCheck == index ? 'color: #ff6022;' : ''"-->
<!--                     @click=" clickVideoItem(videoItem, index, true)" v-for="(videoItem, index) in videoList">-->
<!--                  {{ index + 1 }}-->
<!--                </div>-->
<!--              </div>-->
<!--              <div class="titbitsDiv">-->
<!--                <div>花絮资讯（{{ gagsList ? gagsList.length : 0 }}）</div>-->
<!--                <div class="titbitsRow" v-for="(item, index) in gagsList" :key="index" @click=" clickGagsItem(item, true, index)">-->
<!--                  <div class="titbitsImg" :style="'background-size: cover;background-image: url(' + item.coverLevel + ');'"></div>-->
<!--                  <div class="titbitsContent" :style="gagsCheck == index ? 'color: #ff6022;' : ''"> {{ item.name }}</div>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->


          </div>
        </div>
      </div>


      <div class="foot" v-if="true">
        <div class="footBody">
          <div class="footLeft">
            <!-- 评论区头部 -->
            <div class="commentTitle">
              <div class="commentTitleBig">
                讨论
              </div>
              <div class="commentTitleSm">
                {{ commentTotal }}
              </div>
            </div>
            <!-- 评论区输入框 -->
            <div class="avatarAndCommentInput">
              <img type="avatar" v-if="user && user.avatar" :src="user.avatar" dt-eid="avatar"
                dt-params="father_id=host&amp;mod_id=dissc_area&amp;sub_tab_id=hot" dt-clck-ignore="true"
                class="at-feed-stream-post-avatar">
              <div style="width: 100%;position: relative;">
                <div v-if="!user || !user.id" dt-eid="btn"
                  dt-params="item_id=log_in&amp;mod_id=dissc_area&amp;sub_tab_id=hot"
                  class="at-feed-stream-post__login-btn"><span>登录后可发言</span></div>
                <commentBox @clickComment="comment2" v-if="commentBoxShow"></commentBox>
                <input v-else class="commentInput2" v-model="commentText" placeholder="评论一下吧"
                  @focus=" commentBoxShow = true" />

              </div>
            </div>
            <!-- 评论区标签筛选 -->
            <div class="commentTags">
              <div class="commentTag " :class="commentTag == '热门' ? 'commentTagClick' : ''"
                @click=" changeCommentTag('热门')">
                热门
              </div>
              <div class="commentTag" :class="commentTag == '最新' ? 'commentTagClick' : ''"
                @click=" changeCommentTag('最新')">
                最新
              </div>
            </div>
            <!-- 评论块 -->
            <div class="commentDiv">
              <!-- 主评论 -->
              <div class="commentParent"
                v-for="(comment, index) in commentParentList" :key="index">
                <!-- 主评论头部  头像 用户名 时间地址等在一行 -->
                <div class="commentParentHeader">
                  <!-- 头像 -->
                  <div>
                    <img v-if="comment.commentWho && comment.commentWho.avatar" type="avatar"
                      :src="comment.commentWho.avatar" dt-eid="avatar"
                      dt-params="father_id=host&amp;mod_id=dissc_area&amp;sub_tab_id=hot" dt-clck-ignore="true"
                      class="at-feed-stream-post-avatar">
                    <img v-else type="avatar" src="../../assets/logo.png" dt-eid="avatar"
                      dt-params="father_id=host&amp;mod_id=dissc_area&amp;sub_tab_id=hot" dt-clck-ignore="true"
                      class="at-feed-stream-post-avatar">
                  </div>
                  <div class="userNameParent">{{ comment.commentWho ? comment.commentWho.realName : '用户已注销' }}</div>
                  <div class="commentTime">{{ formatDate(Date.parse(comment.createTime)) }}-{{ comment.address }}</div>
                </div>
                <!-- 评论内容 -->
                <div class="commentContentParent">
                  {{ comment.content }}
                </div>
                <!-- 评论操作按钮 点赞 回复评论 收藏 -->
                <div class="commentAction">
<!--                   <span class="iconfont iconfont-xihuan1" style="font-size: 0.25rem;color: red;"></span>-->
                  <span class="iconfont iconfont-xihuan2" @click=" like(comment.id, index)"
                    style="font-size: 0.25rem;font-weight: bolder;padding-right: 0.2rem;  cursor: pointer;"
                    :style="comment.myLike ? 'color:red;' : ''"><span
                      style="font-size: 0.15rem;">{{ comment.likeNum }}</span></span>
                  <span class="iconfont iconfont-huifu" @click=" off_on_commentBox(comment, [index])"
                    style="font-size: 0.25rem;font-weight: bolder;  cursor: pointer;"><span style="font-size: 0.15rem;">{{ comment.replyCount }}</span></span>
                </div>

                <commentBox @clickComment="clickComment" v-if="comment.openCommentBox"></commentBox>
                <!-- 子评论 -->
                <div class="commentSon" v-for="(sonComment, sonIndex) in comment.sonList" :key="sonIndex">
                  <div>
                    <!-- 子评论头部 -->
                    <div class="commentSonHeader">

                      <div class="userNameSon" v-if="sonComment.parentId != sonComment.reply && sonComment.replyWho">
                        {{ sonComment.commentWho ? sonComment.commentWho.realName : '用户已注销' }}<span
                          style="color: chocolate;">{{ ' 回复 ' }}</span>{{ sonComment.replyWho.realName }}
                      </div>
                      <div class="userNameSon" v-else>
                        {{ sonComment.commentWho ? sonComment.commentWho.realName : '用户已注销' }}
                      </div>
                      <div class="commentTime">
                        {{ formatDate(Date.parse(sonComment.createTime)) }}-{{ sonComment.address }}
                      </div>
                    </div>
                    <div class="commentContentSon">
                      {{ sonComment.content }}
                    </div>
                    <div class="commentAction">
                      <!-- <span class="iconfont iconfont-xihuan1" style="font-size: 0.25rem;color: red;"></span> -->
                      <span class="iconfont iconfont-xihuan2" @click=" likeSon(sonComment.id, index, sonIndex)"
                        style="font-size: 0.25rem;font-weight: bolder;padding-right: 0.2rem;cursor: pointer;"
                        :style="sonComment.myLike ? 'color:red;' : ''"><span
                          style="font-size: 0.15rem;">{{ sonComment.likeNum }}</span></span>
                      <span class="iconfont iconfont-huifu" @click=" off_on_commentBox(sonComment, [index, sonIndex])"
                        style="font-size: 0.25rem;font-weight: bolder;cursor: pointer;"><span
                          style="font-size: 0.15rem;">{{ sonComment.replyCount }}</span></span>
                    </div>
                    <commentBox @clickComment="clickComment" v-if="sonComment.openCommentBox"></commentBox>
                  </div>
                </div>
                <div v-if="comment.replyCount - (comment.sonList ? comment.sonList.length : 0) > 0" class="commentSon"
                  style="cursor: pointer;" @click=" querySonComment(comment.id, index)">
                  查看更多评论（{{ comment.replyCount - (comment.sonList ? comment.sonList.length : 0) }}条）
                  <i class="el-icon-arrow-down"></i>
                </div>
              </div>
            </div>
          </div>
          <div class="footRight">
            这里放节目推荐
          </div>
        </div>
      </div>

    </div>
    <loadData ref="loading"></loadData>
  </div>
</template>

<script>
// https://api.dogecloud.com/player/get.mp4?vcode=5ac682e6f8231991&userI
import topBar from "../../components/topBar/topBar.vue";
import {
  SHARE_URL
} from "../../util/constants";
import commentBox from "../../components/commentBox.vue";
import leftBar from "../../components/leftBar/leftBar.vue";
import videos5 from "../../components/videos5.vue";
import share from "../../components/share.vue";
import HLSCore from "@core-player/playcore-hls"
import api from "../../util/api"
import { mapGetters, mapActions } from 'vuex'
export default {
  components: {
    //用来注册子组件的节点
    topBar,
    leftBar,
    videos5,
    share,
    commentBox
  },
  name: "list",
  data() {
    return {

      videoSource: [{
        src: 'https://media.vued.vanthink.cn/sparkle_your_name_am360p.mp4',
        // src: 'http://101.42.165.6:8888/group1/M00/00/00/ZSqlBmVLkASAbmkAAH7RaF6YBLM716.mp4',
        resolution: 360,
      }, {
        src: 'https://media.vued.vanthink.cn/sparkle_your_name_am720p.mp4',
        resolution: 720,
      }, {
        src: 'https://media.vued.vanthink.cn/y2mate.com%20-%20sparkle_your_name_amv_K_7To_y9IAM_1080p.mp4',
        resolution: 1080
      }],
      cover_1:"https://img1.wxzxzj.com/vpc-example-cover-your-name-c.png",
      title_1:"你的名字",


      HLSCore,
      SHARE_URL,
      checkdEpisodeType: "1",
      barName: "",
      defaultTag: "",
      commentTag: "热门",
      setId: null,

      commentText: "",
      videoList: [
        {
          id: 1,
          cover:
            "https://puui.qpic.cn/vcover_hz_pic/0/mzc00200c2y0bad1675690775371/332?max_age=7776001",
          title: "听说你喜欢我",
          describe: "彭冠英王楚然先婚后爱",
        },
        {
          id: 2,
          cover:
            "https://puui.qpic.cn/vcover_hz_pic/0/mzc002007knmh3g1673530815496/332?max_age=7776001",
          title: "三体",
          describe: "中国科幻巅峰IP",
        },
        {
          id: 3,
          cover:
            "https://puui.qpic.cn/vcover_hz_pic/0/mzc002006sce2l41669280941978/332?max_age=7776001",
          title: "迷寻",
          describe: "一场尘封十年的拐卖恩怨",
        },
        {
          id: 4,
          cover:
            "https://puui.qpic.cn/vcover_hz_pic/0/wf6dzcsfe81z03i1669879665110/332?max_age=7776001",
          title: "原始求生记 第3季",
          describe: "硬核！零装备挑战野外生存",
        },
        {
          id: 5,
          cover:
            "https://puui.qpic.cn/vcover_hz_pic/0/mzc00200c2y0bad1675690775371/332?max_age=7776001",
          title: "听说你喜欢我",
          describe: "彭冠英王楚然先婚后爱",
        },
        {
          id: 6,
          cover:
            "https://puui.qpic.cn/vcover_hz_pic/0/mzc002007knmh3g1673530815496/332?max_age=7776001",
          title: "三体",
          describe: "中国科幻巅峰IP",
        },
        {
          id: 7,
          cover:
            "https://puui.qpic.cn/vcover_hz_pic/0/mzc002006sce2l41669280941978/332?max_age=7776001",
          title: "迷寻",
          describe: "一场尘封十年的拐卖恩怨",
        },
        {
          id: 8,
          cover:
            "https://puui.qpic.cn/vcover_hz_pic/0/wf6dzcsfe81z03i1669879665110/332?max_age=7776001",
          title: "原始求生记 第3季",
          describe: "硬核！零装备挑战野外生存",
        },
      ],
      gagsList: [
        {
          id: 1,
          coverLevel: "https://puui.qpic.cn/vcover_hz_pic/0/wf6dzcsfe81z03i1669879665110/332?max_age=7776001",
          name: "原始求生记 第3季"
        },
        {
          id: 2,
          coverLevel: "https://puui.qpic.cn/vcover_hz_pic/0/mzc002006sce2l41669280941978/332?max_age=7776001",
          name: "迷寻"
        },
        {
          id: 3,
          coverLevel: "https://puui.qpic.cn/vcover_hz_pic/0/mzc002007knmh3g1673530815496/332?max_age=7776001",
          name: "三体"
        },
        {
          id: 4,
          coverLevel: "https://puui.qpic.cn/vcover_hz_pic/0/mzc00200c2y0bad1675690775371/332?max_age=7776001",
          name: "听说你喜欢我"
        },
        {
          id: 5,
          coverLevel: "https://puui.qpic.cn/vcover_hz_pic/0/wf6dzcsfe81z03i1669879665110/332?max_age=7776001",
          name: "原始求生记 第3季"
        },
        {
          id: 6,
          coverLevel: "https://puui.qpic.cn/vcover_hz_pic/0/mzc002006sce2l41669280941978/332?max_age=7776001",
          name: "迷寻"
        },
        {
          id: 7,
          coverLevel: "https://puui.qpic.cn/vcover_hz_pic/0/mzc002007knmh3g1673530815496/332?max_age=7776001",
          name: "三体"
        },
        {
          id: 8,
          coverLevel: "https://puui.qpic.cn/vcover_hz_pic/0/mzc00200c2y0bad1675690775371/332?max_age=7776001",
          name: "听说你喜欢我"
        },
      ],
      videoCheck: 0,
      videoType: 1,
      gagsCheck: -1,
      videoShow: true,
      checkVideo: {
        name: '名字2',
        // playUrl: ''
      },
      videoSet: {
        setName: '阿凡达',
        tags: "动作,奇幻", //英文逗号分隔
        setIntroductionLong: '战斗中负伤而下身瘫痪的前海军战士杰克•萨利决定替死去的同胞哥哥来到潘多拉星操纵格蕾丝博士用人类基因与当地纳美部族基因结合创造出的 “阿凡达” 混血生物。杰克的目的是打入纳美部落，外交说服他们自愿离开世代居住的家园，从而SecFor公司可砍伐殆尽该地区的原始森林，开采地下昂贵的“不可得”矿。在探索潘多拉星的过程中，杰克遇到了纳美部落的公主娜蒂瑞，向她学习了纳美人的生存技能与对待自然的态度。与此同时，SecFor公司的经理和军方代表上校迈尔斯逐渐丧失耐心，决定诉诸武力驱赶纳美人。',
      },
      timer: {},//触底节流
      currenttime: 0,
      parentCommentLoding: false,
      commentBoxShow: false,//控制发表评论的显示
      commentParentList: [
        {
          id:1,
          commentWho:{
            avatar: "https://puui.qpic.cn/vcover_hz_pic/0/mzc00200c2y0bad1675690775371/332?max_age=7776001",
            realName: '123456'
          },
          createTime: "2023-10-09",
          address: "中国",
          content: "真好看",
          myLike:false,
          likeNum:10,
          replyCount: 2,
          openCommentBox: false,
          sonList:[
            {
              id:1,
              parentId: 1,
              reply: 1,
              replyWho: {
                realName:"123456",
              },
              commentWho: {
                realName: "23232323",
              },
              createTime:"2023-10-09",
              address: "中国",
              content:"111111",
              myLike: true,
              likeNum: 3,
              replyCount:1,
              openCommentBox: false,
            },
            // {
            //   id:2,
            //   parentId: 1,
            //   reply: 1,
            //   replyWho: {
            //     realName:"123456",
            //   },
            //   commentWho: {
            //     realName: "666666",
            //   },
            //   createTime:"2023-10-09",
            //   address: "中国",
            //   content:"11333331",
            //   myLike: true,
            //   likeNum: 3,
            //   replyCount:1,
            //   // openCommentBox: "",
            // },
          ],
        }

      ],//评论列表
      commentTotal: 1,//评论总数
      pageSizeTop: 10,//父评论分页
      pageNoTop: 1,//父评论分页
      clickCommentObj: {},//选中了哪个评论
      clickCommentIndex: [],//选中了哪个评论的下标，数组长度为二表示二级评论的下标
      shareShow: false,
      playerOptions: {
        language: 'zh-CN',  // 语言
        playbackRates: [0.5, 1.0, 1.5, 2.0],  // 可选的播放速度
        sources: [{
          type: "application/x-mpegURL",   // 类型
          withCredentials: false,
          src: 'http://cctvalih5ca.v.myalicdn.com/live/cctv1_2/index.m3u8' // 中央1 M3U8连接
        }],
        techOrder: ['html5'],
        flash: { hls: { withCredentials: false } },
        html5: { hls: { withCredentials: false } },
        autoplay: true,  // 是否自动播放
        controls: true,  // 是否显示控制栏
        notSupportedMessage: '无信号',
        muted: true, // 是否静音
      },
      isCollect: false //视频是否已经被收藏
    };
  },
  created() {
    // this.defaultTag = this.$route.query.tag;
    // this.barName = this.$route.query.barName;
    this.setId = this.$route.query.setId;
    this.routeVideoId = this.$route.query.videoId;
    console.log(this.setId, this.routeVideoId);
    if (this.setId) {
      this.init();
    }
  },
  methods: {
    init() {
      this.initVideoSet();//获取合集详情
      this.initVideoList();//初始化视频列表
      this.initGagsList();//初始化花絮列表
      this.videoSetAddClick();//增加点击量
    },
    videoSetAddClick() {
      let param={
        videoSetId:this.setId
      }
      api.videoSetAddClick(param).then((res) => {
        if (res.code == 200) {

        }
      });
    },
    timeupdate(e) {
      this.currenttime = e.target.currentTime;
    },
    loadeddata() {

    },
    playGags(gags) {
      console.log(gags, "gags");
    },
    doRecordHistory() {
      //不是合集序列视频不记录观看历史

      if (this.videoType != 1) {
        return;
      }
      if (!this.checkVideo.id) {
        return;
      }
      let param = {
        videoId: this.checkVideo.id,
        time: this.currenttime,
        notOpenLoginBox: true
      }
      //返回观看历史
      api.recordHistory(param).then((res) => {
        if (res.code == 200) {

        }
      });
    },
    historyQueryTime() {
      let param = {
        videoId: this.checkVideo.id,
        notOpenLoginBox: true
      }
      api.historyQueryTime(param).then((res) => {
        if (res.code == 200) {
          this.historyTime = res.result;
          this.setCurrenttime(this.historyTime);
        }
      });
    },
    setCurrenttime(currentTime) {
      let dom = document.getElementById("boboVideo");
      dom.children[0].currentTime = currentTime;
    },
    saveOrDelCollect() {
      let param = {
        videoId: this.checkVideo.id,
        time: this.currenttime,
      }
      this.$refs.loading.startLoading();
      //反馈收藏或者取消收藏
      api.saveOrDelCollect(param).then((res) => {
        this.isCollect = !this.isCollect;
        this.$refs.loading.stopLoading();
      }).catch((err) => {
        this.$refs.loading.stopLoading();
      })
    },
    initVideoSet() {
      let param = {
        id: this.setId,
      }
      api.getVideoSetById(param).then((res) => {
        if (res.code == 200) {
          this.videoSet = res.result;
        }
      });
    },
    initVideoList() {
      let param = {
        videoSetId: this.setId,
      }
      api.queryVideoList(param).then((res) => {
        if (res.code == 200) {
          this.videoList = res.result;
          //如果有返回具体视频列表就默认选中第一个
          if (this.videoList && this.videoList.length > 0) {

            if (this.routeVideoId) {//如果选择了合集内哪个视频就播放哪个，否则就默认第一个
              let i = -1;
              let b = 0;
              this.videoList.forEach(element => {
                i++;
                if (element.id == this.routeVideoId) {
                  this.clickVideoItem(element, i, false);
                  b = 1;
                }
              });
              // i等于0说明没找到这个视频，默认从第一个开始播放
              if (b == 0) {
                this.clickVideoItem(this.videoList[0], 0, false);

              }
            } else {
              this.clickVideoItem(this.videoList[0], 0, false);
            }
          }
        }
      });
    },
    initGagsList() {
      let param = {
        videoSetId: this.setId
      }
      api.gagsList(param).then((res) => {
        this.gagsList = res.result.records;
      }).catch((err) => {

      })
    },

    //查看当前选中的video是否收藏
    initIsCollect() {
      let param = {
        videoId: this.checkVideo.id,
        notOpenLoginBox: true
      }
      api.checkCollect(param).then((res) => {
        if (res.code == 200) {
          this.isCollect = res.result;
        }
      }).catch((err) => {
        this.isCollect = false;
      })
    },
    clickVideoItem(item, index, doRecordHistory) {
      //清空评论列表
      this.commentParentList = [];
      if (doRecordHistory) {
        this.doRecordHistory();
      }
      this.videoType = 1;
      this.videoShow = false;
      //当前播放视频的id
      this.routeVideoId = item.id;

      this.videoCheck = index;
      this.gagsCheck = -1;
      this.checkVideo = item;
      this.queryComment();
      this.queryCommentTotal();
      //查询是否收藏
      this.initIsCollect();
      this.$nextTick(() => {
        this.videoShow = true;
        this.historyQueryTime();
      });
    },
    clickGagsItem(gags, doRecordHistory, index) {
      //清空评论列表
      this.commentParentList = [];
      if (doRecordHistory) {
        this.doRecordHistory();
      }
      this.videoType = 2;
      this.videoShow = false;

      this.gagsCheck = index;
      this.videoCheck = -1;
      this.checkVideo = gags;
      this.queryComment();
      this.queryCommentTotal();
      this.$nextTick(() => {
        this.videoShow = true;
      });
    },
    getPlayUrl() {
      if (this.checkVideo) {
        return this.checkVideo.playUrl;
      } else {
        return '';
      }
    },

    comment(param) {
      api.addComment(param).then((res) => {
        if (res.code == 200) {
          this.$message.success("评论成功");
          this.commentParentList.unshift(res.result);
          this.commentTotal++;
        } else {
          this.$message.error("操作失败");
        }
      }).catch((err) => {

      })
    },
    //父评论，也就是最顶级的评论，接下去还会有二级评论
    parentComment(content) {
      let param = {
        sourceId: this.checkVideo.id,//视频id
        content: content,//评论文本
        type: this.videoType == 1 ? 'video' : 'gags',//判断是花絮评论还是视频评论
      }
      this.comment(param)
    },
    comment2(comment) {
      this.parentComment(comment);
    },
    queryComment() {
      if (!this.checkVideo.id) {
        return;
      }
      this.parentCommentLoding = true;
      let param = {
        sourceId: this.checkVideo.id,//视频id
        type: this.videoType == 1 ? 'video' : 'gags',//判断是花絮评论还是视频评论
        pageNo: this.pageNoTop,
        pageSize: this.pageSizeTop,
        sort: this.commentTag
      }
      if (this.user && this.user.id) {//说明用户已经登录
        api.commentList(param).then((res) => {
          this.parentCommentLoding = false;
          if (res.code == 200) {
            if (res.result.records.length > 0) {
              this.commentParentList = this.commentParentList.concat(res.result.records)
            } else {
              this.pageNoTop = this.pageNoTop - 1;
            }
          }
        }).catch((err) => {

        })
      } else {//未登录
        api.commentListUnAuth(param).then((res) => {
          this.parentCommentLoding = false;
          if (res.code == 200) {
            if (res.result.records.length > 0) {
              this.commentParentList = this.commentParentList.concat(res.result.records)


            } else {
              this.pageNoTop = this.pageNoTop - 1;
            }
          }
        }).catch((err) => {

        })
      }

    },
    queryCommentTotal() {
      let param = {
        sourceId: this.checkVideo.id,//视频id
        type: this.videoType == 1 ? 'video' : 'gags',//判断是花絮评论还是视频评论
      }
      api.commentTotal(param).then((res) => {
        if (res.code == 200) {
          this.commentTotal = res.result;
        }
      })
    },
    //加载子评论
    querySonComment(parentId, index) {
      //对父列表中的分页参数作出处理
      if (this.commentParentList[index].pageNo) {
        this.commentParentList[index].pageNo = this.commentParentList[index].pageNo + 1;
      } else {
        this.commentParentList[index].pageNo = 1;
      }
      let param = {
        sourceId: this.checkVideo.id,//视频id
        type: this.videoType == 1 ? 'video' : 'gags',//判断是花絮评论还是视频评论
        pageNo: this.commentParentList[index].pageNo,
        parentId: parentId,
        pageSize: 10,
        sort: this.commentTag
      }
      if (this.user && this.user.id) {//说明用户已经登录
        api.commentList(param).then((res) => {
          if (res.code == 200) {
            if (res.result.records.length > 0) {
              //避免父列表中最开始sonList是没有的 concat会报错  所以没有就先初始化一个
              if (!this.commentParentList[index].sonList) {
                this.commentParentList[index].sonList = [];
              }
              let tmp = this.commentParentList[index].sonList.concat(res.result.records);

              const filteredArr = tmp.filter((item, index, self) => {
                return index === self.findIndex((t) => (
                  t.id === item.id
                ))
              })
              this.commentParentList[index].sonList = filteredArr;

            } else {
              //没东西了回退一下分页
              this.commentParentList[index].pageNo = this.commentParentList[index].pageNo - 1;
            }
            this.$forceUpdate();
          }
        }).catch((err) => {

        })
      } else {//未登录
        api.commentListUnAuth(param).then((res) => {
          if (res.code == 200) {
            if (res.result.records.length > 0) {
              //避免父列表中最开始sonList是没有的 concat会报错  所以没有就先初始化一个
              if (!this.commentParentList[index].sonList) {
                this.commentParentList[index].sonList = [];
              }
              this.commentParentList[index].sonList = this.commentParentList[index].sonList.concat(res.result.records);
            } else {
              //没东西了回退一下分页
              this.commentParentList[index].pageNo = this.commentParentList[index].pageNo - 1;
            }
            this.$forceUpdate();
          }
        }).catch((err) => {

        })
      }
    },
    changeCommentTag(e) {
      console.log(e, 'eeeeeeee');
      this.commentTag = e;
      this.refreshComment();
    },
    refreshComment() {
      this.commentParentList = [];
      this.pageNoTop = 1;
      this.pageSizeTop = 10;
      this.clickCommentObj = {};//选中了哪个评论
      this.clickCommentIndex = [];//选中了哪个评论的下标，数组长度为二表示二级评论的下标

      this.queryComment();
    },
    //点赞父评论
    like(commentId, index) {
      api.commentLike({ commentId }).then((res) => {
        if (res.code == 200) {
          if (this.commentParentList[index].myLike) {
            this.commentParentList[index].likeNum = this.commentParentList[index].likeNum - 1;
          } else {
            this.commentParentList[index].likeNum = this.commentParentList[index].likeNum + 1;

          }
          this.commentParentList[index].myLike = !this.commentParentList[index].myLike;
          console.log(this.commentParentList[index].myLike);
        }
      }).catch((err) => {

      })

    },
    //点赞子评论
    likeSon(commentId, index, sonIndex) {
      api.commentLike({ commentId }).then((res) => {
        if (res.code == 200) {
          console.log("this.commentParentList[index][sonIndex]", this.commentParentList[index].sonList[sonIndex]);
          if (this.commentParentList[index].sonList[sonIndex].myLike) {
            this.commentParentList[index].sonList[sonIndex].likeNum = this.commentParentList[index].sonList[sonIndex].likeNum - 1;
          } else {
            this.commentParentList[index].sonList[sonIndex].likeNum = this.commentParentList[index].sonList[sonIndex].likeNum + 1;

          }
          this.commentParentList[index].sonList[sonIndex].myLike = !this.commentParentList[index].sonList[sonIndex].myLike;
          this.$forceUpdate();
        }
      })

    },
    //打开或者关闭评论窗口 index:[1]   或者 index:[1,2]
    off_on_commentBox(comment, index) {
      //关闭原来打开的盒子
      if (this.clickCommentIndex.length > 0) {
        if (this.clickCommentIndex.length == 2) {//二级评论
          this.commentParentList[this.clickCommentIndex[0]].sonList[this.clickCommentIndex[1]].openCommentBox = false;
        } else if (this.clickCommentIndex.length == 1) {//一级评论{
          this.commentParentList[this.clickCommentIndex[0]].openCommentBox = false;
        }
      }
      this.clickCommentIndex = index;
      if (index.length == 2) {//二级评论
        if (this.clickCommentObj && this.clickCommentObj.id == comment.id) {//重复点击说明关闭评论盒子
          this.commentParentList[index[0]].sonList[index[1]].openCommentBox = false;
          this.clickCommentObj = {};
        } else {//否则打开新的评论盒子
          this.clickCommentObj = comment;
          this.commentParentList[index[0]].sonList[index[1]].openCommentBox = true;
        }
      } else if (index.length == 1) {//一级评论
        if (this.clickCommentObj && this.clickCommentObj.id == comment.id) {//重复点击说明关闭评论盒子
          this.commentParentList[index[0]].openCommentBox = false;
          this.clickCommentObj = {};
        } else {//否则打开新的评论盒子
          this.clickCommentObj = comment;
          this.commentParentList[index[0]].openCommentBox = true;
        }
      } else {
        return;
      }

      this.$forceUpdate();
    },
    //评论组件 点击评论按钮了
    clickComment(comment) {
      console.log(comment, "comment");
      let index = this.clickCommentIndex;
      let param = {
        sourceId: this.checkVideo.id,//视频id
        content: comment,//评论文本
        type: this.videoType == 1 ? 'video' : 'gags',//判断是花絮评论还是视频评论
        //如果parentId存在就说明是回复二级评论，如果不存在就说明是回复一级评论
        //回复二级评论的parentId跟被回复的是一样的，回复一级评论的parentId跟被回复的id是一样的
        parentId: this.clickCommentObj.parentId ? this.clickCommentObj.parentId : this.clickCommentObj.id,
        //如果是回復二級的，需要记录回复的是哪条二级评论
        reply: this.clickCommentObj.id

      }
      api.addComment(param).then((res) => {
        if (res.code == 200) {

          this.$message.success("评论成功");
          this.commentTotal++;
          if (index.length == 2) {//回复二级评论
            this.commentParentList[index[0]].sonList[index[1]].replyCount = this.commentParentList[index[0]].sonList[index[1]].replyCount + 1;//递增回复数
            this.commentParentList[index[0]].replyCount = this.commentParentList[index[0]].replyCount + 1;//一级评论递增回复数
            this.commentParentList[index[0]].sonList[index[1]].openCommentBox = false;//关闭评论窗口
            this.commentParentList[index[0]].sonList.unshift(res.result);//及时回显
            this.clickCommentObj = {};
            this.$forceUpdate();

          } else if (index.length == 1) {//回复一级评论

            this.commentParentList[index[0]].replyCount = this.commentParentList[index[0]].replyCount + 1;//递增回复数
            this.commentParentList[index[0]].openCommentBox = false;//关闭评论窗口
            if (!this.commentParentList[index[0]].sonList) {
              this.commentParentList[index[0]].sonList = [];
            }
            this.commentParentList[index[0]].sonList.unshift(res.result);//及时回显
            this.clickCommentObj = {};
            this.$forceUpdate();
          }
        } else {
          this.$message.error("操作失败");
        }
      }).catch((err) => {

      })
    },
    changeEpisodeType(num) {
      this.checkdEpisodeType = num;
    },
    doShareShow() {
      this.shareShow = true;
    },
    scrollBottom() {

      // debugger;
      // 变量scrollTop为当前页面的滚动条纵坐标位置
      let scrollTop =
        document.documentElement.scrollTop || document.body.scrollTop;
      // 变量 windowHeight 是可视区的高度
      let windowHeight =
        document.documentElement.clientHeight || document.body.clientHeight;
      //如果向下滚动超过过100 则给头部导航换个颜色
      if (scrollTop >= 100) {
        this.$store.dispatch('setTopBarBackground', 'rgba(45, 45, 45, 0.826)');
      } else {
        this.$store.dispatch('setTopBarBackground', '');
      }
      // 变量 scrollHeight 是滚动条的总高度
      let scrollHeight =
        document.documentElement.scrollHeight || document.body.scrollHeight;
      const THROTTLE_TIME = 300; // 定义节流间隔时间
      if (scrollTop + windowHeight + 1 >= scrollHeight) {
        if (this.timer) {
          clearTimeout(this.timer); // 取消掉已有的节流定时器
        }

        this.timer = setTimeout(() => {
          if (this.parentCommentLoding) return //控制节流
          this.pageNoTop = this.pageNoTop + 1;
          this.queryComment();
        }, THROTTLE_TIME);
      }
    },
    async handleScroll() {
      const wrapper = this.$refs.scrollWrapper // 获取滚动元素
      const rect = wrapper.getBoundingClientRect() // 获取元素大小及其相对于视口的位置
      const bottom = rect.bottom // 获取元素底部距离视口顶部的距离
      const height = wrapper.clientHeight // 获取元素高度
      const scrollTop = wrapper.scrollTop // 获取滚动条的高度

      if (bottom <= height && !this.isLoadMore) { // 判断是否滚动到底部，并且没有在加载数据
        console.log("ccccccccccccccccccccccccccccccccc");
      }
    },
    formatDate(timestamp) {
      // 补全为13位
      let arrTimestamp = (timestamp + '').split('');
      for (let start = 0; start < 13; start++) {
        if (!arrTimestamp[start]) {
          arrTimestamp[start] = '0';
        }
      }
      timestamp = arrTimestamp.join('') * 1;
      let minute = 1000 * 60;
      let hour = minute * 60;
      let day = hour * 24;
      let month = day * 30;
      let now = new Date().getTime();
      let diffValue = now - timestamp;
      // 如果本地时间反而小于变量时间
      if (diffValue < 0) {
        return '不久前';
      }
      // 计算差异时间的量级
      let monthC = diffValue / month;
      let weekC = diffValue / (7 * day);
      let dayC = diffValue / day;
      let hourC = diffValue / hour;
      let minC = diffValue / minute;
      // 数值补0方法
      let zero = function (value) {
        if (value < 10) {
          return '0' + value;
        }
        return value;
      };
      // 使用
      if (monthC > 4) {
        // 超过1年，直接显示年月日
        return "很久了";
      } else if (monthC >= 1) {
        return parseInt(monthC) + '月前';
      } else if (weekC >= 1) {
        return parseInt(weekC) + '周前';
      } else if (dayC >= 1) {
        return parseInt(dayC) + '天前';
      } else if (hourC >= 1) {
        return parseInt(hourC) + '小时前';
      } else if (minC >= 1) {
        return parseInt(minC) + '分钟前';
      }
      return '刚刚';
    }
  },
  computed: {
    ...mapGetters([
      'user'
    ])
  },
  mounted() {
    window.addEventListener('scroll', this.scrollBottom)
  },
  //页面销毁前解除监听
  beforeDestroy() {
    this.doRecordHistory();
    window.removeEventListener('scroll', this.scrollBottom)
  },
  watch: {
    '$route'(to, from) {
      console.log(to, from, "asdadasd");
      if (to.path == '/detail') {
        this.doRecordHistory();
        this.setId = to.query.setId;
        this.routeVideoId = to.query.videoId;
        this.init();
      }
    },
    'user'(newValue, oldValue) {
      this.pageNoTop = 1;
      this.pageSizeTop = 10;
      this.commentParentList = [];
      this.queryComment();
    }
  }
};
</script>


<style lang="scss" scoped>
.main {
  width: 100%;
  background-color: #0d0d1d;
  height: 10rem;
  position: relative;
}

.body {
  position: absolute;
  z-index: 999;
  top: 1rem;
  left: 0;
  right: 0;
  margin: auto;
}

.back {
  line-height: 0.5rem;
  color: #cacaca;
  cursor: pointer;
}

.back:hover {
  color: #ffffff;
}

.all {
  min-width: 0.5rem;
}

.tagBase {
  padding: 0.1rem 0.2rem 0.1rem 0.2rem;
  line-height: 0.2rem;
}

.row {
  flex-flow: row wrap;
  width: 100%;
}

.parRow {
  display: flex;
  flex-direction: row;
}

.font {
  font-size: 0.2rem;
}

.bg {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.play {
  display: flex;
  flex-direction: row;
  justify-content: center;
  height: 8.8rem;
  width: 16rem;
  left: 0;
  right: 0;
  margin: auto;
}

.playleft {
  width: 12rem;
  height: 8.8rem;
}

.playright {
  background-color: #121228;
  width: 4rem;
  height: 8.8rem;
}

.padding20 {
  padding: 0.2rem;
}

.videoTitle {
  font-size: 0.3rem;
  height: 0.4rem;
}

.introduction {
  position: relative;
  margin-top: 0.2rem;
  font-size: 0.15rem;
  height: 2rem;
  color: #cacaca;
  overflow: auto;
}

::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 5px;
  /*高宽分别对应横竖滚动条的尺寸*/
  height: 5px;
  scrollbar-arrow-color: red;
}

::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: rgba(172, 172, 172, 0.945);
  scrollbar-arrow-color: red;
}

::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.3);
  border-radius: 0;
  background: rgba(0, 0, 0, 0);
}

.episode {
  display: flex;
  flex-direction: row;
  margin-top: 0.2rem;
  justify-content: space-between;
  height: 0.5rem;
  line-height: 0.5rem;
  border-bottom: 0.5px solid #8d8d8d75;
}

.episodeType {
  cursor: pointer;
  text-align: center;
  width: 50%;
  position: relative;
}

.episodeType:hover {
  background: #cdcdcd69;
}

.sold {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translate(-50%);
  content: "";
  display: block;
  width: 20px;
  height: 2px;
  border-radius: 1px;
  background: #ff6022;
}

.episodesBox {
  width: 4rem;
  display: flex;
  justify-content: flex-start;
  align-content: flex-start;
  flex-wrap: wrap;
  position: relative;
  left: -100px;
  top: 0.2rem;

  .episodeItem {
    float: left;
    cursor: pointer;
    background: rgba(0, 0, 0, 0.5);
    margin: 0.1rem;
    // width: calc((100% - 60px) / 4);
    text-align: center;
    line-height: 0.4rem;
    // min-width: calc((100% - 60px) / 4);
    // max-width: calc((100% - 60px) / 4);
    width: 0.39rem;
    height: 0.39rem;
  }
}

.titbitsDiv{
  width: 3.5rem;
  position: relative;
  left: -300px;
}

.titbitsRow {
  width: 3.5rem;
  cursor: pointer;
  display: flex;
  flex-direction: row;
  padding: 0.2rem 0rem;
}

.titbitsImg {
  width: 1.6rem;
  height: 1rem;
}

.titbitsContent {
  width: 1.9rem;
  height: 1rem;
  font-size: 0.2rem;
  color: #fff;
  padding-left: 0.1rem;
}

.titbitsContent span {
  position: relative;
  top: 0.25rem;
}

.videoActionButton {
  cursor: pointer;
  margin: 0 0.2rem 0 0.2rem;
  text-align: center;
  font-size: 0.18rem;
  display: flex;
  flex-direction: row;
  align-items: center;
  background: rgba(190, 190, 255, .15);
  width: 0.9rem;
  height: 0.32rem;
  border-radius: 20px;
  justify-content: center;
}

.videoActionButton:hover {
  color: #ff6022;
}

.buttonRow {}

.actionButtonRow {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.commentDiv {
  padding-bottom: 0.1rem;
}

.commentInput1 {
  -webkit-appearance: none;
  background-image: none;
  border-radius: 41px 0 0 41px;
  border: 1px solid #DCDFE6;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: inherit;
  height: 0.35rem;
  line-height: 0.4rem;
  outline: 0;
  padding: 0 0.2rem;
  -webkit-transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
  transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
  width: 100%;
}



.commentButton {
  line-height: 0.35rem;
  text-align: center;
  height: 0.35rem;
  border-radius: 0 41px 41px 0;
  background: #414141;
  width: 0.7rem;
}

.videoBottomAction {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: #121228;
  background-color: #121228;
  height: 0.8rem;
}

.videoBottom {
  height: 2rem;
}

.episodeTitle {
  margin-top: 0.2rem;
  font-size: 0.35rem;
}

.episodeTag {
  text-align: center;
  font-size: 0.15rem;
  height: 0.25rem;
  width: 0.5rem;
  background: rgba(255, 255, 255, .2);
  border-radius: 5px;
  margin-right: 0.2rem;
  margin-top: 0.2rem;
  line-height: 0.25rem;
}

.tagDiv {
  display: flex;
  flex-direction: row;
}

.foot {
  background: #ffffff;
  color: black;
  display: flex;
  justify-content: center;
  height: 10rem;
}

.footBody {
  width: 16rem;
  display: flex;
  flex-direction: row;

}

.commentParent {
  margin: 0.2rem 0 0.2rem 0;
  padding: 0.1rem 0;
  border: 1px solid #CDCDCD69;
  border-right: 0px;
  border-left: 0px;
}

.footLeft {
  flex: 3;
}

.footRight {
  flex: 1;
}

.commentTitle {
  position: relative;
  margin-top: 0.3rem;
  // width: 1.4rem;
  display: flex;
  flex-direction: row;
}

.commentTitleBig {
  font-size: 0.4rem;

}

// .commentTitleSm {
//   position: absolute;
//   right: 0;
//   top: 0;
// }

.at-feed-stream-post-avatar {
  width: 0.5rem;
  height: 0.5rem;
  border-radius: 50%;
  margin-right: 0.15rem;
  flex: 2;
}

.commentInput2 {
  width: 100%;
  -webkit-appearance: none !important;
  background-image: none !important;
  border-radius: 10px !important;
  border: 1px solid #DCDFE6 !important;
  -webkit-box-sizing: border-box !important;
  box-sizing: border-box !important;
  color: #606266 !important;
  display: inline-block !important;
  font-size: inherit !important;
  height: 0.65rem !important;
  line-height: 0.65rem !important;
  outline: 0 !important;
  padding: 0 0.2rem !important;
  margin-right: 0.2rem;
  -webkit-transition: border-color .2s cubic-bezier(.645, .045, .355, 1) !important;
  transition: border-color .2s cubic-bezier(.645, .045, .355, 1) !important;

}

.avatarAndCommentInput {
  display: flex;
  flex-direction: row;
  margin-top: 0.2rem;
  align-items: center;
}

.at-feed-stream-post__login-btn {
  color: #ffffff;
  background: #00c8c8;
  border-radius: 16px;
  text-align: center;
  width: 1.8rem;
  position: absolute;
  height: 0.4rem;
  line-height: 0.4rem;
  margin-left: 0.2rem;
  left: 0.2rem;
  top: 0;
  bottom: 0;
  margin: auto;
}

.commentTags {
  margin-top: 0.2rem;
  display: flex;
  flex-direction: row;
}

.commentTag {
  color: #6e6e6e;
  width: 0.7rem;
  height: 0.4rem;
  background: #ecebeb;
  margin: 0.2rem 0.2rem 0.2rem 0;
  line-height: 0.4rem;
  text-align: center;
  border-radius: 20px;
  cursor: pointer;
}

.commentTagClick {
  background: linear-gradient(27deg, rgba(255, 96, 34, .1) 5%, rgba(255, 153, 73, .06) 100%);
  color: #ff6022;
}

.commentParentHeader {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.userNameParent {
  font-weight: bolder;
  padding-right: 0.15rem;
}

.commentTime {
  color: #8a8a8a;
  font-size: 0.15rem;
}

.commentContentParent {
  margin-top: 0.15rem;
}

.commentAction {
  margin-top: 0.15rem;
}

.commentSon {
  background: #f6f8fa;
  border-radius: 0.16rem;
  padding: 0.2rem;
  margin: 0.2rem 0 0 0;
}

.commentSonHeader {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.commentContentSon {
  padding-top: 0.15rem;
}

.userNameSon {
  font-size: 0.16rem;
  font-weight: bolder;
  padding-right: 0.15rem;
}

#vue-player{
  //width: 1150px;
  //height: 650px;
  width: 100%;
  height: 800px;
}
</style>
