<template>
  <div class="textareaBox" style="background: #f6f8fa;">
    <textarea v-model="text" placeholder="发一条友善的评论吧~" class="textarea"></textarea>
    <button class="commentBut" :style="text.length > 0 ? 'background-color: #00c8c8;' : ''" @click="clickComment">
      评论
    </button>
  </div>
</template>

<script>
export default {
  components: {
    //用来注册子组件的节点
  },
  name: "commentBox",
  props: {
    bgColor: {
      type: String,
      default: '',
    },
  },
  data() {
    return {
      text: '',
    };
  },
  created() { },
  methods: {
    clickComment() {
      if(this.text.length>0){
        this.$emit('clickComment',this.text)
        this.text='';
      }
    },
  },
  mounted() { },
};
</script>
 

<style lang="scss" scoped >
.textarea {
  line-height: 0.3rem;
  min-height: 1.2rem;

  width: 100%;
  resize: none;
  caret-color: var(--CB);
  overflow: auto;
  /* 去除边框 */
  border: none;
  /* 去除选中后的边框 */
  outline: none;
}

.textareaBox {
  background-color: var(--C8);
  padding: 12px;
  border-radius: 12px;
  height: 2rem;
  box-sizing: border-box;
}

.textarea:active {
  border: 0 !important;
}

.commentBut {
  cursor: pointer;
  width: 1rem;
  height: 0.5rem;
  line-height: 0.3rem;
  background-color: #999;
  color: #fff;
  font-size: 0.2rem;
  border-radius: 0.2rem;
  border: none;
  float: right;

}
</style>
