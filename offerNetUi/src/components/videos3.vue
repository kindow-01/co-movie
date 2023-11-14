<template>
  <div class="">
    <div class="">
      <div class="">
        <div class="flex-wrap" v-if="videoList.length > 0">
          <div class="flex-wrap-item" v-for="(videoItem, index) in videoList" :key="index">
            <div class="bigCard" :style="getUrl(videoItem.cover)" @click="goVideoDetail(videoItem.id,videoItem.setid)"></div>
            <div class="bigCardBottom">
              <div class="bigCardBottomContent">
                <div class="g_row_and_center_between">
                  <div class="hostTitle">{{ videoItem.title }}</div>
                  <div>
                    <el-button :disabled="dramaAddOrDelLoad" v-if="!videoItem.chasingDrama" type="primary" round icon="el-icon-plus"
                      @click="chasingDramaAddOrDel(videoItem.id,index)">追</el-button>
                    <el-button :disabled="dramaAddOrDelLoad" v-else type="primary" round @click="chasingDramaAddOrDel(videoItem.id,index)">在追</el-button>
                  </div>
                </div>

                <div class="hostDescribe">{{ videoItem.describe }}</div>
              </div>
            </div>
          </div>
        </div>
        <no-data v-else message="没有找到相关数据"></no-data>
      </div>
    </div>
  </div>
</template>

<script>
import api from "../util/api"
export default {
  components: {
    //用来注册子组件的节点
  },
  name: "video3",
  props: {
    videoList: {
      type: Array,
      default: [],
    },
  },
  data() {
    return {
      dramaAddOrDelLoad:false,
    };
  },
  created() { },
  methods: {
    chasingDramaAddOrDel(videoSetId,index) {
      this.dramaAddOrDelLoad=true;
      let param = {
        videoSetId
      }
      api.chasingDramaAddOrDel(param).then((res) => {
        if (res.code == 200) {
          this.$emit("chasingDramaAddOrDelOk",index)
          this.dramaAddOrDelLoad=false;
        }
      }).catch((err) => {
        this.dramaAddOrDelLoad=false;
      })
    },
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
.bigCard {
  cursor: pointer;
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
