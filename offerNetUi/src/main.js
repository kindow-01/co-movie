// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './style/global.css'
import './util/rem'
import noData from './components/noData.vue'
import pleaseLogin from './components/pleaseLogin.vue'

import loadData from './components/loadData.vue'
import VueCoreVideoPlayer from 'vue-core-video-player'
import Vuex from 'vuex';

import {
  ACCESS_TOKEN,
  USER_NAME,
  USER_INFO,
  BASE_URL,
} from "./util/constants";
import api from "./util/api";
Vue.use(ElementUI)
Vue.config.productionTip = false
import { mapActions } from 'vuex'


Vue.component('noData', noData)
Vue.component('loadData', loadData)
Vue.component('pleaseLogin', pleaseLogin)

Vue.use(VueCoreVideoPlayer)
Vue.use(Vuex);
// 从localStorage中获取用户信息
function getUser() {
  console.log("被调用了");
  return localStorage.getItem(USER_INFO) || '{}';
}

const store = new Vuex.Store({
  state: {
    user: getUser() || '{}',
    showLoginBoxData: false,
    topBarBackground: ''
  },
  mutations: {
    setUser(state, user) {
      state.user = user
    },
    setShowLoginBoxData(state, showLoginBoxData) {
      state.showLoginBoxData = showLoginBoxData
    },
    setTopBarBackground(state, topBarBackground) {
      state.topBarBackground = topBarBackground
    }
  },
  actions: {
    setUser({ commit }, user) {
      commit('setUser', user)
    },
    setShowLoginBoxData({ commit }, showLoginBoxData) {
      commit('setShowLoginBoxData', showLoginBoxData)
    },
    setTopBarBackground({ commit }, topBarBackground) {
      commit('setTopBarBackground', topBarBackground)
    }
  },
  getters: {
    user: state => JSON.parse(state.user),
    showLoginBoxData: state => state.showLoginBoxData,
    topBarBackground: state => state.topBarBackground
  }
})



/* eslint-disable no-new */
new Vue({
  store,
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
})

Vue.prototype.loadUserInfo = function () {
  console.log("load函数被调用了")
  api.getUserInfoById().then((res) => {
    if (res.code == 200) {
      console.log(res.data);
      window.localStorage.setItem(USER_INFO, JSON.stringify(res.data));
      store.dispatch('setUser',getUser());
    }
  });
}

Vue.prototype.goVideoDetail = function ( videoId, setId) {
  //如果videoId没东西要获取一下要查询下播放历史来确定播放哪条视频
  if (!videoId) {
    api.getHistoryVideoByVideoSetId({ videoSetId: setid,notOpenLoginBox: true }).then((res) => {
      if (res.code == 200) {
        if (res.result == null) {
          res.result = undefined;
        }
        this.$router.push({
          path: "/detail",
          query: { videoId: res.data.id ,setId: res.data.setid }
        });
      }
    }).catch((err) => {
      this.$router.push({
        path: "/detail",
        query: { videoId: videoId ,setId: setId}
      });
    });

  } else {
    this.$router.push({
      path: "/detail",
      query: { videoId: videoId ,setId: setId}
    });
  }
   
}

Vue.prototype.goList = function (barName, tag) {
  this.$router.push({
    name: "list",
    query: {
      barName: barName,
      tag: tag
    },
  });
}


export default store