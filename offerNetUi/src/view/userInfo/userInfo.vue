<template>
    <div class="body">
        <userInfoBar></userInfoBar>
        <router-view></router-view>
    </div>
</template>
  
<script>
import { mapGetters, mapActions } from 'vuex'
import userInfoBar from './userInfoBar.vue'
export default {
    components: {
        //用来注册子组件的节点
        userInfoBar
    },
    data() {
        return {

        }
    },
    methods: {
    },
    mounted() {
        this.$store.dispatch('setTopBarBackground', '#333333fc');
    },
    beforeDestroy() {
        this.$store.dispatch('setTopBarBackground', '');
    },
}
</script>
  
<style scoped>
.body {
    padding-top: 1rem;
    color: #000;
    width: 16.5rem;
    left: 0;
    right: 0;
    margin: 0 auto;
    display: flex;
    flex-direction: row;
}
</style>