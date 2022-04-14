<template>
  <el-card class="sub-task-box">
    <template #header>
      {{ subTask.taskName }}
    </template>
    <template #default>
      <el-scrollbar :height="340">
        <el-row>
          <el-col :span="24">
            <el-timeline>
              <el-timeline-item
                :timestamp="startTime"
                :color="'#0bbd87'"
                :icon="startIcon"
                :size="'large'"
              >
                Start Time
              </el-timeline-item>
              <el-timeline-item
                :color="color"
                :timestamp="deadline"
                :icon="icon"
                :size="'large'"
              >
                <span>Deadline</span>
                <el-tag type="success" v-if="completed == 'finished'"
                  >Finished</el-tag
                >
                <el-tag type="danger" v-else-if="completed == 'missed'"
                  >Missed</el-tag
                >
                <el-tag type="warning" v-else-if="completed == 'notFinish'"
                  >Haven't Finished</el-tag
                >
              </el-timeline-item>
            </el-timeline>
          </el-col>
        </el-row>
        <div>
          <p>Subtask Content</p>
          <p>{{ subTask.context }}</p>
        </div>
        <div>
          <el-button type="success" @click="finishSubTask"
            >Finish Subtask</el-button
          >
          <el-button type="danger" @click="deleteSubTask"
            >Delete Subtask</el-button
          >
        </div>
      </el-scrollbar>
    </template>
  </el-card>
</template>

<script>
import {
  CircleCheckFilled,
  CircleCloseFilled,
  WarningFilled,
  ColdDrink,
} from "@element-plus/icons-vue";
import HeadIcon from "../../HeadIcon.vue";

import "swiper/modules/free-mode/free-mode.min.css";
import "swiper/modules/scrollbar/scrollbar.min.css";
import "swiper/modules/mousewheel/mousewheel.min.css";
import { ElMessage } from "element-plus";
import qs from "qs";

export default {
  name: "TaskCard",
  components: {
    ColdDrink,
    CircleCheckFilled,
    CircleCloseFilled,
    WarningFilled,
  },
  data() {
    return {
      startIcon: ColdDrink,
    };
  },
  props: {
    // task completed icon startTime deadline taskContent
    subTask: {
      type: Object,
      default: {},
    },
  },
  computed: {
    icon() {
      if (this.completed == "finished") {
        return CircleCheckFilled;
      } else if (this.completed == "missed") {
        return CircleCloseFilled;
      } else if (this.completed == "notFinish") {
        return WarningFilled;
      }
    },
    color() {
      if (this.completed == "finished") {
        return "#0bbd87";
      } else if (this.completed == "missed") {
        return "#f5222d";
      } else if (this.completed == "notFinish") {
        return "#faad14";
      }
    },
    deadline() {
      return this.changeTimeFormat(this.subTask.deadline);
    },
    startTime() {
      return this.changeTimeFormat(this.subTask.startTime);
    },
    completed() {
      if (this.subTask.completed) {
        //完成
        return "finished";
      } else {
        //未完成
        //TODO - 判断是否超时
        var todayTime = new Date().getTime();
        if (todayTime > this.subTask.deadline) {
          //超时
          return "missed";
        } else {
          //未超时
          return "notFinish";
        }
      }
    },
  },
  methods: {
    finishSubTask() {
      this.axios({
        method: "post",
        url: "/finishSubTask",
        data: qs.stringify({
          subTaskId: this.subTask.subTaskId,
        }),
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
        .then((res) => {
          if (res.status == 200) {
            ElMessage.success("Finish Subtask Successfully");
            this.$emit("finishSubTask",this.subTask.taskId);
          } else {
            ElMessage.error("error finish");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //删除子任务
    deleteSubTask() {
      this.axios({
        method: "post",
        url: "/deleteSubTask",
        data: qs.stringify({
          subTaskId: this.subTask.subTaskId,
        }),
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
        .then((res) => {
          if (res.status == 200) {
            ElMessage.success("Delete Subtask Successfully");
            this.$emit("deleteSubTask",this.subTask.taskId);
          } else {
            ElMessage.error("error delete");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    changeTimeFormat(timestamp) {
      let _date = new Date(parseInt(timestamp));
      let y = _date.getFullYear();
      let m = _date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      let d = _date.getDate();
      d = d < 10 ? "0" + d : d;
      // console.log( y + '-' + m + '-' + d + ' ' + '　' + h + ':' + minute + ':' + second)
      let dates = y + "-" + m + "-" + d;
      return dates;
    },
  },
};
</script>

<style scoped>
.sub-task-box {
  width: 300px;
  height: 400px;
  margin: 10px;
}
.cooperator {
  margin-bottom: 20px;
}
</style>
