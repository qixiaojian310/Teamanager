<template>
  <el-container>
    <strip-toolbar></strip-toolbar>
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="aside-box-left hidden-sm-and-down"
      :style="{ height: asideHeight + 'px' }"
    >
      <student-info :signInStudentId="signInStudentId"></student-info>
    </el-aside>
    <el-container :style="{ height: asideHeight + 'px' }">
      <el-main>
        <el-scrollbar :style="{ width: 100 + '%' }">
          <!-- show your task haven't done  -->
          <el-row style="width: 100%">
            <el-col :span="20" :offset="2">
              <un-completed-tasks
                :unCompletedTasks="unCompletedTasks"
              ></un-completed-tasks>
            </el-col>
          </el-row>
        </el-scrollbar>
      </el-main>
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
import StripToolbar from "../toolbar/StripToolbar.vue";

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
      unCompletedTasks: [],
      signInStudentId: 1,
    };
  },
  methods: {
    timeReverter(timestamp) {
      var time = new Date(timestamp);
      var year = time.getFullYear();
      var month =
        time.getMonth() + 1 < 10
          ? "0" + (time.getMonth() + 1)
          : time.getMonth() + 1;
      var day = time.getDate() < 10 ? "0" + time.getDate() : time.getDate();
      var dateString = year + "-" + month + "-" + day;
      return dateString;
    },
    //TODO - 该方法负责直接将所有的当前用户的所有组注入到vuex中
    injectTeams() {
      this.axios({
        url: "/getAllTeam",
        data: {
          studentId: this.$store.state.signInStudent.name,
        },
        method: "post",
      })
        .then((response) => {
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
        })
        .then(() => {
          this.injectCompletedTasks();
          this.injectUnCompletedTasks();
        });
    },
    // TODO - 该方法负责直接将所有的当前用户的所有课程注入到vuex中
    injectModule() {
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
    injectUnCompletedTasks() {
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
          for (let i = 0; i < this.teamItems.length; i++) {
            if (
              this.teamItems[i].teamId == UnCompletedTasksInject[index].teamId
            ) {
              UnCompletedTaskObj.teamName = this.teamItems[i].name;
            }
          }
          UnCompletedTaskObj.progress = UnCompletedTasksInject[index].progress;
          var deadlineTimestamp = UnCompletedTasksInject[index].deadline;
          var startTimeTimestamp = UnCompletedTasksInject[index].startTime;
          UnCompletedTaskObj.deadline = this.timeReverter(deadlineTimestamp);
          UnCompletedTaskObj.startTime = this.timeReverter(startTimeTimestamp);
          UnCompletedTaskObj.cooperator =
            UnCompletedTasksInject[index].studentList;
          this.unCompletedTasks.push(UnCompletedTaskObj);
        }
      });
    },
    //该方法负责初始化所有的completed task
    injectCompletedTasks() {
      this.axios({
        url: "/getCompletedTaskByStudentId",
        data: {
          studentId: this.$store.state.signInStudent.name,
        },
        method: "post",
        // baseURL: "http://localhost:8080/api/",
      }).then((response) => {
        var completedTasksInject = response.data;
        for (let index = 0; index < completedTasksInject.length; index++) {
          var completedTaskObj = new Object();
          // taskTitle taskContent taskTeamName progress
          completedTaskObj.taskTitle = completedTasksInject[index].taskName;
          completedTaskObj.taskContent = completedTasksInject[index].context;
          for (let i = 0; i < this.teamItems.length; i++) {
            if (
              this.teamItems[i].teamId == completedTasksInject[index].teamId
            ) {
              completedTaskObj.teamName = this.teamItems[i].name;
            }
          }
          completedTaskObj.progress = 100;
          this.completedTasks.push(completedTaskObj);
        }
      });
    },
    injectUserInfo() {
      //用cookie先看看用户登录了没有，因为只有登录成功cookie才会有值
      var username = getCookie("studentId");
      console.log(username);
      if (this.$store.state.rememberCookie) {
        if (this.$route.params.id == username) {
          //验证成功
          this.axios({
            url: "/getStudentInfo",
            data: {
              studentId: this.$route.params.id,
            },
            method: "post",
            // baseURL: "http://localhost:8080/api/",
          })
            .then((response) => {
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
            })
            .then(() => {
              //注入其他值
              this.injectModule();
              this.injectTeams();
              // this.injectUnCompletedTasks();
              // this.injectCompletedTasks();
            });
        } else {
          //验证失败
          //TODO - 没有cookie会有两种情况，一种是直接输入url一种是通过signin没选择remembercode登录需要区分
          this.$router.push("/signin");
        }
      } else {
        //无需验证
        this.axios({
          url: "/getStudentInfo",
          data: {
            studentId: this.$route.params.id,
          },
          method: "post",
          // baseURL: "http://localhost:8080/api/",
        })
          .then((response) => {
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
          })
          .then(() => {
            //注入其他值
            this.injectModule();
            this.injectTeams();
          });
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
    StripToolbar,
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
  padding: 10px;
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
  background: #cbcbcb;
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
