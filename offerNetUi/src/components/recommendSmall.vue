<template>
  <div class="">
    <div class="g_row_and_center_between recommend">
      <div class="left" v-if="recommendList.length > 0">
        <div class="title" style="margin: 0.2rem 0.2rem 0.2rem 0">
          {{ checkedRecommend.setName }}
        </div>
        <div class="detail" style="margin: 0.2rem 0.2rem 0.2rem 0">
          {{ checkedRecommend.setIntroductionShort }}
        </div>
        <div class="" style="margin: 0.2rem 0.2rem 0.2rem 0">
          <el-button icon="el-icon-caret-right" class="playButton" @click="goVideoDetail(checkedRecommend.id,checkedRecommend.setid)">播放正片</el-button>
        </div>
      </div>
      <no-data v-else message="没有找到相关数据"></no-data>
      <div class="right g_row_and_center_between">
        <i class="el-icon-arrow-left recommendArrow" @click="leftClick()"></i>
        <div v-if="recommendListPage.length>0" v-for="(item, index) in recommendListPage" style="  cursor: pointer;" @click="goVideoDetail(checkedRecommend.id,checkedRecommend.setid)" :key="index" class="g_vertical_center">
          <div style="position: relative">
            <div class="card" :style="getUrl(item.cover)"></div>
            <!-- <div class="cardMask" v-if="item.checked" v-on:mouseover="handleOver(item,index)"></div> -->
            <div :class="item.id == checkedRecommend.id ? 'cardMaskClear' : 'cardMask'
              " v-on:mouseover="handleOver(item, index)"></div>
          </div>

          <div class="">{{ item.title }}</div>
        </div>
        <no-data v-if="recommendListPage.length==0" message="没有找到相关数据"></no-data>

        <i class="el-icon-arrow-right recommendArrow" @click="rightClick()"></i>

        <!-- <div class="card"></div>
        <div class="card"></div>
        <div class="card"></div>
        <div class="card"></div> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    //用来注册子组件的节点
  },
  name: "recommend",
  props: {
    recommendList: {
      type: Array,
      default: [],
    },
  },
  data() {
    return {
      pageNo: 1,
      recommendListPage: [],
      checkedRecommend: {
        id: 2,
        cover:
          "https://puui.qpic.cn/tv/0/1249835401_276386/450?max_age=7776001",
        payUrl: "",
        detail: "邓超 陈赫 生活体验",
        title: "五哈3",
      },
    };
  },
  created() { },
  methods: {
    leftClick() {
      if (this.pageNo == 1) {
        //实现第一页跳到最后一页
        let maxNo = Math.ceil(this.recommendList.length / 4);
        this.pageNo = maxNo;
      } else {
        //正常上一页
        this.pageNo = this.pageNo - 1;
      }
      this.pageNoChange();
    },
    rightClick() {
      let maxNo = Math.ceil(this.recommendList.length / 4);
      if (this.pageNo == maxNo) {
        // 实现最后一页跳到第一页
        this.pageNo = 1;
      } else {
        //正常下一页
        this.pageNo = this.pageNo + 1;
      }
      this.pageNoChange();

    },
    handleOver(data) {
      console.log(data);
      this.checkedRecommend = data;
      this.$emit('checkedUrl', this.checkedRecommend.coverVideoUrl)
    },
    handleOut(data) {
      console.log(data);
    },
    pageNoChange() {
      let list = this.recommendList.slice((this.pageNo - 1) * 4, (this.pageNo - 1) * 4 + 4);
      this.recommendListPage = list;
      this.checkedRecommend = list[0];
      this.$emit('checkedUrl', this.checkedRecommend.coverVideoUrl)
    },
    getUrl(str) {
      let ret = "";
      ret = 'background-image: url(' + '"' + str + '"' + ');';
      return ret;
    }
  },
  mounted() {

  },
  watch: {
    // 被侦听的变量count
    recommendList() {
      this.pageNoChange();
    }
  }
};
</script>
 

<style scoped >
.recommend {
  width: 16rem;
}

.title {
  font-size: 0.5rem;
}

.card {
  margin: 0.2rem;
  width: 1.5rem;
  height: 1rem;
  background: #ffffff;
  border-radius: 10px;

  background-size: cover;
}

.cardMaskClear {
  margin: 0.2rem;
  border-radius: 10px;
  width: 1.5rem;
  height: 1rem;
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0);
}

.cardMask {
  margin: 0.2rem;
  border-radius: 10px;
  width: 1.5rem;
  height: 1rem;
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
}

.recommendArrow {
  z-index: 999999999999;
  font-size: 1rem;
}

.recommendArrow:hover {
  color: #ff9d00;
}

playButton {
  background-color: #ababab1f;
}

.el-button {
  display: inline-block;
  line-height: 1;
  white-space: nowrap;
  cursor: pointer;
  background: #5f5f5f77;
  border: 1px solid #dcdfe6;
  color: #ffffff;
  -webkit-appearance: none;
  text-align: center;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  outline: 0;
  margin: 0;
  -webkit-transition: 0.1s;
  transition: 0.1s;
  font-weight: 500;
  padding: 12px 20px;
  font-size: 0.25rem;
  border-radius: 30px;
}
</style>
