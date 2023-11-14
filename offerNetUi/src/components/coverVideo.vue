<template>
  <div class="">
    <div class="videoDiv">
      <video id="videoPlayer">
      </video>
      <div class="video-mask"></div>
    </div>

  </div>
</template>
  
<script>
import videojs from "video.js";
import "video.js/dist/video-js.css";
export default {
  name: 'coverVideo',
  props: {
    coverVideoUrl: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      dp: null,
      options: {
        autoplay: true, // 设置自动播放
        muted: true, // 设置了它为true，才可实现自动播放,同时视频也被静音（Chrome66及以上版本，禁止音视频的自动播放）
        preload: "auto", // 预加载
        controls: false, // 显示播放的控件
        loop: true,//循环播放
      },
    };
  },
  //页面销毁前解除监听
  beforeDestroy() {
    this.beforeDestroy1();
  },
  methods: {
    loadVideo() {
      if (!this.coverVideoUrl || this.coverVideoUrl == '') {
        return;
      }
      // debugger;
      this.db = videojs("videoPlayer", this.options);
      // 也可以使用以下方式给vedio设置 src
      this.db.src([
        {
          src: this.coverVideoUrl, // 地址
          type: "application/x-mpegURL", // 告诉videojs,这是一个hls流
        },
      ]);

      // this.db.addEvent("ended", function () {
      //   this.db.play();
      // });
    },
    // 销毁
    beforeDestroy1() {
      if (this.db) {
        this.db.dispose(); // dispose()会直接删除Dom元素
      }
    },
  },
  mounted() {
    this.loadVideo();
  },
  watch: {
    // 被侦听的变量count
    coverVideoUrl() {
      console.log('coverVideoUrl 发生了变化');
      this.loadVideo();
    }
  }
}
</script>
  
<style lang="scss" scoped>
.videoDiv {
  position: absolute;
  z-index: 0;
  left: 0;
  right: 0;
  margin: auto;
}

.video-mask {
  position: absolute;
  top: 0;
  height: 100%;
  width: 100%;
  background-image: linear-gradient(180deg, rgba(0, 0, 0, 0) 0, #000000 70%);
}

/deep/ .videoPlayer-dimensions {
  width: 100%;
  height: 10.85rem;
}

/deep/ .vjs-tech {
  width: 100%;
  height: 100%;
}
</style>