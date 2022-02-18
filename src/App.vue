<template>
  <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </div> -->
  <div>
    <router-view />
  </div>
</template>

<script>

export default {
  name: "App",
  mounted() {
    this.reloadChange()
  },
  methods:{
    //监听窗口大小变化
    reloadChange() {
      this.$store.state.windowSize.windowSizeWidth = window.innerWidth;
      this.$store.state.windowSize.windowSizeHeight = window.innerHeight;
      window.onresize = () => {
        this.$store.state.windowSize.windowSizeWidth = window.innerWidth;
        this.$store.state.windowSize.windowSizeHeight = window.innerHeight;
      }
    }
  }
};
</script>

<style>
.el-scrollbar__thumb {
background-color: #0988ff !important;
border-radius: 3px;
}
.el-scrollbar__bar{
  width: 4px !important;
}
</style>
