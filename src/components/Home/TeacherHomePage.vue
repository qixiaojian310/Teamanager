<template>
  <el-container>
    <strip-toolbar></strip-toolbar>
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="aside-box-left hidden-sm-and-down"
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
    <el-container :style="{ height: asideHeight + 'px' }">
      <el-main>
        <!-- show your task haven't done  -->
        <el-container :style="{ height: asideHeight + 'px' }">
          <el-scrollbar :style="{ width: 100 + '%' }">
            <el-row style="width: 100%">
              <el-col :span="20" :offset="2">
                <un-completed-tasks
                  :unCompletedTasks="unCompletedTasks"
                ></un-completed-tasks>
              </el-col>
            </el-row>
          </el-scrollbar>
        </el-container>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import AsideModules from "../Home/aside/AsideModules.vue";
import AsideTeams from "../Home/aside/AsideTeams.vue";
import AsideCompletedTasks from "../Home/aside/AsideCompletedTasks.vue";
import UnCompletedTasks from "../Home/main/UnCompletedTasks.vue";
import StripToolbar from "../toolbar/StripToolbar.vue";
import { getCookie } from "../../util/cookieUtil";
import qs from "qs";

export default {
  name: "TeacherHomePage",
  mounted() {
    this.$store.commit("selectRole", "teacher");
    this.checkTeacherCookie();
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
    checkTeacherCookie() {
      var teacherCookie = getCookie("teacherId");
      if (this.$route.params.id == teacherCookie) {
        //该用户已经登陆
        this.$store.commit("updateSignInTeacherName", teacherCookie);
        this.axios({
          url: "/teacher",
          method: "post",
          data: qs.stringify({
            teacherId: this.$route.params.id,
            password: getCookie("teacherPassword"),
            rememberCode: true,
          }),
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }).then((response) => {
          //执行注入的操作
          this.injectModule();
          this.injectTeams();
        }).then(()=>{
          this.injectCompletedTasks();
          this.injectUnCompletedTasks();
        });
        return true;
      } else {
        //该用户未登陆
        this.$router.push("/signin");
        return false;
      }
    },
    injectModule: function () {
      this.axios({
        url: "/getAllTeacherModule",
        method: "post",
      }).then((response) => {
        var injectModules = response.data;
        this.moduleItems = response.data;
        this.$store.commit("injectTeacherModules", injectModules);
      });
    },
    injectTeams: function () {
      this.axios({
        url: "/getAllTeacherTeam",
        data: {
          studentId: this.$store.state.signInStudent.name,
        },
        method: "post",
      }).then((response) => {
        var injectTeams = response.data;
        this.$store.commit("clearTeams");
        for (let index = 0; index < injectTeams.length; index++) {
          var tempTeamObj = new Object();
          tempTeamObj.teamName = injectTeams[index].teamName;
          tempTeamObj.id = injectTeams[index].teamId;
          tempTeamObj.leaderId = injectTeams[index].leaderId;
          tempTeamObj.students = injectTeams[index].studentList;
          tempTeamObj.available = injectTeams[index].available;
          tempTeamObj.chatRoomId = injectTeams[index].chatRoomId;
          this.teamItems.push(tempTeamObj);
          this.$store.commit("pushTeacherTeams", tempTeamObj);
        }
      });
    },
    injectCompletedTasks: function () {
      this.axios({
        url: "/getCompletedTaskByTeacher",
        method: "post",
        // baseURL: "http://localhost:8080/api/",
      }).then((response) => {
        var completedTasksInject = response.data;
        for (let index = 0; index < completedTasksInject.length; index++) {
          var completedTaskObj = new Object();
          // taskTitle taskContent taskTeamName progress
          completedTaskObj.taskTitle = completedTasksInject[index].taskName;
          completedTaskObj.taskContent = completedTasksInject[index].context;
          for (let i = 0; i < this.$store.state.teams.length; i++) {
            if (
              this.$store.state.teams[i].id ==
              completedTasksInject[index].teamId
            ) {
              completedTaskObj.taskTeamName = this.$store.state.teams[i].name;
            }
          }
          completedTaskObj.progress = 100;
          this.completedTasks.push(completedTaskObj);
        }
      });
    },
    injectUnCompletedTasks: function () {
      this.axios({
        url: "/getUnCompletedTaskByTeacher",
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
              this.$store.state.teams[i].id ==
              UnCompletedTasksInject[index].teamId
            ) {
              UnCompletedTaskObj.teamName =
                this.$store.state.teams[i].name;
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
  },

  data() {
    return {
      country: "china",
      // 左边放module的信息，右边放team的
      moduleItems: [],
      // 左边放module的信息，右边放team的
      teamItems: [],
      completedTasks: [],
      unCompletedTasks: [],
      signInStudentId: 1,
    };
  },
  components: {
    AsideModules,
    AsideTeams,
    AsideCompletedTasks,
    UnCompletedTasks,
    StripToolbar,
  },
  computed: {
    asideHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 60;
    },
    asideLeftWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 1200) {
        return this.$store.state.windowSize.windowSizeWidth * 0.3;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.4;
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
  background: #39852e;
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
  background: linear-gradient(#ffdae6, #3994be);
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
.el-main {
  background: #cbcbcb;
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
    /* vertical line */
      linear-gradient(0, rgba(155, 155, 155, 0.5) 2px, transparent 2px);
  padding: 0;
}
/* #399448 #3994BE #B2E8F7 #FFDAE6 #DF7599 */
</style>
