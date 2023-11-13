<template>
    <div class="share-mask" v-if="show">
      <div class="share-dialog">
        <button class="close-btn" @click="close">X</button>
        <div class="share-content">
          <button @click="copyLink">复制链接</button>
          <img :src="qrCodeUrl" alt="二维码" @click="saveQrCode">
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import QRCode from 'qrcode'
  
  export default {
    name: 'Share',
    props: {
      url: {
        type: String,
        required: true
      },
      show:{
        type: Boolean,
        required: true
      }
    },
    data() {
      return {
        qrCodeUrl: '',
      }
    },
    mounted() {
      this.generateQrCode()
    },
    methods: {
      // 生成二维码图片地址
      generateQrCode() {
        QRCode.toDataURL(this.url)
          .then(url => {
            this.qrCodeUrl = url
          })
          .catch(err => {
            console.error(err)
          })
      },
      // 复制链接
      copyLink() {
        const input = document.createElement('input')
        input.value = this.url
        document.body.appendChild(input)
        input.select()
        document.execCommand('copy')
        document.body.removeChild(input)
        alert('已复制链接')
      },
      // 保存二维码图片
      saveQrCode() {
        const link = document.createElement('a')
        link.download = 'qrCode.png'
        link.href = this.qrCodeUrl
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
        alert('已保存二维码')
      },
      // 关闭弹窗
      close() {
        this.$emit('closeShare')
      }
    }
  }
  </script>
  
  <style scoped>
  .share-mask {
    z-index: 9999999999;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .share-dialog {
    background-color: #fff;
    width: 3rem;
    height: 3rem;
    border-radius: 5px;
    padding: 0.2rem;
    position: relative;
  }
  
  .close-btn {
    position: absolute;
    top: 0.1rem;
    right: 0.1rem;
    font-size: 0.2rem;
    cursor: pointer;
  }
  
  .share-content {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
  }
  
  .share-content button {
    margin-bottom: 20px;
  }
  </style>
  