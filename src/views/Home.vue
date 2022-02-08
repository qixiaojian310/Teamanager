<template>
  <el-container :direction="'vertical'">
    <main-header />
    <el-container>
      <el-aside width="200px">
        <aside-modules></aside-modules>
      </el-aside>
      <el-container>
        <el-main> main </el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import MainHeader from "@/components/header/MainHeader.vue";
import AsideModules from "@/components/aside/AsideModules";

export default {
  name: "Home",
  data() {
    return {
      country: "china",
    };
  },
  components: {
    MainHeader,
    AsideModules,
  },
};
</script>
MainHeader
