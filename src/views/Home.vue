<template>
  <el-container :direction="'vertical'">
    <main-header />
    <el-container>
      <el-aside width="200px">Aside</el-aside>
      <el-container>
        <el-main>
        </el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import MainHeader from "@/components/header/MainHeader.vue";

export default {
  name: "Home",
  data() {
    return {
      country: "china",
    };
  },
  components: {
    MainHeader,
  },
};
</script>
    MainHeader
