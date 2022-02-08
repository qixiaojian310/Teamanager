<template>
  <el-container :direction="'vertical'">
    <main-header />
    <el-container>
      <el-aside
        :width="asideWidth+'px'"
        class="aside-box-left hidden-xs-only"
        :style="{ height: asideHeight + 'px' }"
      >
        <aside-teams :team-items="teamItems"></aside-teams>
      </el-aside>
      <el-container>
        <el-main> main </el-main>
        <el-footer>Footer</el-footer>
      </el-container>
      <el-aside
        :width="asideWidth+'px'"
        class="aside-box-right hidden-sm-and-down"
        :style="{ height: asideHeight + 'px' }"
      >
        <aside-modules :module-items="moduleItems"></aside-modules>
      </el-aside>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import MainHeader from "@/components/header/MainHeader.vue";
import AsideModules from "@/components/aside/AsideModules";
import AsideTeams from "@/components/aside/AsideTeams";

export default {
  name: "Home",
  data() {
    return {
      country: "china",
      // 左边放module的信息，右边放team的
      moduleItems: [
        {
          item: "JS Class",
        },
        {
          item: "Java Class",
        },
        {
          item: "Matlab Class",
        },
        {
          item: "Python Class",
        },
      ],
      // 左边放module的信息，右边放team的
      teamItems: [
        {
          item: "JS Team",
        },
        {
          item: "Java Team",
        },
        {
          item: "Matlab Team",
        },
        {
          item: "Python Team",
        },
      ],
    };
  },
  components: {
    MainHeader,
    AsideModules,
    AsideTeams,
  },
  computed: {
    asideHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 60;
    },
    asideWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 992) {
        return this.$store.state.windowSize.windowSizeWidth*0.22;
      } else {
        return this.$store.state.windowSize.windowSizeWidth*0.25;
      }
},
  },
};
</script>

<style scoped>
.aside-box-left {
  box-shadow: 1px 0px 7px #888888;
}
.aside-box-right {
  box-shadow: -1px 0px 7px #888888;
}
</style>