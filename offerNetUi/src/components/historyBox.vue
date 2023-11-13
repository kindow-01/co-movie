<template>
  <div class="box">
    <div class="headerRow">
      <div class="headerItem" :style="checkedItemIndex == '1' ? 'color: #ff6022;' : ''" @click="checkedItem(1)">
        看过
        <div class="sold" v-if="checkedItemIndex == '1'"></div>
      </div>
      <div class="headerItem" :style="checkedItemIndex == '2' ? 'color: #ff6022;' : ''" @click="checkedItem(2)">
        在追
        <div class="sold" v-if="checkedItemIndex == '2'"></div>
      </div>
      <div class="headerItem" :style="checkedItemIndex == '3' ? 'color: #ff6022;' : ''" @click="checkedItem(3)">
        收藏
        <div class="sold" v-if="checkedItemIndex == '3'"></div>
      </div>
    </div>
    <div class="bodyRow">

      <div v-if="checkedItemIndex == '1' && historyList.length > 0" class="historyItem"
        v-for="(item, index) in historyList" @click="goVideoDetail(item.setId, item.videoId)" :key="index">
        <div class="historyImgDiv" :style="getUrl(item.coverLevel)"></div>
        <div style="padding-left: 0.1rem;">
          <div class="title">{{ item.videoSetName }} {{ item.videoName }}</div>
          <div class="rateOfProgress">观看至{{ item.schedule }}%</div>
        </div>
      </div>
      <no-data v-if="checkedItemIndex == '1' && historyList.length < 1 && !needLogin" message="没有找到相关数据"></no-data>

      <div v-if="checkedItemIndex == '2' && chasingDramaList.length > 0" class="historyItem"
        @click="goVideoDetail(item.videoSetId)" v-for="(item, index) in chasingDramaList">
        <div class="historyImgDiv" :style="getUrl(item.coverLevel)"></div>
        <div style="padding-left: 0.1rem;">
          <div class="title">{{ item.setName }}</div>
          <div class="rateOfProgress">{{ item.setIntroductionShort }}</div>
        </div>
      </div>
      <no-data v-if="checkedItemIndex == '2' && chasingDramaList.length < 1 && !needLogin" message="没有找到相关数据"></no-data>


      <div v-if="checkedItemIndex == '3' && collectList.length > 0" class="historyItem"
        @click="goVideoDetail(item.setId, item.videoId)" v-for="(item, index) in collectList" :key="index">
        <div class="historyImgDiv" :style="getUrl(item.coverLevel)"></div>
        <div style="padding-left: 0.1rem;">
          <div class="title">{{ item.videoSetName }} {{ item.videoName }}</div>
          <div class="rateOfProgress">观看至{{ item.schedule }}%</div>
        </div>
      </div>
      <no-data v-if="checkedItemIndex == '3' && collectList.length < 1 && !needLogin" message="没有找到相关数据"></no-data>
      <please-login v-if="needLogin" message="登录获取更好的体验"></please-login>
    </div>
  </div>
</template>

<script>
import api from "../util/api"
export default {
  components: {
    //用来注册子组件的节点
  },
  name: "historyBox",
  data() {
    return {
      checkedItemIndex: '1',
      historyList: [],
      collectList: [],
      chasingDramaList: [],
      needLogin: false
    };
  },
  created() { },
  methods: {
    init() {
      this.initHistory();
    },
    //追剧列表
    initChasingDramaList() {
      let param = {
        notOpenLoginBox: true,
        pageSize: 50,
      }
      api.chasingDramaList(param).then((res) => {
        if (res.code == 200) {
          this.chasingDramaList = res.result.records;
          this.needLogin = false;
        }
      }).catch((err) => {
        this.needLogin = true;
      })
    },
    initHistory() {
      let param = {
        notOpenLoginBox: true, pageSize: 50,
      }
      api.historyList(param).then((res) => {
        if (res.code == 200) {
          this.historyList = res.result.records;
          this.needLogin = false;
        }
      }).catch((err) => {
        this.needLogin = true;
      })
    },
    initCollect() {
      let param = {
        notOpenLoginBox: true,
        pageSize: 50,

      }
      api.collectList(param).then((res) => {
        if (res.code == 200) {
          this.collectList = res.result.records;
          this.needLogin = false;
        }
      }).catch((err) => {
        this.needLogin = true;
      })
    },
    checkedItem(index) {
      this.checkedItemIndex = index;
      if (index == 3) {
        //加载收藏列表
        this.initCollect();
      }
      if (index == 2) {
        this.initChasingDramaList();
      }
    },
    getUrl(str) {
      let ret = "";
      ret = 'background-image: url(' + '"' + str + '"' + ');';
      return ret;
    }
  },
  mounted() {
    this.init();
  },
};
</script>
 

<style scoped>
.box {
  width: 4rem;
  height: 5rem;
  background: #1e2737;
}

.headerRow {
  display: flex;
  flex-direction: row;
  background: #181f2c;
}

.bodyRow {
  height: 4.35rem;
  overflow-y: auto;
  margin: 0 0.2rem 0 0.2rem;
}

.headerItem {
  font-size: 0.17rem;
  flex: 1;
  text-align: center;
  position: relative;
  padding: 0.2rem 0 0.2rem 0;
  height: 0.25rem;
}

.headerItem:hover {
  color: #ff6022;
}

.sold {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translate(-50%);
  content: "";
  display: block;
  width: 0.2rem;
  height: 2px;
  border-radius: 1px;
  background: #ff6022;
}

.historyItem {
  padding-top: 0.2rem;
  display: flex;
  flex-direction: row;
}

.historyImgDiv {
  width: 1.2rem;
  height: 0.6rem;
  border-radius: 7px;
  background-size: cover;
  background-image: url('https://puui.qpic.cn/vcover_hz_pic/0/mzc002007knmh3g1673530815496/332?max_age=7776001');
}

.rateOfProgress {
  height: 0.2rem;
  font-size: 0.1rem;
  color: rgb(146, 146, 146);
}

.title {
  font-size: 0.17rem;
  height: 0.45rem;
  width: 2.4rem;
}
</style>
