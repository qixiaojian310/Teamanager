<template>
  <el-container :direction="'vertical'">
    <colorful-header />
  <router-view></router-view>
  </el-container>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
// import MainHeader from "../components/Home/header/MainHeader.vue";
import ColorfulHeader from "@/components/Home/header/ColorfulHeader.vue";

export default {
  name: "StudentHome",
  components: {
    ColorfulHeader,
  },
};
</script>

<style scoped>
</style>
