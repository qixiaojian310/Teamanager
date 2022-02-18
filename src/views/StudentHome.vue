<template>
  <el-container :direction="'vertical'">
    <main-header />
  <router-view></router-view>
  </el-container>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import MainHeader from "../components/Home/header/MainHeader.vue";

export default {
  name: "StudentHome",
  components: {
    MainHeader,
  },
};
</script>

<style scoped>
</style>
