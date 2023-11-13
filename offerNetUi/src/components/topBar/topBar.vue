<template>
  <div>
    <div class="manuDiv"
      :style="topBarBackground && topBarBackground != '' ? 'background:' + topBarBackground + ';' : ''">

      <router-link to="/">
        <div class="" style="width: 2rem;height: 0.5rem;">
          <img src="../../assets//logo-long-tm.png" style="object-fit: cover;width: 100%;height: 100%;">
        </div>

      </router-link>

      <!-- <div class="seachDiv g_row_and_center" style="flex:1;">
        <el-input v-model="input" placeholder="请输入内容"> </el-input>
        <i class="el-icon-search searchIcon"></i>
      </div> -->
      <div class="headerRight" style="flex:1;">
        <div class="headerRightItem historyText">
          <span class="iconfont iconfont-lishixiao g-textHoverColor" @mouseover="historyBoxMouseOver1"
            @mouseleave="historyBoxMouseLeave1" style="font-size: 0.3rem;"></span>
          <div class="historyBox" @mouseover="historyBoxMouseOver2" @mouseleave="historyBoxMouseLeave2">
            <historyBox v-if="historyBoxShow1 || historyBoxShow2"></historyBox>
          </div>
        </div>
        <div class="headerRightItem g-textHoverColor" @click="showAppreciate">
          赞赏
        </div>
        <div class="headerRightItem g-textHoverColor" @click="showAbout">
          关于
        </div>
        <div class="headerRightItem g-textHoverColor" @click="goREADME">

          项目介绍

        </div>
        <div class="headerRightItem g-textHoverColor" @click="goLICENSE">

          开源协议

        </div>
        <div class="headerRightItem g-textHoverColor" @click="showLoginBox">
          <img class="g-avatar" :src="user.avatar ? user.avatar : 'https://doc.firstui.cn/img/img_logo.png'">
        </div>

      </div>
    </div>
    <loginBox :show="showLoginBoxData" @close="loginBoxClose()"></loginBox>
    <appreciate ref="appreciate"></appreciate>

  </div>
</template>

<script>
import topBar from "../../components/topBar/topBar.vue";
import leftBar from "../../components/leftBar/leftBar.vue";
import recommend from "../../components/recommend.vue";
import videos3 from "../../components/videos3.vue";
import videos4 from "../../components/videos4.vue";
import { barS } from "../../util/manuConstants";
import historyBox from "../../components/historyBox.vue";
import loginBox from "../../components/loginBox.vue";
import appreciate from "../../components/appreciate.vue";

import {
  ACCESS_TOKEN,
  USER_NAME,
  USER_INFO,
  BASE_URL,
} from "../../util/constants";
import { mapGetters, mapActions } from 'vuex'
export default {
  components: {
    //用来注册子组件的节点
    topBar,
    leftBar,
    recommend,
    videos3,
    videos4,
    historyBox,
    loginBox,
    appreciate,
  },
  name: "concentration",
  data() {
    return {
      userInfo: {},
      scroll: '',
      historyBoxShow1: false,
      historyBoxShow2: false,
      input: '',
    };
  },
  created() {

  },
  computed: {
    ...mapGetters([
      'user', 'showLoginBoxData', 'topBarBackground'
    ])
  },
  mounted() {
    // window.addEventListener('storage', this.handleStorageChange)
    // this.$watch(() => {
    //   return window.localStorage.getItem(USER_INFO)
    // }, (newVal, oldVal) => {
    //   // 缓存数据发生变化时的处理逻辑
    //   console.log('localStorage中的user值发生了变化1')

    // })
  },

  methods: {
    goREADME() {
      window.open("https://gitee.com/yang-ruyuan/video-system-open/blob/master/README.md")
    },
    goLICENSE() {
      window.open("https://gitee.com/yang-ruyuan/video-system-open/blob/master/LICENSE")
    },
    showAbout() {
      this.$router.push({
        path: "/about",
      });
    },
    showAppreciate() {
      this.$refs.appreciate.openModal();
    },
    loginBoxClose() {
      this.$store.dispatch('setShowLoginBoxData', false);
    },

    getAvatar() {
    },
    showLoginBox() {
      if (this.user.id) {
        this.$router.push({
          path: "/userInfo",
        });
      } else {
        this.$store.dispatch('setShowLoginBoxData', true)
      }
    },
    handleScroll() {
      //或者使用document.querySelector('.class或者#id').scrollTop
      this.scroll = document.documentElement.scrollTop || document.body.scrollTop
      console.log(this.scroll)
    },
    historyBoxMouseOver1() {
      console.log("移入了");
      this.historyBoxShow1 = true;
    },
    historyBoxMouseLeave1() {
      console.log("移出了");
      let that = this;
      setTimeout(function () {
        that.historyBoxShow1 = false;	// 需要执行的代码		
      }, 300); // 定时时间

    },
    historyBoxMouseOver2() {
      console.log("移入了");
      this.historyBoxShow2 = true;
    },
    historyBoxMouseLeave2() {
      console.log("移出了");
      let that = this;
      setTimeout(function () {
        that.historyBoxShow2 = false;	// 需要执行的代码		
      }, 300); // 定时时间
    }
  },
  //销毁,否则跳到别的路由还是会出现
  destroyed() {
    window.removeEventListener('scroll', this.handleScroll)
  }

};
</script>
 
<style scoped >
.manuDiv {
  padding: 0.2rem 0.2rem 0.2rem 0.2rem;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  position: fixed;
  left: 0;
  right: 0;
  margin: auto;
  z-index: 1999;
}

.sonLine::after {
  content: "";
  display: flex;
  left: 0;
  right: 0;
  top: 100%;
  height: 3px;
  background: #4c81c9;
  -webkit-transform: scaleX(0);
  transform: scaleX(0);
}

/* .sonLine:hover {
      color: #4c81c9;
    } */

.sonLine:hover::after {
  transition: 0.3s;
  -webkit-transform: scaleX(1);
  transform: scaleX(1);
}

/* .sonLine:hover{
  position: relative;
  content: "";
  height: 1px;
  border-bottom: 3px solid #eee;
  transition: all 0.3s ease-in-out;
} */
.parentDiv {
  flex-direction: column;
  display: flex;
  align-items: center;
  flex-direction: column;
}

.parentUnderline::after {
  content: "";
  height: 1px;
  display: block;
  width: 90%;
  margin: 0 auto;
  border-bottom: 1px solid #eee;
  padding: 1px;
}

.web-logo {
  width: 1.8rem;
  height: 0.4rem;
  background-image: url(https://puep.qpic.cn/vupload/0/20171219_1513666482477.png/0);
  background-size: cover;
}

.seachDiv {
  width: 5rem;
}

.searchIcon {
  margin-left: 0.2rem;
  font-size: 0.2rem;
}

.searchIcon:hover {
  font-size: 0.2rem;
  background-color: #414141;
}

.headerRight {
  width: 5rem;
  display: flex;
  flex-direction: row;
  justify-content: right;
  align-items: center;
}

.headerRightItem {
  padding: 0 0.2rem 0 0.2rem;
  cursor: pointer;
}

.historyText {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* position: relative; */
}

.historyBox {
  /* left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: auto; */
  position: absolute;
  top: 0.8rem;
}

a {
  text-decoration: none;
  color: #000;
}
</style>
