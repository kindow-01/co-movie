<template>
  <div class="manuDiv">
    <div
      @click="check(bars.CONCENTRATION.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.CONCENTRATION.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-star-on"></i>
      <div class="manuFont">精选</div>
    </div>
    <div
      @click="check(bars.FREE.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.FREE.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-star-off"></i>
      <div class="manuFont">免费专区</div>
    </div>
    <div
      @click="check(bars.MEDIUMIDEO.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.MEDIUMIDEO.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-video-camera-solid"></i>
      <div class="manuFont">中视频</div>
    </div>
    <div
      @click="check(bars.TV.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.TV.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-camera"></i>
      <div class="manuFont">电视剧</div>
    </div>
    <div
      @click="check(bars.FILM.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.FILM.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-video-camera"></i>
      <div class="manuFont">电影</div>
    </div>
    <div
      @click="check(bars.VARIETY.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.VARIETY.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-s-grid"></i>
      <div class="manuFont">综艺</div>
    </div>
    <div
      @click="check(bars.COMIC.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.COMIC.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-video-camera"></i>
      <div class="manuFont">动漫</div>
    </div>
    <div
      @click="check(bars.JUVENILE.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.JUVENILE.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-s-opportunity"></i>
      <div class="manuFont">少儿</div>
    </div>
    <div
      @click="check(bars.DOCUMENTARY.cname)"
      class="g_row_and_center manuItem"
      :style="barClick == bars.DOCUMENTARY.cname ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-view"></i>
      <div class="manuFont">纪录片</div>
    </div>
    <div
      @click="check('manuItem10')"
      class="g_row_and_center manuItem"
      :style="barClick == 'manuItem10' ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-coordinate"></i>
      <div class="manuFont">游戏中心</div>
    </div>
    <div
      @click="check('manuItem1')"
      class="g_row_and_center manuItem"
      :style="barClick == 'manuItem11' ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-s-finance"></i>
      <div class="manuFont">VIP会员</div>
    </div>
    <div
      @click="check('manuItem11')"
      class="g_row_and_center manuItem"
      :style="barClick == 'manuItem12' ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-basketball"></i>
      <div class="manuFont">NBA</div>
    </div>
    <div
      @click="check('manuItem12')"
      class="g_row_and_center manuItem"
      :style="barClick == 'manuItem13' ? '  color: #ff6f00;' : ''"
    >
      <i class="el-icon-ship"></i>
      <div class="manuFont">传奇游戏</div>
    </div>
  </div>
</template>

<script>
import { barS } from "../../util/manuConstants";
export default {
  components: {
    //用来注册子组件的节点
  },
  props: {
    barClick: {
      type: String,
      default: barS.CONCENTRATION.cname,
    },
  },
  name: "leftbar",
  data() {
    return {
      bars: barS,
    };
  },
  created() {},
  methods: {
    check(str) {
      console.log(this.barClick, "barClick");
      console.log(str, "str");
      console.log(this.bars, "bars");
      // this.barClick = str;
      if (str == this.bars.CONCENTRATION.cname) {
        this.$router.push({
          path: "/",
        });
      } else if (str == this.bars.FREE.cname) {
        this.$router.push({
          path: "/free",
        });
      } else if (str == this.bars.MEDIUMIDEO.cname) {
        this.$router.push({
          path: "/mediumVideo",
        });
      } else if (str == this.bars.TV.cname) {
        this.$router.push({
          path: "/tv",
        });
      } else if (str == this.bars.FILM.cname) {
        this.$router.push({
          path: "/film",
        });
      } else if (str == this.bars.VARIETY.cname) {
        this.$router.push({
          path: "/variety",
        });
      } else if (str == this.bars.COMIC.cname) {
        this.$router.push({
          path: "/comic",
        });
      } else if (str == this.bars.JUVENILE.cname) {
        this.$router.push({
          path: "/juvenile",
        });
      } else if (str == this.bars.DOCUMENTARY.cname) {
        this.$router.push({
          path: "/documentary",
        });
      }
    },
  },
  mounted() {},
};
</script>
 

<style scoped >
.manuDiv {
  padding-top: 0.2rem;
  padding-left: 0.5rem;
  padding-right: 0.5rem;
  display: flex;
  flex-direction: column;
  font-size: 0.2rem;
  position: fixed;
  left: 0;
  z-index: 99999;
  top: 0.5rem;
}
.manuItem {
  color: #cacaca;
  cursor: pointer;
  width: 2rem;
  padding-top: 0.2rem;
  padding-left: 0.2rem;
}
.manuItem:hover {
  color: #ffffff;
}
.manuFont {
  padding-left: 0.2rem;
}
.clickstyle {
  background-color: red;
}
</style>
