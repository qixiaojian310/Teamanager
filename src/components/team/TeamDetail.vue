<template>
  <div class="scroll-bar-right" :style="{ height: asideHeight - 100 + 'px' }">
    <div :style="{ height: asideHeight - 140 + 'px', padding: 20 + 'px' }">
      <swiper
        :enabled="detailStatus"
        :modules="modules"
        :slides-per-view="1"
        :space-between="50"
        :pagination="{ clickable: true }"
        :scrollbar="{ draggable: true }"
        :hashNavigation="{ watchState: true }"
        @swiper="onSwiper"
        @slideChange="onSlideChange"
      >
        <swiper-slide data-hash="teamChart">
          <el-scrollbar :wrap-class="'slide-page'" :height="asideHeight - 140">
            <p>
              Team name: <span>{{ focusTeamObj.name }}</span>
            </p>
            <span class="teacher-name-big">Leader name:</span>
            <span v-html="searchLeader"></span>
            <div class="student-box"></div>
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
        <swiper-slide data-hash="vote">
          <el-scrollbar :wrap-class="'slide-page'" :height="asideHeight - 140">
            <div class="vote-header">
              <p>Vote Center</p>
            </div>
            <div class="member-box">
              <vote-page
                :teamId="focusTeamObj.teamId"
                :focusTeam="focusTeamObj"
                @voted="getVoteStudent"
                :height="asideHeight-220"
              ></vote-page>
            </div>
          </el-scrollbar>
        </swiper-slide>
        <swiper-slide data-hash="task">
          <el-scrollbar :wrap-class="'slide-page'" :height="asideHeight - 140">
            <div class="task-header">
              <p>task Center</p>
              <el-button type="primary" @click="isCreateTask = !isCreateTask">
                <div><i class="fa fa-tasks"></i><span style="font-weight: 300;margin-left: 10px;">create task</span></div>
              </el-button>
            </div>
            <div class="task-box">
              <task-page :team-id="focusTeamObj.teamId" :is-create-task="isCreateTask" :height="asideHeight-196" :tasks="focusTeamObj.taskList"></task-page>
            </div>
          </el-scrollbar>
        </swiper-slide>
      </swiper>
    </div>
    <div class="btn-box">
      <el-button
        :style="{ width: 100 + '%' }"
        type="primary"
        @click="seeDetail"
      >
        <el-icon><data-board /></el-icon>
        see more detail
      </el-button>
    </div>
  </div>
</template>

<script>
import { ElNotification } from "element-plus";

import GanttChart from "./GanttChart.vue";
import { DataBoard } from "@element-plus/icons-vue";
import VotePage from "./vote/VotePage.vue";
import TaskPage from "@/components/team/task/TaskPage";
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
      modules: [Pagination, Scrollbar, A11y, EffectCube, HashNavigation],
      detailStatus: false,
      activeName: "1",
      // 被选中的课
      studentIDsChoose: [],
      voteStudent: "",
      //task page是否是create状态
      isCreateTask: false,
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
  },
  methods: {
    getVoteStudent(voteStudent) {
      this.voteStudent = voteStudent;
    },
    onSwiper(swiper) {
      console.log(swiper);
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
  },
  created() {
    var taskList = this.focusTeamObj.taskList;
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
    TaskPage
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
</style>
<style>
.slide-page {
  background: rgb(231, 120, 129);
}
</style>