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
      this.$store.state.windowSize = window.innerWidth;
      window.onresize = () => {
        this.$store.state.windowSize = window.innerWidth;
      }
    }
  }
};
</script>

<style>
</style>
