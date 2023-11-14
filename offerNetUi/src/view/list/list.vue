<template>
  <div class="main">
    <div>
      <topBar></topBar>
    </div>
    <div>
      <leftBar :barClick="barName"></leftBar>
    </div>

    <div class="body">
      <div class="back g_row_and_center">
        <i class="el-icon-arrow-left"></i>
        <div @click="back">返回</div>
      </div>
      <div class="font">
        <div>
          <div class="parRow" v-for="(tagItem, indexItem) in tagList">
            <div class="all tagBase" @click="clearSelectTag(tagItem.tags, indexItem)">全部</div>
            <div class="g_row_and_center row">
              <div @click="addSelectTag(tag, indexItem)" class="tagBase"
                :style="checkTagList[indexItem].split(',').indexOf(tag) != -1 ? 'color: #ff6022;' : ''"
                v-for="(tag, indexTag) in tagItem.tags.split(',')">{{ tag }}</div>
            </div>
          </div>
        </div>
        <div>
          <el-divider></el-divider>
        </div>
        <div>
          <div class="g-categoryTagDiv">
            <!-- <div class="g-categoryTagDiv-item">最新</div>
            <div class="g-categoryTagDiv-item">最热</div>
            <div class="g-categoryTagDiv-item">好评</div> -->
          </div>
          <div>
            <videos5 :videoList="hostList"></videos5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import topBar from "../../components/topBar/topBar.vue";
import leftBar from "../../components/leftBar/leftBar.vue";
import videos5 from "../../components//videos5.vue";
import api from "@/util/api"
export default {
  components: {
    //用来注册子组件的节点
    topBar,
    leftBar,
    videos5
  },
  name: "list",
  data() {
    return {
      barName: "",
      defaultTag: "",
      tagList: [],
      checkTagList: [],
      hostList: [

      ],
      pageNo: 1,
    };
  },
  created() {
    this.defaultTag = this.$route.query.tag;
    this.barName = this.$route.query.barName;
    console.log(this.barName, this.defaultTag);
    //在首页展示的标签默认为最前面的标签组，所以直接[0]
    this.checkTagList[0] = this.defaultTag;
    this.init();


  },
  methods: {
    init() {
      let param = {
        categoryName: this.barName,
        pageNo: 1,
        pageSize: 10//最多获取十组标签吧，太多了也没意义
      }
      api.videoSetTagPage(param).then((res) => {
        if (res.code == 200) {
          this.tagList = res.result.records;
          //初始化标签选择数组
          for (let index = 0; index < this.tagList.length; index++) {
            this.checkTagList[index] = "";
          }
          this.checkTagList[0] = this.defaultTag;
        }
      })


      this.queryVideo();
    },
    //返回
    back() {
      this.$router.go(-1);
    },
    scrollBottom() {
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
      const THROTTLE_TIME = 300; // 定义节流间隔时间
      if (scrollTop + windowHeight + 1 >= scrollHeight) {
        if (this.timer) {
          clearTimeout(this.timer); // 取消掉已有的节流定时器
        }

        this.timer = setTimeout(() => {
          this.pageNo = this.pageNo + 1;
          this.queryVideo();
        }, THROTTLE_TIME);
      }
    },
    //添加筛选的标签
    addSelectTag(tag, index) {
      let temp = JSON.parse(JSON.stringify(this.checkTagList[index])).split(",").filter(tag => tag !== "");

      temp.push(tag);
      console.log(temp, "this.checkTagList[index]");

      this.checkTagList[index] = temp.join(',');
      this.refsh();
    },
    //把tags在checkTagList中清空
    clearSelectTag(tags, index) {
      this.checkTagList[index] = ''
      this.refsh();
    },
    refsh() {
      this.hostList = [];
      this.pageNo = 1;
      this.queryVideo();
    },
    queryVideo() {
      this.checkTagList
      let param = {
        categoryName: this.barName,
        pageNo: this.pageNo,
        pageSize: 10,
        tags: this.checkTagList.filter(tag => tag !== "").join('|')
      }
      api.videoSetPage(param).then((res) => {
        if (res.code == 200) {
          if (res.result.records.length > 0) {
            this.hostList = this.hostList.concat(res.result.records);
          } else {
            this.pageNo--;
          }
        }
      })
    }
  },
  mounted() {
    window.addEventListener('scroll', this.scrollBottom)
  },
  //页面销毁前解除监听
  beforeDestroy() {
    window.removeEventListener('scroll', this.scrollBottom)
  },
};
</script>
 

<style lang="scss" scoped >
.main {
  width: 100%;
  background-color: #000000;
  height: 16rem;
}

.body {
  position: relative;

  padding-top: 1rem;
  padding-left: 3rem;
  width: calc((100% - 3rem));
  background: black;
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
  cursor: pointer;
}

.tagBase:hover {
  color: #ff6022;
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
</style>
