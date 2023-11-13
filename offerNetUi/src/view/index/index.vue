<template>
  <div>
    <div>

      <top-bar></top-bar>
   
      <router-view/>
    </div>
  </div>
</template>

<script>
import topBar from "../../components/topBar/topBar.vue";
import leftBar from "../../components/leftBar/leftBar.vue";

export default {
  components: {
    //用来注册子组件的节点
    "top-bar": topBar,
    "left-bar": leftBar,
  },
  name: "index",
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
    };
  },
  created() { },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style scoped>
.bgImg {
  /* background: url("../../assets/背景1.jpg") center center / cover no-repeat; */
  height: 100vh;
  background-color: black;
}

.phoneDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /*在水平和垂直方向上各偏移-50%*/
}

.player {
  background-color: #fff;
}
</style>

