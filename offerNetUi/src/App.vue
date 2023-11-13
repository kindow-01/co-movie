<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {

  },
}
</script>

<style>
@import url('../src/assets/icon/iconfont.css');

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #fbfbfb;
  /* margin-top: 60px; */
  font-size: 0.2rem;
  min-width: 1200px;
}

* {
  margin: 0;
  padding: 0;
}
</style>
