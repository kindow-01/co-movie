<template>
  <div class="main">
    <div>
      <leftBar :barClick="barName.cname"></leftBar>
    </div>

    <div>
      <div class="player">
        <!-- <coverVideo :coverVideoUrl="coverVideoUrl"></coverVideo> -->

        <div class="rightView">
          <recommendSmall @checkedUrl="checkedUrl" :recommendList="recommendList"> </recommendSmall>
          <div class="videoList">
            <div class="">
              <div class="g-categoryTagDiv">
                <div v-for="(tag, index) in tags" class="g-categoryTagDiv-item" @click="goList(barName.cname, tag)">
                  {{ tag }}
                </div>
              </div>
              <div class="hotTitle">重磅热播</div>
              <videos4 :videoList="hostList"></videos4>
            </div>

            <div class="">
              <div class="hotTitle">为你推荐</div>
              <div class="">
                <videos3 @chasingDramaAddOrDelOk="chasingDramaAddOrDelOk" :videoList="recommendVideoList"></videos3>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import coverVideo from "../../components/coverVideo.vue";
import topBar from "../../components/topBar/topBar.vue";
import leftBar from "../../components/leftBar/leftBar.vue";
import recommendSmall from "../../components/recommendSmall.vue";
import videos3 from "../../components/videos3.vue";
import videos4 from "../../components/videos4.vue";
import { barS } from "../../util/manuConstants";
import api from "../../util/api";
import { mapGetters, mapActions } from 'vuex';

export default {
  components: {
    //用来注册子组件的节点
    topBar,
    leftBar,
    recommendSmall,
    videos3,
    videos4,
    coverVideo
  },
  name: "concentration",
  data() {
    return {
      barName: barS.FILM,
      coverVideoUrl: 'd=17&ext=.mp4',
      recommendList: [],
      hostList: [
        
      ],
      recommendVideoSearchData: {
        pageNo: 1,
        pageSize: 10,
        categoryName: '电影'
      },
      recommendVideoLoding: false,
      recommendVideoList: [],
      tags: []
    };
  },
  created() {
    this.recommend();
    this.videoSetAll();
    this.recommendVideo();
    this.initTag();
  },
  methods: {
    //追剧接口请求完毕回调
    chasingDramaAddOrDelOk(index) {
      this.recommendVideoList[index].chasingDrama = !this.recommendVideoList[index].chasingDrama;
    },
    //获取标签
    initTag() {
      let param = {
        pageSize: 1,
        categoryName: this.barName.cname
      }
      api.videoSetTagPage(param).then((res) => {
        if (res.code == 200 && res.result.records.length > 0) {
          this.tags = res.result.records[0].tags.split(',');
        }
      });
    },
    //推荐卡片
    recommend() {
      let param = {
        limit: 16,
        carefullyChosen: 1,
        categoryName: '电影'
      }
      api.videoSetAll(param).then((res) => {
        if (res.code == 200) {
          this.recommendList = res.data;
          // this.recommendList=[]
        }
      });
    },
    //推荐视频
    recommendVideo() {
      this.recommendVideoLoding = true;
      if (this.user && this.user.id) {
        api.recommendAuth(this.recommendVideoSearchData).then((res) => {
          if (res.code == 200) {
            this.recommendVideoList = this.recommendVideoList.concat(res.data);
          }
          if (res.data.length < 1) {
            this.recommendVideoSearchData.pageNo--;
          }
          this.recommendVideoLoding = false;
        });
      } else {
        api.recommend(this.recommendVideoSearchData).then((res) => {
          if (res.code == 200) {
            this.recommendVideoList = this.recommendVideoList.concat(res.data);
          }
          if (res.data.length < 1) {
            this.recommendVideoSearchData.pageNo--;
          }
          this.recommendVideoLoding = false;
        });
      }
    },
    checkedUrl(url) {
      this.coverVideoUrl = url;
    },
    videoSetAll() {
      let param = {
        limit: 8,
        categoryName: "电影"
      }
      api.videoSetAll(param).then((res) => {
        if (res.code == 200) {
          this.hostList = res.data;
        }
      });
    },
    scrollBottom() {
      // 变量scrollTop为当前页面的滚动条纵坐标位置
      let scrollTop =
        document.documentElement.scrollTop || document.body.scrollTop;
      // 变量 windowHeight 是可视区的高度
      let windowHeight =
        document.documentElement.clientHeight || document.body.clientHeight;
      // 变量 scrollHeight 是滚动条的总高度
      let scrollHeight =
        document.documentElement.scrollHeight || document.body.scrollHeight;
      //如果向下滚动超过过100 则给头部导航换个颜色
      if (scrollTop >= 100) {
        this.$store.dispatch('setTopBarBackground', 'rgba(45, 45, 45, 0.826)');
      } else {
        this.$store.dispatch('setTopBarBackground', '');
      }
      // 到底的条件
      if (scrollTop + windowHeight + 1 >= scrollHeight) {

        const THROTTLE_TIME = 300; // 定义节流间隔时间
        if (this.timer) {
          clearTimeout(this.timer); // 取消掉已有的节流定时器
        }

        this.timer = setTimeout(() => {
          if (this.recommendVideoLoding) return //控制节流
          this.recommendVideoSearchData.pageNo = this.recommendVideoSearchData.pageNo + 1;
          this.recommendVideo();
        }, THROTTLE_TIME);
      }
    },
  },
  mounted() {
    window.addEventListener('scroll', this.scrollBottom)
  },
  computed: {
    ...mapGetters([
      'user'
    ])
  },
  //页面销毁前解除监听
  beforeDestroy() {
    window.removeEventListener('scroll', this.scrollBottom)
  },
  watch: {
    'user'(newValue, oldValue) {
      this.recommendVideoSearchData.pageNo = 1;
      this.recommendVideoSearchData.pageSize = 10;
      this.recommendVideoList = [];
      this.recommendVideo();
    }
  }
};
</script>
 

<style lang="scss" scoped >
.video-mask {
  position: absolute;
  top: 0;
  height: 100%;
  width: 100%;
  background-image: linear-gradient(180deg, rgba(0, 0, 0, 0) 0, #000000 70%);
}

.videoB {
  width: 16rem;
}

.videoList {

  z-index: 9999999;

  width: 16rem;
}

.hotTitle {
  font-size: 0.4rem;
  margin: 0.2rem 0.2rem 0.2rem 0;
}

.hostCard {
  height: 2rem;
  background-color: #ffffff;
  border-radius: 10px;
}

.bigCard {
  width: 100%;
  height: 3rem;
  background-color: #ffffff;
  border-radius: 10px 10px 0 0;
}

.hostDescribe {
  padding-top: 0.05rem;
  color: #747373;
  font-size: 0.18rem;
}

.hostTitle {
  padding-top: 0.1rem;
  color: #ffffff;
}

.flex-wrap {
  display: flex;
  justify-content: flex-start;

  flex-wrap: wrap;

  .flex-wrap-item {
    margin: 0 15px 0 0;
    width: calc((100% - 60px) / 4);
    min-width: calc((100% - 60px) / 4);
    max-width: calc((100% - 60px) / 4);
  }
}

.bigCardBottom {
  background-color: #2b2b2b;
  border-radius: 0 0 10px 10px;
}

.bigCardBottomContent {
  padding: 0.2rem;
}
</style>
