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
      <el-container :style="{ height: asideHeight + 'px' }">
        <el-scrollbar >
          <el-main>
            <!-- show your task haven't done  -->
            <el-container>
              <el-row>
                <el-col :span="20" :offset="2">
                  <un-completed-tasks
                    :unCompletedTasks="unCompletedTasks"
                  ></un-completed-tasks>
                </el-col>
              </el-row>
            </el-container>
          </el-main>
          <el-footer>Footer</el-footer>
        </el-scrollbar>
      </el-container>
      <el-aside
        :width="asideWidth + 'px'"
        class="aside-box-right hidden-sm-and-down"
        :style="{ height: asideHeight + 'px' }"
      >
        <p>Completed Task</p>
        <aside-completed-tasks
          :completedTasks="completedTasks"
        ></aside-completed-tasks>
      </el-aside>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import MainHeader from "@/components/Home/header/MainHeader.vue";
import AsideModules from "@/components/Home/aside/AsideModules";
import AsideTeams from "@/components/Home/aside/AsideTeams";
import AsideCompletedTasks from "@/components/Home/aside/AsideCompletedTasks";
import UnCompletedTasks from "@/components/Home/main/UnCompletedTasks";

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
      completedTasks: [
        {
          taskTitle: "JS Class",
          taskContent: "JS Class JS Class JS Class JS Class JS Class JS Class",
          taskTeamName: "JS Team",
          progress: "100",
        },
        {
          taskTitle: "Java Class",
          taskContent:
            "Java Class Java Class Java Class Java Class Java Class Java Class",
          taskTeamName: "Java Team",
          progress: "90",
        },
        {
          taskTitle: "Matlab Class",
          taskContent:
            "Matlab Class Matlab Class Matlab Class Matlab Class Matlab Class Matlab Class",
          taskTeamName: "Matlab Team",
          progress: "70",
        },
        {
          taskTitle: "Python Class",
          taskContent:
            "Python Python Python Python Python Python Python Python Python Python",
          taskTeamName: "Python Team",
          progress: "10",
        },
      ],
      unCompletedTasks: [
        {
          unCompletedTaskName: "JS Code Implementation",
          unCompletedTaskContent:
            "JS Code Implementation Amet irure laborum magna eiusmod. Nostrud commodo reprehenderit" +
            "nisi nulla ea esse enim enim reprehenderit ea nulla. Culpa eu anim consectetur nulla nostrud ex" +
            "Lorem aliqua elit commodo labore sunt incididunt. Consectetur ullamco excepteur ex sint irure dol" +
            "or voluptate. Esse eiusmod incididunt minim eu quis minim amet. Veniam laboris nulla duis molli" +
            " consequat cillum eiusmod qui est Lorem nulla reprehenderit. Ea consequat aliqua amet tempor occaecat" +
            " magna. Esse sunt aute anim deserunt est veniam minim ad incididunt deserunt. Commodo officia" +
            " excepteur ullamco anim culpa laborum magna in voluptate aute sint proident cillum aliqua. Nisi" +
            " sit aliqua nisi proident dolor ad.Ex nostrud exercitation occaecat non adipisicing laborum. Aute elit" +
            " id pariatur ipsum do dolor nisi sit fugiat esse culpa irure labore. Anim culpa ad cillum dolore ipsum" +
            " velit tempor elit.",
          unCompletedTaskTeamName: "JS Team",
          cooperator: [1, 2, 3, 4],
          deadline: "2022-05-08",
          progress: "10",
        },
        {
          unCompletedTaskName: "JS Code Implementation",
          unCompletedTaskContent:
            "JS Code Implementation Amet irure laborum magna eiusmod. Nostrud commodo reprehenderit" +
            "nisi nulla ea esse enim enim reprehenderit ea nulla. Culpa eu anim consectetur nulla nostrud ex" +
            "Lorem aliqua elit commodo labore sunt incididunt. Consectetur ullamco excepteur ex sint irure dol" +
            "or voluptate. Esse eiusmod incididunt minim eu quis minim amet. Veniam laboris nulla duis molli" +
            " consequat cillum eiusmod qui est Lorem nulla reprehenderit. Ea consequat aliqua amet tempor occaecat" +
            " magna. Esse sunt aute anim deserunt est veniam minim ad incididunt deserunt. Commodo officia" +
            " excepteur ullamco anim culpa laborum magna in voluptate aute sint proident cillum aliqua. Nisi" +
            " sit aliqua nisi proident dolor ad.Ex nostrud exercitation occaecat non adipisicing laborum. Aute elit" +
            " id pariatur ipsum do dolor nisi sit fugiat esse culpa irure labore. Anim culpa ad cillum dolore ipsum" +
            " velit tempor elit.",
          unCompletedTaskTeamName: "JS Team",
          cooperator: [1, 2, 3, 4, 5, 6, 7],
          deadline: "2022-05-01",
          progress: "10",
        },
        {
          unCompletedTaskName: "JS Code Implementation",
          unCompletedTaskContent:
            "JS Code Implementation Amet irure laborum magna eiusmod. Nostrud commodo reprehenderit" +
            "nisi nulla ea esse enim enim reprehenderit ea nulla. Culpa eu anim consectetur nulla nostrud ex" +
            "Lorem aliqua elit commodo labore sunt incididunt. Consectetur ullamco excepteur ex sint irure dol" +
            "or voluptate. Esse eiusmod incididunt minim eu quis minim amet. Veniam laboris nulla duis molli" +
            " consequat cillum eiusmod qui est Lorem nulla reprehenderit. Ea consequat aliqua amet tempor occaecat" +
            " magna. Esse sunt aute anim deserunt est veniam minim ad incididunt deserunt. Commodo officia" +
            " excepteur ullamco anim culpa laborum magna in voluptate aute sint proident cillum aliqua. Nisi" +
            " sit aliqua nisi proident dolor ad.Ex nostrud exercitation occaecat non adipisicing laborum. Aute elit" +
            " id pariatur ipsum do dolor nisi sit fugiat esse culpa irure labore. Anim culpa ad cillum dolore ipsum" +
            " velit tempor elit.",
          unCompletedTaskTeamName: "JS Team",
          cooperator: [2, 3, 4],
          deadline: "2022-05-01",
          progress: "10",
        },
        {
          unCompletedTaskName: "JS Code Implementation",
          unCompletedTaskContent:
            "JS Code Implementation Amet irure laborum magna eiusmod. Nostrud commodo reprehenderit" +
            "nisi nulla ea esse enim enim reprehenderit ea nulla. Culpa eu anim consectetur nulla nostrud ex" +
            "Lorem aliqua elit commodo labore sunt incididunt. Consectetur ullamco excepteur ex sint irure dol" +
            "or voluptate. Esse eiusmod incididunt minim eu quis minim amet. Veniam laboris nulla duis molli" +
            " consequat cillum eiusmod qui est Lorem nulla reprehenderit. Ea consequat aliqua amet tempor occaecat" +
            " magna. Esse sunt aute anim deserunt est veniam minim ad incididunt deserunt. Commodo officia" +
            " excepteur ullamco anim culpa laborum magna in voluptate aute sint proident cillum aliqua. Nisi" +
            " sit aliqua nisi proident dolor ad.Ex nostrud exercitation occaecat non adipisicing laborum. Aute elit" +
            " id pariatur ipsum do dolor nisi sit fugiat esse culpa irure labore. Anim culpa ad cillum dolore ipsum" +
            " velit tempor elit.",
          unCompletedTaskTeamName: "JS Team",
          cooperator: [1, 2, 4],
          deadline: "2022-05-01",
          progress: "10",
        },
        {
          unCompletedTaskName: "JS Code Implementation",
          unCompletedTaskContent:
            "JS Code Implementation Amet irure laborum magna eiusmod. Nostrud commodo reprehenderit" +
            "nisi nulla ea esse enim enim reprehenderit ea nulla. Culpa eu anim consectetur nulla nostrud ex" +
            "Lorem aliqua elit commodo labore sunt incididunt. Consectetur ullamco excepteur ex sint irure dol" +
            "or voluptate. Esse eiusmod incididunt minim eu quis minim amet. Veniam laboris nulla duis molli" +
            " consequat cillum eiusmod qui est Lorem nulla reprehenderit. Ea consequat aliqua amet tempor occaecat" +
            " magna. Esse sunt aute anim deserunt est veniam minim ad incididunt deserunt. Commodo officia" +
            " excepteur ullamco anim culpa laborum magna in voluptate aute sint proident cillum aliqua. Nisi" +
            " sit aliqua nisi proident dolor ad.Ex nostrud exercitation occaecat non adipisicing laborum. Aute elit" +
            " id pariatur ipsum do dolor nisi sit fugiat esse culpa irure labore. Anim culpa ad cillum dolore ipsum" +
            " velit tempor elit.",
          unCompletedTaskTeamName: "JS Team",
          cooperator: [2, 3],
          deadline: "2022-05-01",
          progress: "10",
        },
      ],
    };
  },
  components: {
    MainHeader,
    AsideModules,
    AsideTeams,
    AsideCompletedTasks,
    UnCompletedTasks,
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
.aside-box-left > div {
  height: 40%;
  box-shadow: 0px 0px 10px #3d458b inset;
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 20px;
  border-radius: 10px;
  overflow-x: hidden;
  overflow-y: auto;
}
.aside-box-left > div p {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.aside-box-right {
  box-shadow: -1px 0px 7px #888888;
  overflow: hidden;
}
</style>
