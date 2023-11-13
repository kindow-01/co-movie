<template>
    <div class="no-data">
      <img src="../assets/noData.png" alt="暂无数据" class="no-data-img">
      <p class="no-data-text">{{ message }}</p>
    </div>
  </template>
  
  <script>
  export default {
    name: 'NoData',
    props: {
      message: {
        type: String,
        default: '暂无数据'
      }
    }
  }
  </script>
  
  <style scoped>
  .no-data {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
    padding: 0.2rem;
  }
  
  .no-data-img {
    width: 2rem;
    height: 2rem;
    margin-bottom: 0.2rem;
  }
  
  .no-data-text {
    font-size: 0.16rem;
    color: #999;
  }
  </style>