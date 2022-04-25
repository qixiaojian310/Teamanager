<template>
  <div class="scroll-bar-right" :style="{ height: asideHeight - 100 + 'px' }">
    <div :style="{ height: asideHeight - 140 + 'px', padding: 20 + 'px' }">
      <swiper
        :enabled="detailStatus"
        :modules="modules"
        :slides-per-view="1"
        navigation
        :space-between="50"
        :pagination="{ clickable: true }"
        :hashNavigation="{ watchState: true }"
        @swiper="onSwiper"
        @slideChange="onSlideChange"
        :simulateTouch="false"
      >
        <swiper-slide data-hash="teamChart">
          <el-scrollbar :wrap-class="'slide-page'" :height="asideHeight - 140">
            <p>
              Team name: <span>{{ focusTeamObj.teamName }}</span>
            </p>
            <span class="teacher-name-big">Leader name:</span>
            <span v-html="searchLeader"></span>
            <div class="team-box">
              <gantt-chart
                :option="ganttOption"
                :width="asideWidth - 160"
                :height="asideHeight - 196"
              ></gantt-chart>
              <!-- <gantt-chart-frappe></gantt-chart-frappe> -->
            </div>
          </el-scrollbar>
        </swiper-slide>
        <swiper-slide
          data-hash="vote"
          v-if="this.$store.state.role == 'student'"
        >
          <el-scrollbar :wrap-class="'slide-page'" :height="asideHeight - 140">
            <div class="vote-header">
              <p>{{ focusTeamObj.teamName }} Vote Center</p>
            </div>
            <div class="member-box">
              <vote-page
                :teamId="focusTeamObj.teamId"
                :focusTeam="focusTeamObj"
                @voted="getVoteStudent"
                :height="asideHeight - 220"
                @change-leader="changeLeader"
              ></vote-page>
            </div>
          </el-scrollbar>
        </swiper-slide>
        <swiper-slide
          data-hash="score"
          v-if="this.$store.state.role == 'teacher'"
        >
          <el-scrollbar :height="asideHeight - 196">
            <p>Score center</p>
            <score-unit
              :students="scoreStudents"
              :team-id="focusTeamObj.teamId"
            ></score-unit>
          </el-scrollbar>
        </swiper-slide>
        <swiper-slide data-hash="task">
          <div class="task-header">
            <p>{{ focusTeamObj.teamName }} task center</p>
            <el-button type="primary" @click="isCreateTask = !isCreateTask">
              <div>
                <i class="fa fa-tasks"></i
                ><span
                  style="font-weight: 300; margin-left: 10px"
                  v-if="!isCreateTask"
                  >create & join</span
                >
                <span style="font-weight: 300; margin-left: 10px" v-else
                  >task list</span
                >
              </div>
            </el-button>
          </div>
          <div class="task-box">
            <task-page
              :leader-id="focusTeamObj.leaderId"
              :is-detail="detailStatus"
              :team-id="focusTeamObj.teamId"
              :is-create-task="isCreateTask"
              :height="asideHeight - 196"
              :tasks="focusTeamObj.taskList"
              :asideHeight="asideHeight"
              @refresh-task="refreshTask"
              @open-drawer="openDrawer"
              @seeDetail="seeDetail"
            ></task-page>
          </div>
        </swiper-slide>
        <swiper-slide data-hash="chat">
          <chat
            :in-team="true"
            :chat-room-id-props="focusTeamObj.chatRoomId"
          ></chat>
        </swiper-slide>
        <swiper-slide data-hash="contribution">
          <el-scrollbar :height="asideHeight - 186" style="margin-left: 20px">
            <contribution-page
              :option="contributeOption"
              :width="asideWidth - 160"
              :height="400"
            ></contribution-page>
            <contribution-all-statistics
              :option="contributePieOption"
              :height="400"
              :width="asideWidth - 160"
            ></contribution-all-statistics>
          </el-scrollbar>
        </swiper-slide>
      </swiper>
    </div>
    <div class="btn-box">
      <el-button
        :style="{ width: 100 + '%' }"
        type="primary"
        @click="seeDetail"
        v-if="!detailStatus"
      >
        <el-icon><data-board /></el-icon>
        see more detail
      </el-button>
      <el-button
        :style="{ width: 100 + '%' }"
        type="primary"
        @click="hideDetail"
        v-else
      >
        <el-icon><data-board /></el-icon>
        hide detail
      </el-button>
    </div>
  </div>
</template>

<script>
import GanttChart from "./GanttChart.vue";
import ContributionPage from "@/components/team/contribution/ContributionPage";
import { DataBoard } from "@element-plus/icons-vue";
import VotePage from "./vote/VotePage.vue";
import TaskPage from "@/components/team/task/TaskPage";
import Calender from "mpvue-calendar";
import Chat from "@/views/Chat";
import qs from "qs";

import {
  Navigation,
  Pagination,
  Scrollbar,
  A11y,
  HashNavigation,
  EffectCube,
} from "swiper";

// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue/swiper-vue.js";

// Import Swiper styles
import "swiper/swiper.min.css";
import "swiper/modules/navigation/navigation.min.css";
import "swiper/modules/pagination/pagination.min.css";
import "swiper/modules/scrollbar/scrollbar.min.css";
import "swiper/modules/effect-cube/effect-cube.min.css";
import "swiper/modules/hash-navigation/hash-navigation.min.css";
import ContributionAllStatistics from "./contribution/ContributionAllStatistics.vue";
import ScoreUnit from "@/components/team/contribution/ScoreUnit";

export default {
  name: "TeamDetail",
  props: {
    focusTeamObj: {
      type: Object,
    },
    asideHeight: {
      type: Number,
    },
    teamObjs: {
      type: Array,
    },
    asideWidth: {
      type: Number,
    },
  },
  data() {
    return {
      contributionTeamSum: 0,
      taskList: [],
      //是contribution要显示的所有task
      allTaskList: [],
      haveJoinChat: false,
      drawerState: false,
      createSubTask: {},
      modules: [Pagination, A11y, EffectCube, HashNavigation, Navigation],
      detailStatus: false,
      activeName: "1",
      // 被选中的课
      studentIDsChoose: [],
      voteStudent: "",
      //task page是否是create状态
      isCreateTask: false,
      contribution: [],
      ganttOption: {
        chart: {
          type: "xrange",
          width: 200,
        },
        title: {
          text: "",
        },
        time: {
          useUTC: false,
        },
        xAxis: {
          opposite: true,
          type: "datetime",
          lineColor: "#525E89",
          tickColor: "#525E89",
          lineWidth: 1,
          labels: {
            style: {
              color: "#7F88AA",
            },
          },
        },
        yAxis: {
          gridLineColor: "transparent",
          title: {
            text: "",
          },
          labels: {
            style: {
              color: "#7F88AA",
            },
          },
          categories: [],
          reversed: true,
        },
        noData: {
          style: {
            fontWeight: "bold",
            fontSize: "15px",
            color: "#303030",
          },
        },
        credits: {
          enabled: false, // 隐藏右下角水印
        },
        tooltip: {
          headerFormat:
            '<span style="font-size: 10px">{point.x:%Y/%m/%d %H:%M:%S} - {point.x2:%Y/%m/%d %H:%M:%S}</span><br/>',
        },
        legend: {
          enabled: false,
        },
        plotOptions: {
          series: {
            animation: false,
          },
        },
        series: [
          {
            name: "Task name",
            borderColor: "gray",
            pointWidth: 10,
            borderRadius: 0,
            borderWidth: 0,
            data: [],
            dataLabels: {
              enabled: true,
            },
          },
        ],
      },
      contributeOption: {
        chart: {
          type: "bar",
        },
        title: {
          text: "The Contribution of Team",
        },
        xAxis: {
          categories: ["苹果", "橘子", "梨", "葡萄", "香蕉"],
        },
        yAxis: {
          min: 0,
          title: {
            text: "The Story point of the task",
          },
        },
        legend: {
          /* 图例显示顺序反转
           * 这是因为堆叠的顺序默认是反转的，可以设置
           * yAxis.reversedStacks = false 来达到类似的效果
           */
          reversed: true,
        },
        plotOptions: {
          series: {
            stacking: "normal",
          },
        },
        series: [
          {
            name: "小张",
            data: [5, 3, 4, 7, 2],
          },
          {
            name: "小彭",
            data: [2, 2, 3, 2, 1],
          },
          {
            name: "小潘",
            data: [3, 4, 4, 2, 5],
          },
        ],
      },
      contributePieOption: {
        chart: {
          plotBackgroundColor: null,
          plotBorderWidth: null,
          plotShadow: false,
          type: "pie",
        },
        title: {
          text: "Contribution summary for each member",
        },
        tooltip: {
          pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>",
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: "pointer",
            dataLabels: {
              enabled: false,
            },
            showInLegend: true,
          },
        },
        series: [
          {
            name: "Task contribution",
            colorByPoint: true,
            data: [
              {
                name: "Chrome",
                y: 61.41,
              },
              {
                name: "Internet Explorer",
                y: 11.84,
              },
              {
                name: "Firefox",
                y: 10.85,
              },
              {
                name: "Edge",
                y: 4.67,
              },
              {
                name: "Safari",
                y: 4.18,
              },
              {
                name: "Other",
                y: 7.05,
              },
            ],
          },
        ],
      },
    };
  },
  computed: {
    searchLeader() {
      if (this.focusTeamObj.leaderId != null) {
        return this.focusTeamObj.leaderId;
      } else {
        return "<span style='color:red'>No selected leader</span>";
      }
    },
    scoreStudents() {
      return this.contribution.map((item) => {
        return { student: item.student, score: item.score };
      });
    },
  },
  methods: {
    cancelClick() {
      this.drawerState = false;
    },
    openDrawer(taskId) {
      this.drawerState = true;
    },
    refreshTask(teamId) {
      this.$emit("refreshTask", teamId);
    },
    changeLeader(leaderObj) {
      this.$emit("changeLeader", leaderObj);
    },
    getVoteStudent(voteStudent) {
      this.voteStudent = voteStudent;
    },
    onSwiper(swiper) {
      this.$store.commit("updateTeamSwiper", swiper);
    },
    onSlideChange(swiper) {
      var warningMessage = {};
      var infoMessage = {};
      if (swiper.activeIndex == 1) {
        //在vote上
        if (this.voteStudent != "") {
          warningMessage = this.$notify({
            type: "warning",
            message: "You have vote " + this.voteStudent,
            duration: 10000,
            position: "top-left",
          });
        } else {
          infoMessage = this.$notify({
            type: "info",
            message: "You haven't vote",
            duration: 10000,
            position: "top-left",
          });
        }
      } else if (swiper.activeIndex == 0) {
        console.log("关闭message");
        this.$notify.closeAll();
      }
    },
    seeDetail() {
      this.$emit("see-detail");
      this.detailStatus = !this.detailStatus;
    },
    hideDetail() {
      this.$emit("hide-detail");
      this.detailStatus = !this.detailStatus;
    },
    // 老师用的方法
    getTaskListWithAxios() {
      this.axios({
        url: "/getTaskListWithTeamId",
        data: qs.stringify(
          {
            teamId: this.focusTeamObj.teamId,
          },
          { indices: false }
        ),
        method: "post",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
        .then((res) => {
          this.taskList = res.data;
          this.focusTeamObj.taskList = res.data;
        })
        .then(() => {
          var taskList = this.taskList;
          console.log(taskList);
          var contextList = [];
          var taskDataList = [];
          for (let index = 0; index < taskList.length; index++) {
            contextList.push(taskList[index].context);
            var taskData = new Object();
            taskData.x = taskList[index].startTime;
            taskData.x2 = taskList[index].deadline;
            taskData.y = index;
            taskDataList.push(taskData);
          }
          this.ganttOption.series[0].data = taskDataList;
          this.ganttOption.yAxis.categories = contextList;
          this.ganttOption.chart.width = this.asideWidth - 160;
        });
    },
    getContribution() {
      this.contribution = [];
      this.axios({
        url: "getAllStoryPoint",
        data: qs.stringify({
          teamId: this.focusTeamObj.teamId,
        }),
        method: "post",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((subRes) => {
        this.contributionTeamSum = subRes.data;
      });
      this.axios({
        url: "getContribution",
        data: qs.stringify({
          teamId: this.focusTeamObj.teamId,
        }),
        method: "post",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
        .then((res) => {
          this.contribution = res.data;
          this.contributeOption.series = this.contribution.map((item) => {
            return {
              name: item.student.studentId,
              data: item.contributionStoryPoints.map((item) => {
                return item.storyPoint;
              }),
            };
          });
          this.contributePieOption.series[0].data = this.contribution.map(
            (item) => {
              return {
                name: item.student.studentId,
                y:
                  (item.contributionStoryPoints.reduce((a, b) => {
                    return a + b.storyPoint;
                  }, 0) /
                    this.contributionTeamSum) *
                  100,
              };
            }
          );
        })
        .then(() => {
          this.getAllTask();
        });
    },
    getAllTask() {
      this.axios({
        url: "/refreshTeacherTask",
        data: qs.stringify({
          teamId: this.focusTeamObj.teamId,
        }),
        method: "post",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        this.allTaskList = res.data;
        this.contributeOption.xAxis.categories = this.allTaskList.map(
          (item) => item.taskName
        );
      });
    },
  },
  created() {
    this.getContribution();
    // this.getTaskListWithAxios();
    var taskList = this.focusTeamObj.taskList;
    console.log(taskList);
    var contextList = [];
    var taskDataList = [];
    for (let index = 0; index < taskList.length; index++) {
      contextList.push(taskList[index].context);
      var taskData = new Object();
      taskData.x = taskList[index].startTime;
      taskData.x2 = taskList[index].deadline;
      taskData.y = index;
      taskDataList.push(taskData);
    }
    this.ganttOption.series[0].data = taskDataList;
    this.ganttOption.yAxis.categories = contextList;
    this.ganttOption.chart.width = this.asideWidth - 160;
  },
  components: {
    GanttChart,
    DataBoard,
    Swiper,
    SwiperSlide,
    VotePage,
    TaskPage,
    Calender,
    Chat,
    ContributionPage,
    ContributionAllStatistics,
    ScoreUnit,
  },
};
</script>

<style scoped>
.scroll-bar-right {
  background: #e0e0e0;
  margin-left: 30px;
  margin-right: 30px;
  margin-top: 40px;
  margin-bottom: 30px;
  border-radius: 10px;
  overflow-x: hidden;
  overflow-y: auto;
}
.scroll-bar-right > div p {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.scroll-bar-right > div span {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.btn-box {
  position: absolute;
  bottom: 10px;
  left: 50%;
  margin-left: -7em;
  width: 14em;
}
.team-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: 20px;
  margin-right: 20px;
}
.vote-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px;
  margin-left: 0;
}
.task-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px;
  margin-left: 0;
}
.swiper-slide {
  background: rgb(222, 222, 222);
}
.student-box {
  display: flex;
  justify-content: flex-start;
  margin-left: 30px;
}
</style>
<style></style>
