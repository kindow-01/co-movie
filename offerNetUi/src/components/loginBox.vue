<template>
  <div class="box" v-if="show">
    <div class="headerDiv">
      <div class="boxTitle">账号登陆</div>
      <div class="closeBox" @click="close"><span class="iconfont iconfont-guanbi"
          style="font-size: 0.3rem !important;"></span></div>
    </div>
    <div class="bodyDiv">
      <div class="form">
        <div class="loginBox" v-if="titleTag == '密码登录'">
          <div style="margin: 0.2rem;color: black;width: 4rem;text-align: center;">密码登陆</div>
          <div>
            <input class="input" v-model="userName" placeholder="手机号" />
          </div>
          <div>
            <input class="input" type="password" v-model="password" placeholder="输入密码" />
          </div>
          <div>
            <button class="button" @click="login">登陆</button>
          </div>
        </div>
        <div class="registerBox" v-if="titleTag == '注册账号'">
          <div style="margin: 0.2rem;color: black;width: 4rem;text-align: center;">注册</div>
          <div>
            <input class="input" v-model="userName" placeholder="手机号" />
          </div>
          <div>
            <input class="input" type="password" v-model="password" placeholder="输入密码" />
          </div>
          <!-- <div style="position: relative;">
            <input class="input" v-model="code" placeholder="输入短信验证码" />
            <button class="codeBut" v-if="auth_time > 0">{{ auth_time }}s后重新获取</button>
            <button class="codeBut" @click="getCode" v-else>获取验证码</button>

          </div> -->
          <div>
            <button class="button" @click="register">注册</button>
          </div>
        </div>
        <div class="action">
          <div @click="changeTab('密码登录')" v-if="titleTag != '密码登录'">密码登录</div>
          <div @click="changeTab('找回密码')" v-if="titleTag != '找回密码'">找回密码</div>
          <div @click="changeTab('注册账号')" v-if="titleTag != '注册账号'">注册账号</div>
          <div @click="changeTab('意见反馈')" v-if="titleTag != '意见反馈'">意见反馈</div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import {
  ACCESS_TOKEN,
  USER_NAME,
  USER_INFO,
  BASE_URL,
} from "../util/constants";
import api from "../util/api";
export default {
  props: {
    show: {
      type: Boolean,
      default: false
    },
  },
  components: {
    //用来注册子组件的节点
  },
  name: "loginBox",
  data() {
    return {
      input: '',
      userName: '',
      password: '',
      titleTag: '密码登录',
      auth_time: 0,
    };
  },
  created() { },
  methods: {
    getCode() {
      if (!this.userName) {
        this.$message.error("手机号不允许为空");
        return;
      }
      if (!(/^1[34578]\d{9}$/.test(this.userName))) {
        this.$message.error("手机号格式有误");
        return;
      }
      let param = {
        phone: this.userName
      }
      api.code(param).then((res) => {
        if (res.code == 200) {

          this.$message.success("模拟您手机收到的验证码为--" + res.result)

          this.auth_time = 60;
          var auth_timetimer = setInterval(() => {
            this.auth_time--;
            if (this.auth_time <= 0) {
              clearInterval(auth_timetimer);
            }
          }, 1000);

        } else {
          this.$message.error("发送失败请重新尝试--")
        }
      });
    },
    login() {
      let that = this;
      let param = {
        userName: this.userName,
        password: this.password
      }
      api.login(param).then((res) => {
        if (res.code == 200) {
          window.localStorage.setItem(ACCESS_TOKEN, JSON.stringify(res.data));
          console.log(res.data);
          this.$notify.success({
            title: '成功',
            message: res.message,
            offset: 50
          });
          this.loadUserInfo();
          that.close();
        }
        else{
          this.$notify.error({
            title: '失败',
            message: res.message,
            offset: 50
          })
        }
      });
    },
    register() {
      if (!this.userName) {
        this.$message.error("手机号不允许为空");
        return;
      }
      if (!(/^1[34578]\d{9}$/.test(this.userName))) {
        this.$message.error("手机号格式有误");
        return;
      }
      // if (!this.code) {
      //   this.$message.error("验证码不允许为空");
      //   return;
      // }
      if (!this.password) {
        this.$message.error("密码不允许为空");
        return;
      }
      let param = {
        userName: this.userName,
        password: this.password,
        // code: this.code
      }
      api.register(param).then((res) => {
        if (res.code == 200) {
          this.$message.success("注册成功");
          this.titleTag = '密码登录';
        } else {
          this.$message.error(res.message);
        }
      });
    },
    changeTab(title) {
      if (title == '意见反馈') {
        this.$message.success('谢谢你的建议！');
        return;
      }
      this.titleTag = title;
    },
    close() {
      this.userName = '';
      this.password = '';
      this.$emit('close');
    }
  },
  mounted() { },
};
</script>
 

<style scoped>
.codeBut {
  position: absolute;
  right: 0.3rem;
  top: 0;
  bottom: 0;
  margin: auto;
  font-size: 0.12rem;
  height: 0.4rem;
  border-radius: 0.2rem;
  width: 1.5rem;
  background-color: #09F;
  border: none;
}

.box {
  width: 8rem;
  background: #ffffff;
  position: fixed;
  z-index: 999999999;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.headerDiv {
  line-height: 0.6rem;
  width: 100%;
  background: #f1f1f1;
  height: 0.6rem;
  position: relative;
 }

.boxTitle {
  font-size: 0.23rem;
  color: black;
  text-align: center;
}

.closeBox {
  position: absolute;
  top: 0;
  right: 0.1rem;
  bottom: 0;
  margin: auto;
  color: rgb(92, 92, 92);
}

.input {
  font-size: 0.15rem;
  margin: 0.2rem;
  width: 4rem;
  -webkit-appearance: none !important;
  background-image: none !important;
  border-radius: 10px !important;
  border: 1px solid #DCDFE6 !important;
  -webkit-box-sizing: border-box !important;
  box-sizing: border-box !important;
  color: #000000 !important;
  display: inline-block !important;
  height: 0.5rem !important;
  line-height: 0.5rem !important;
  outline: 0 !important;
  -webkit-transition: border-color .2s cubic-bezier(.645, .045, .355, 1) !important;
  transition: border-color .2s cubic-bezier(.645, .045, .355, 1) !important;
}

.button {
  color: #ffffff;
  font-size: 0.16rem;
  margin: 0.2rem;
  cursor: pointer;
  background-color: #09F;
  width: 4rem;
  height: 0.4rem;
  border-radius: 10px;
  border: none;
  outline: none;
}

.action {
  cursor: pointer;
  font-size: 0.14rem;
  color: black;
  width: 4rem;
  margin: 0.2rem;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.bodyDiv {}

.form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
