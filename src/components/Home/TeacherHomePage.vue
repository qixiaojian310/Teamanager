<template>
      <el-container>
      <el-aside
        :width="asideLeftWidth + 'px'"
        class="aside-box-left hidden-sm-and-down"
        :style="{ height: asideHeight + 'px' }"
      >
        <el-scrollbar :view-style="[{height:100+'%'}]">
          <div class="scroll-bar-right">
            <el-scrollbar :wrap-class="'scroll-bar-right'">
              <p>Your Teams</p>
              <aside-teams :team-items="teamItems"></aside-teams>
            </el-scrollbar>
          </div>
          <div class="scroll-bar-right">
            <el-scrollbar :wrap-class="'scroll-bar-right'">
              <p>Your Modules</p>
              <aside-modules :module-items="moduleItems"></aside-modules>
            </el-scrollbar>
          </div>
          <div class="scroll-bar-right">
            <aside-completed-tasks :completedTasks="completedTasks">
              <p>Completed Task</p>
            </aside-completed-tasks>
          </div>
        </el-scrollbar>
      </el-aside>
      <el-container :style="{ height: asideHeight + 'px' }">
        <el-scrollbar>
          <el-main>
            <!-- show your task haven't done  -->
            <el-container>
              <el-row style="width: 100%">
                <el-col :span="20" :offset="2">
                  <un-completed-tasks
                    :unCompletedTasks="unCompletedTasks"
                  ></un-completed-tasks>
                </el-col>
              </el-row>
            </el-container>
          </el-main>
        </el-scrollbar>
      </el-container>
      <el-aside
        :width="asideRightWidth + 'px'"
        class="aside-box-right hidden-md-and-down"
        :style="{ height: asideHeight + 'px' }"
      >
        
      </el-aside>
    </el-container>
</template>
<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import AsideModules from "../Home/aside/AsideModules.vue";
import AsideTeams from "../Home/aside/AsideTeams.vue";
import AsideCompletedTasks from "../Home/aside/AsideCompletedTasks.vue";
import UnCompletedTasks from "../Home/main/UnCompletedTasks.vue";
import StudentInfo from "../Home/aside/StudentInfo.vue";

export default {
  name: "TeacherHomePage",
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
          progress: 100,
        },
        {
          taskTitle: "Java Class",
          taskContent:
            "Java Class Java Class Java Class Java Class Java Class Java Class",
          taskTeamName: "Java Team",
          progress: 90,
        },
        {
          taskTitle: "Matlab Class",
          taskContent:
            "Matlab Class Matlab Class Matlab Class Matlab Class Matlab Class Matlab Class",
          taskTeamName: "Matlab Team",
          progress: 70,
        },
        {
          taskTitle: "Python Class",
          taskContent:
            "Python Python Python Python Python Python Python Python Python Python",
          taskTeamName: "Python Team",
          progress: 10,
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
          cooperator: ["12345", "34567", "34785", "33245"],
          deadline: "2022-05-08",
          progress: 10,
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
          cooperator: [
            "12345",
            "34567",
            "34785",
            "33245",
            "12645",
            "34895",
            "34580",
          ],
          deadline: "2022-05-01",
          progress: 20,
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
          cooperator: ["12345", "34567", "34785", "34580"],
          deadline: "2022-05-01",
          progress: 30,
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
          cooperator: ["12645", "34895", "34580"],
          deadline: "2022-05-01",
          progress: 40,
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
          cooperator: ["12345", "34567", "34785", "34895", "34580"],
          deadline: "2022-05-01",
          progress: 50,
        },
      ],
      signInStudentId:'12345'
    };
  },
  components: {
    AsideModules,
    AsideTeams,
    AsideCompletedTasks,
    UnCompletedTasks,
    StudentInfo
  },
  computed: {
    asideHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 60;
    },
    asideLeftWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 1200) {
        return this.$store.state.windowSize.windowSizeWidth * 0.3;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.4 ;
      }
    },
  },
};
</script>

<style scoped>
.aside-box-left {
  /* box-shadow: 1px 0px 7px #888888; */
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(#DF7599,#3994BE);
}
.aside-box-left > div {
  height: 95%;
  width: 90%;
  /* box-shadow: 0px 0px 14px 2px #3994BE inset; */
  background: #fff;
  margin-left: 20px;
  margin-right: 20px;
  padding-top: 20px;
  border-radius: 10px;
  overflow-x: hidden;
  overflow-y: auto;
  background-color: rgba(255, 255, 255, 0.3);
}
.aside-box-left > div p {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.aside-box-right {
  background: linear-gradient(#FFDAE6,#3994BE);
  box-shadow: -1px 0px 7px #888888;
  overflow: hidden;
}
.scroll-bar-right {
  background: #e0e0e0;
  height: 30%;
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 20px;
  border-radius: 10px;
  overflow-x: hidden;
  overflow-y: auto;
}
.scroll-bar-right > div p {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.el-main{
  background: #9EB8D2;
  color: black;
  font-size: large;
  background-size: 50px 50px, 50px 50px; /* grid size */
  background-image:
                /* horizontal line */
                /* the first size is defines horizontal line,while the second defines transeparent */ linear-gradient(
      90deg,
      rgba(156, 156, 156, 0.5) 2px,
      transparent 2px
    ),
    /* vertical line */ linear-gradient(0, rgba(155, 155, 155, 0.5) 2px, transparent 2px);
}
/* #399448 #3994BE #B2E8F7 #FFDAE6 #DF7599 */
</style>
