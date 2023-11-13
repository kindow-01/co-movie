<template>
  <div class="modal" v-show="showModal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <div class="payment-code">
        <img src="./../assets/weixinpay.jpg" alt="微信收款码">
        <img src="./../assets/alipay.jpg" alt="支付宝收款码">
        <img src="./../assets/wx.jpg" alt="微信二维码">
        <img src="./../assets/tiktok.jpg" alt="抖音二维码">
      </div>

      <div style="padding-top: 0.5rem;">
        <div class="text">
          开源不易，一块两块不嫌少，十倍八倍不嫌多
          捐赠者携带截图可以获得相应技术支持，
        </div>
        <div class="text">
          资金什么时候充裕了，搞个好点的服务器
        </div>
        <div class="text">
          添加微信请注明来意（1.本项目交流群 2.编程交流群 3.需要技术支持）
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
export default {
  data() {
    return {
      showModal: false
    }
  },
  methods: {
    openModal() {
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    }
  }
}
</script>
  
<style>
.modal {
  position: fixed;
  z-index: 999999999999;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #ccc;
  width: 80%;
  max-width: 14rem;
  text-align: center;
}

.close {
  color: #aaa;
  float: right;
  font-size: 0.2rem;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: #000;
}

.payment-code {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.payment-code img {
  width: 100%;
  max-width: 3rem;
  height: auto;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.text {
  color: black;
}
</style>