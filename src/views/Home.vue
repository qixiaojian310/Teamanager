<template>
  <el-container :direction="'vertical'">
    <main-header />
    <el-container>
      <el-aside
        :width="asideWidth + 'px'"
        class="aside-box-left hidden-xs-only"
        :style="{ height: asideHeight + 'px' }"
      >
          <el-scrollbar>
            <p>Your Teams</p>
            <aside-teams :team-items="teamItems"></aside-teams>
          </el-scrollbar>
          <el-scrollbar>
            <p>Your Modules</p>
            <aside-modules :module-items="moduleItems"></aside-modules>
          </el-scrollbar>
      </el-aside>
      <el-container>
        <el-main> main </el-main>
        <el-footer>Footer</el-footer>
      </el-container>
      <el-aside
        :width="asideWidth + 'px'"
        class="aside-box-right hidden-sm-and-down"
        :style="{ height: asideHeight + 'px' }"
      >
        <p>Completed Task</p>
        <aside-completed-tasks :completedTasks="completedTasks"></aside-completed-tasks>
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
import AsideCompletedTasks from "@/components/aside/AsideCompletedTasks";

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
          item: "Matlab Team asdsadas das",
        },
        {
          item: "Python Team",
        },
      ],
      completedTasks:[
        {
          taskTitle: "JS Class",
          taskContent: "JS Class JS Class JS Class JS Class JS Class JS Class",
          taskTeamName: "JS Team",
          progress: "100",
        },
        {
          taskTitle: "Java Class",
          taskContent: "Java Class Java Class Java Class Java Class Java Class Java Class",
          taskTeamName: "Java Team",
          progress: "90",
        },
        {
          taskTitle: "Matlab Class",
          taskContent: "Matlab Class Matlab Class Matlab Class Matlab Class Matlab Class Matlab Class",
          taskTeamName: "Matlab Team",
          progress: "70",
        },
        {
          taskTitle: "Python Class",
          taskContent: "Python Python Python Python Python Python Python Python Python Python",
          taskTeamName: "Python Team",
          progress: "10",
        },
      ]
    };
  },
  components: {
    MainHeader,
    AsideModules,
    AsideTeams,
    AsideCompletedTasks
  },
  computed: {
    asideHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 60;
    },
    asideWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 992) {
        return this.$store.state.windowSize.windowSizeWidth * 0.22;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.25;
      }
    },
  },
};
</script>

<style scoped>
.aside-box-left {
  box-shadow: 1px 0px 7px #888888;
}
.aside-box-left > div{
  height: 40%;
  box-shadow: 0px 0px 5px #888888 inset;
  margin: 5px;
  padding-top: 20px;
  border-radius: 5px;
  overflow-x: hidden;
  overflow-y: auto;

} 
.aside-box-left > div p{
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.aside-box-right {
  box-shadow: -1px 0px 7px #888888;
}
</style>
