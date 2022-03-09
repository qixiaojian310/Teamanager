<template>
  <el-container>
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="aside-box-left hidden-sm-and-down"
      :style="{ height: asideHeight + 'px' }"
    >
      <student-info :signInStudentId="signInStudentId"></student-info>
    </el-aside>
    <el-container :style="{ height: asideHeight + 'px' }">
      <el-scrollbar :style="{ width: 100 + '%' }">
        <el-main>
          <!-- show your task haven't done  -->
          <el-container :style="{ height: asideHeight + 'px' }">
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
      <el-scrollbar :view-style="[{ height: 100 + '%' }]">
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
import { getCookie } from "../../util/cookieUtil";

export default {
  name: "StudentHomePage",
  data() {
    return {
      country: "china",
      // 左边放module的信息，右边放team的
      moduleItems: [],
      // 左边放module的信息，右边放team的
      teamItems: [],
      // taskTitle taskContent taskTeamName progress
      completedTasks: [],
      // unCompletedTaskName,unCompletedTaskContent,unCompletedTaskTeamName,deadline,progress
      unCompletedTasks: [
        // {
        //   unCompletedTaskName: "JS Code Implementation",cooperator
        //   unCompletedTaskContent:
        //     "JS Code Implementation Amet irure laborum magna eiusmod. Nostrud commodo reprehenderit" +
        //     "nisi nulla ea esse enim enim reprehenderit ea nulla. Culpa eu anim consectetur nulla nostrud ex" +
        //     "Lorem aliqua elit commodo labore sunt incididunt. Consectetur ullamco excepteur ex sint irure dol" +
        //     "or voluptate. Esse eiusmod incididunt minim eu quis minim amet. Veniam laboris nulla duis molli" +
        //     " consequat cillum eiusmod qui est Lorem nulla reprehenderit. Ea consequat aliqua amet tempor occaecat" +
        //     " magna. Esse sunt aute anim deserunt est veniam minim ad incididunt deserunt. Commodo officia" +
        //     " excepteur ullamco anim culpa laborum magna in voluptate aute sint proident cillum aliqua. Nisi" +
        //     " sit aliqua nisi proident dolor ad.Ex nostrud exercitation occaecat non adipisicing laborum. Aute elit" +
        //     " id pariatur ipsum do dolor nisi sit fugiat esse culpa irure labore. Anim culpa ad cillum dolore ipsum" +
        //     " velit tempor elit.",
        //   unCompletedTaskTeamName: "JS Team",
        //   cooperator: [1, 2, 3, 4, 5],
        //   deadline: "2022-05-01",
        //   progress: 50,
        // },
      ],
      signInStudentId: 1,
    };
  },
  methods: {
    //TODO - 该方法负责直接将所有的当前用户的所有组注入到vuex中
    injectTeams: function () {
      this.axios({
        url: "/getAllTeam",
        data: {
          studentId: this.$store.state.signInStudent.name,
        },
        method: "post",
      }).then((response) => {
        var injectTeams = response.data;
        this.$store.commit("clearTeams");
        for (let index = 0; index < injectTeams.length; index++) {
          var tempTeamObj = new Object();
          tempTeamObj.name = injectTeams[index].teamName;
          tempTeamObj.teamId = injectTeams[index].teamId;
          tempTeamObj.leaderId = injectTeams[index].leaderId;
          tempTeamObj.students = injectTeams[index].studentList;
          tempTeamObj.available = injectTeams[index].available;
          tempTeamObj.moduleId = injectTeams[index].moduleId;
          tempTeamObj.taskList = injectTeams[index].taskList;
          this.teamItems.push(tempTeamObj);
          this.$store.state.teams.push(tempTeamObj);
        }
      });
    },
    // TODO - 该方法负责直接将所有的当前用户的所有课程注入到vuex中
    injectModule: function () {
      this.axios({
        url: "/getAllModule",
        data: {
          studentId: this.$store.state.signInStudent.name,
        },
        method: "post",
      }).then((response) => {
        var injectModules = response.data;
        this.moduleItems = response.data;
        this.$store.commit("injectStudentModules", injectModules);
        // for (let index = 0; index < injectModules.length; index++) {
        //   var tempModuleObj = new Object();
        //   //绑定name
        //   tempModuleObj.moduleName = injectModules[index].moduleName;
        //   //绑定id
        //   tempModuleObj.moduleId = injectModules[index].moduleId;
        //   tempModuleObj.teacher = injectModules[index].teacher;
        //   tempModuleObj.students = injectModules[index].students;
        //   tempModuleObj.teamIds = injectModules[index].teamIds;
        //   tempModuleObj.teamNum = injectModules[index].teamNum;
        //   tempModuleObj.moduleSize = injectModules[index].moduleSize;
        //   this.moduleItems.push(tempModuleObj);
        //   this.$store.commit("pushStudentModule",tempModuleObj);
        // }
      });
    },
    //该方法负责初始化所有的uncompleted task
    injectUnCompletedTasks: function () {
      this.axios({
        url: "/getUnCompletedTaskByStudentId",
        data: {
          studentId: this.$store.state.signInStudent.name,
        },
        method: "post",
        // baseURL: "http://localhost:8080/api/",
      }).then((response) => {
        // cooperator unCompletedTaskName,unCompletedTaskContent,unCompletedTaskTeamName,deadline,progress
        var UnCompletedTasksInject = response.data;
        for (let index = 0; index < UnCompletedTasksInject.length; index++) {
          var UnCompletedTaskObj = new Object();
          UnCompletedTaskObj.unCompletedTaskName =
            UnCompletedTasksInject[index].taskName;
          UnCompletedTaskObj.unCompletedTaskContent =
            UnCompletedTasksInject[index].context;
          for (let i = 0; i < this.$store.state.teams.length; i++) {
            if (
              this.$store.state.teams[i].teamId ==
              UnCompletedTasksInject[index].teamId
            ) {
              UnCompletedTaskObj.unCompletedTaskName =
                this.$store.state.teams[i].name;
            }
          }
          UnCompletedTaskObj.progress = UnCompletedTasksInject[index].progress;
          var deadlineTimestamp = UnCompletedTasksInject[index].deadline;
          var time = new Date(deadlineTimestamp);
          var year = time.getFullYear();
          var month =
            time.getMonth() + 1 < 10
              ? "0" + (time.getMonth() + 1)
              : time.getMonth() + 1;
          var day = time.getDate();
          var dateString = year + "-" + month + "-" + day;
          UnCompletedTaskObj.deadline = dateString;
          UnCompletedTaskObj.cooperator =
            UnCompletedTasksInject[index].studentList;
          this.unCompletedTasks.push(UnCompletedTaskObj);
        }
      });
    },
    //该方法负责初始化所有的completed task
    injectCompletedTasks: function () {
      this.axios({
        url: "/getCompletedTaskByStudentId",
        data: {
          studentId: this.$store.state.signInStudent.name,
        },
        method: "post",
        // baseURL: "http://localhost:8080/api/",
      })
        .then((response) => {
          var completedTasksInject = response.data;
          for (let index = 0; index < completedTasksInject.length; index++) {
            var completedTaskObj = new Object();
            // taskTitle taskContent taskTeamName progress
            completedTaskObj.taskTitle = completedTasksInject[index].taskName;
            completedTaskObj.taskContent = completedTasksInject[index].context;
            for (let i = 0; i < this.$store.state.teams.length; i++) {
              if (
                this.$store.state.teams[i].teamId ==
                completedTasksInject[index].teamId
              ) {
                completedTaskObj.taskTeamName = this.$store.state.teams[i].name;
              }
            }
            completedTaskObj.progress = 100;
            this.completedTasks.push(completedTaskObj);
          }
        })
    },
    injectUserInfo: function () {
      //用cookie先看看用户登录了没有，因为只有登录成功cookie才会有值
      var username = getCookie("studentId");
      console.log(username);
      if (this.$route.params.id == username) {
        //验证成功
        this.axios({
          url: "/getStudentInfo",
          data: {
            studentId: this.$route.params.id,
          },
          method: "post",
          // baseURL: "http://localhost:8080/api/",
        }).then((response) => {
          var userInfoInject = response.data;
          this.$store.commit(
            "updateSignInStudentInfo",
            userInfoInject.studentInfo
          );
          this.$store.commit(
            "updateSignInStudentName",
            userInfoInject.studentId
          );
          this.$store.commit(
            "updateSignInStudentUserIcon",
            userInfoInject.userIconSrc
          );
          this.$store.commit("selectRole", "student");
        }).then(() => {
          //注入其他值
          this.injectModule();
          this.injectTeams();
          this.injectUnCompletedTasks();
          this.injectCompletedTasks();
        });
      } else {
        //验证失败
        this.$router.push("/signin");
      }
    },
  },
  mounted() {
    this.injectUserInfo();
  },
  components: {
    AsideModules,
    AsideTeams,
    AsideCompletedTasks,
    UnCompletedTasks,
    StudentInfo,
  },
  computed: {
    asideHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 60;
    },
    asideRightWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 992) {
        return this.$store.state.windowSize.windowSizeWidth * 0.3;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.35;
      }
    },
    asideLeftWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 1200) {
        return this.$store.state.windowSize.windowSizeWidth * 0.2;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.3;
      }
    },
    asideMainWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 1200) {
        return this.$store.state.windowSize.windowSizeWidth * 0.5;
      } else if (
        this.$store.state.windowSize.windowSizeWidth > 992 &&
        this.$store.state.windowSize.windowSizeWidth < 1200
      ) {
        return this.$store.state.windowSize.windowSizeWidth * 0.4;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.35;
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
  background: linear-gradient(#df7599, #3994be);
}
.aside-box-left > div {
  height: 80%;
  width: 80%;
  /* box-shadow: 0px 0px 14px 2px #3994BE inset; */
  background: #fff;
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
  background: linear-gradient(#df7599, #3994be);
  box-shadow: -1px 0px 7px #888888;
  overflow: hidden;
}
.scroll-bar-right {
  background: #e0e0e0;
  height: 30%;
  margin-left: 60px;
  margin-right: 60px;
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
.el-main {
  background: #9eb8d2;
  color: black;
  font-size: large;
  background-size: 50px 50px, 50px 50px; /* grid size */
  background-image:
                /* horizontal line */
                /* the first size is defines horizontal line,while the second defines transeparent */ linear-gradient(
      90deg,
      rgba(80, 80, 80, 0.5) 2px,
      transparent 2px
    ),
    /* vertical line */
      linear-gradient(0, rgba(80, 80, 80, 0.5) 2px, transparent 2px);
}
/* #399448 #3994BE #B2E8F7 #FFDAE6 #DF7599 */
</style>
