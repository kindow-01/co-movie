<template>
  <div class="">
    <div class="flex-wrap">
      <div class="flex-wrap-item" v-for="(hostItem, index) in videoList" :key="index">
        <div class="hostCard" :style="getUrl(hostItem.coverVertical)" @click="goVideoDetail(hostItem.id,hostItem.setid)"></div>
        <div>
          <div class="hostTitle">{{ hostItem.setName }}</div>
          <div class="hostDescribe">{{ hostItem.setIntroductionShort }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    //用来注册子组件的节点
  },
  name: "video4",
  props: {
    videoList: {
      type: Array,
      default: [],
    },
  },
  data() {
    return {};
  },
  created() { },
  methods: {
    getUrl(str) {
      let ret = "";
      ret = 'background-size: cover;background-size: cover;background-image: url(' + '"' + str + '"' + ');';
      return ret;
    }
  },
  mounted() {

  },
};
</script>
 

<style lang="scss" scoped >
.flex-wrap {
  display: flex;
  justify-content: flex-start;

  flex-wrap: wrap;

  .flex-wrap-item {
    margin: 0 15px 0 0;
    width: calc((100% - 75px) / 5);
    min-width: calc((100% - 75px) / 5);
    max-width: calc((100% - 75px) / 5);
  }
}

.hostCard {
  height: 4rem;
  background-color: #ffffff;
  border-radius: 10px;
  cursor: pointer;
}

.hostTitle {
  padding-top: 0.1rem;
  color: #ffffff;
}

.hostDescribe {
  padding-top: 0.05rem;
  color: #747373;
  font-size: 0.18rem;
}
</style>
