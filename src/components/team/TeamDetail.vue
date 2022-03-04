<template>
  <div class="scroll-bar-right" :style="{ height: asideHeight - 100 + 'px' }">
    <div :style="{ height: asideHeight - 160 + 'px' }">
      <el-scrollbar>
        <p>Team name: {{ focusTeamObj.name }}</p>
        <p class="teacher-name-big">Leader name: {{ searchLeader }}</p>
        <div class="student-box"></div>
        <div class="team-box">
          <gantt-chart :id="'gantt'" :option="ganttOption"></gantt-chart>
          <!-- <gantt-chart-frappe></gantt-chart-frappe> -->
        </div>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import GanttChart from './GanttChart.vue';
import GanttChartFrappe from "./GanttChartFrappe.vue";

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
    asideWidth:{
      type:Number,
    }
  },
  data() {
    return {
      activeName: "1",
      // 被选中的课
      studentIDsChoose: [],
      ganttOption:{
        chart: {
          type: "xrange",
          width:200,
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
    }
    };
  },
  computed: {
    searchLeader() {
      return this.focusTeamObj.leaderId;
    },

  },
  beforeMount(){
      var taskList = this.focusTeamObj.taskList;
      var contextList = [];
      var taskDataList = [];
      for (let index = 0; index < taskList.length; index++) {
        contextList.push(taskList[index].context)
        var taskData = new Object();
        taskData.x = taskList[index].startTime;
        taskData.x2 = taskList[index].deadline;
        taskData.y = index;
        taskDataList.push(taskData);
      }
      this.ganttOption.series[0].data = taskDataList;
      this.ganttOption.yAxis.categories = contextList;
      this.ganttOption.chart.width = this.asideWidth-160;
  },
  components: {
    GanttChart,
    GanttChartFrappe
  },
};
</script>

<style scoped>
.scroll-bar-right {
  background: #e0e0e0;
  margin-left: 60px;
  margin-right: 60px;
  margin-top: 50px;
  margin-bottom: 50px;
  border-radius: 10px;
  overflow-x: hidden;
  overflow-y: auto;
}
.scroll-bar-right > div p {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.btn-box {
  width: 100%;
  display: flex;
  justify-content: center;
}
.team-box {
  margin-left: 20px;
  margin-right: 20px;
}
</style>