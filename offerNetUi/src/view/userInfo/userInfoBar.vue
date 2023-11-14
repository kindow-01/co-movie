<template>
    <div class="barBody">

        <div class="userCard">

            <div>
                <boboCropper @change="avatarChange" v-model="user.avatar" :aspectRatio="1 / 1"></boboCropper>
                <div v-if="!nameInputShow" @click="nameInputShow = true;">{{ user.realName }}</div>
                <input v-else v-model=" user.realName " @blur=" nameInpuptBlur " />
            </div>
        </div>
        <div class="barContent">
            <router-link :to=" { path: '/history' } ">
                <div class="barItem"><i class="el-icon-star-on barContentIcon"></i>
                    <div>观看历史</div>
                </div>
            </router-link>
            <router-link :to=" { path: '/chasingDrama' } ">
                <div class="barItem"><i class="el-icon-star-on barContentIcon"></i>
                    <div>在追</div>
                </div>
            </router-link>
            <router-link :to=" { path: '/collect' } ">
                <div class="barItem"><i class="el-icon-star-on barContentIcon"></i>
                    <div>收藏</div>
                </div>
            </router-link>
            <router-link :to=" { path: '/comment' } ">
                <div class="barItem"><i class="el-icon-star-on barContentIcon"></i>
                    <div>评论</div>
                </div>
            </router-link>



        </div>
    </div>
</template>
  
<script>
import boboCropper from "@/components/boboCropper"
import api from "@/util/api"
import { mapGetters, mapActions } from 'vuex'
export default {
    components: {
        //用来注册子组件的节点
        boboCropper
    },
    data() {
        return {
            avater: '',
            name: '',
            nameInputShow: false,
        }
    },
    methods: {
        nameInpuptBlur() {
            this.nameInputShow = false;
            this.updateUserInfo();
        },
        updateUserInfo() {
            api.updateUser(this.user).then((res) => {
                if (res.code == 200) {
                    //刷新一下用户信息
                    this.loadUserInfo();
                }
            })
        },
        avatarChange(avatar) {
            //已经双向绑定了，直接调用修改用户信息的方法
            this.updateUserInfo();
        }
    },
    computed: {
        ...mapGetters([
            'user'
        ])
    },
    mounted() {

    },
    beforeDestroy() {

    },
}
</script>
  
<style scoped>
.router-link-active {
    text-decoration: none;
    color: orange;
}

a {
    text-decoration: none;
    color: #5b5b5b;
}

.barBody {
    color: #000;
    width: 4rem;
}

.userCard {
    width: 4rem;
    height: 3.8rem;
    border-radius: 4px 4px 0 0;
    /* background: #fbf2ef url(/tencentvideo/vstyle/web/v6/style/img/user/bg_default.png) 0 0 no-repeat; */
    background-image: -webkit-image-set(url('../../assets/userCardBG.png') 2x);
    background-size: 100% auto;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.barContent {
    background: rgb(244, 240, 240);
    color: #5b5b5b;
    border-radius: 0 0 40px 4px;
}

.barContentIcon {
    padding-right: 0.15rem;
}

.barItem {
    display: flex;
    flex-direction: row;
    align-items: center;
    font-size: 0.2rem;
    padding: 0.2rem;
    cursor: pointer;
}

.barItem:hover {
    color: orange;
}
</style>