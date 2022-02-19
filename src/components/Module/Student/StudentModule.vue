<template>
  <el-container>
    <!-- 工具栏 -->
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="hidden-sm-and-down"
      :style="{ height: asideHeight + 'px' }"
    >
      <student-toolbar></student-toolbar>
    </el-aside>
    <el-main>
    </el-main>
  </el-container>
</template>

<script>
import StudentToolbar from './StudentToolbar.vue'

export default {
  name: "StudentModule",
  components:{
  },
  computed:{
    asideHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 60;
    },
    asideLeftWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 1200) {
        return this.$store.state.windowSize.windowSizeWidth * 0.2;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.3;
      }
    },
  },
  components:{
    StudentToolbar,
  },
};
</script>

<style>
</style>