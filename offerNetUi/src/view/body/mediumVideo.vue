<template>
  <div class="main">
    <div>
      <leftBar :barClick="barName.cname"></leftBar>
    </div>
    <div class="rightViewUnTop1">
      <videos3 @chasingDramaAddOrDelOk="chasingDramaAddOrDelOk" :videoList="recommendVideoList"></videos3>
    </div>
  </div>
</template>

<script>
import topBar from "../../components/topBar/topBar.vue";
import leftBar from "../../components/leftBar/leftBar.vue";
import recommend from "../../components/recommend.vue";
import videos3 from "../../components/videos3.vue";
import { barS } from "../../util/manuConstants";
import api from "../../util/api";
import { mapGetters, mapActions } from 'vuex';
export default {
  components: {
    //用来注册子组件的节点
    topBar,
    leftBar,
    recommend,
    videos3,
  },
  name: "concentration",
  data() {
    return {
      barName: barS.MEDIUMIDEO,
      recommendVideoLoding: false,
      recommendVideoList: [],
      recommendVideoSearchData: {
        pageNo: 1,
        pageSize: 10,
        categoryName: barS.MEDIUMIDEO.cname
      },
      hostList: [
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
      ],
    };
  },
  created() { },
  methods: {
    //追剧接口请求完毕回调
    chasingDramaAddOrDelOk(index) {
      this.recommendVideoList[index].chasingDrama = !this.recommendVideoList[index].chasingDrama;
    },
    //推荐视频
    recommendVideo() {
      this.recommendVideoLoding = true;
      if (this.user && this.user.id) {
        api.recommendAuth(this.recommendVideoSearchData).then((res) => {
          if (res.code == 200) {
            this.recommendVideoList = this.recommendVideoList.concat(res.result.records);
          }
          if (res.result.records.length < 1) {
            this.recommendVideoSearchData.pageNo--;
          }
          this.recommendVideoLoding = false;
        });
      } else {
        api.recommend(this.recommendVideoSearchData).then((res) => {
          if (res.code == 200) {
            this.recommendVideoList = this.recommendVideoList.concat(res.result.records);
          }
          if (res.result.records.length < 1) {
            this.recommendVideoSearchData.pageNo--;
          }
          this.recommendVideoLoding = false;
        });
      }
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
      console.log(scrollTop, "scrollTop");
      console.log(windowHeight, "windowHeight");
      console.log(scrollHeight, "scrollHeight");
      // 到底的条件
      if (scrollTop + windowHeight + 1 >= scrollHeight) {

        // //到底后要去触发的事件
        console.log("触底了？？？？？？");
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
  //页面销毁前解除监听
  beforeDestroy() {
    window.removeEventListener('scroll', this.scrollBottom)
  },
  computed: {
    ...mapGetters([
      'user'
    ])
  },
  mounted() {
    window.addEventListener('scroll', this.scrollBottom)
    this.recommendVideo();
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
    width: calc((100% - 45px) / 3);
    min-width: calc((100% - 45px) / 3);
    max-width: calc((100% - 45px) / 3);
  }
}

.bigCardBottom {
  background-color: #2b2b2b;
  border-radius: 0 0 10px 10px;
}

.bigCardBottomContent {
  padding: 0.2rem;
}

.tag {
  font-size: 0.2rem;
  padding: 0.2rem 0.2rem 0.35rem 0.2rem;
  color: #cacaca;
  cursor: pointer;
}

.tag:hover {
  color: #ffffff;
}
</style>
