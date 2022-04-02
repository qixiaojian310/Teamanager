<template>
  <el-container style="margin-bottom: 30px">
    <el-row style="width: 100%">
      <el-col :span="10" :offset="1">
        <div class="input-box">
          <p>your task name</p>
          <el-input
            v-model="createTask.taskName"
            placeholder="your task name"
          ></el-input>
        </div>
        <div class="input-box">
          <p>your task content</p>
          <el-input
            v-model="createTask.taskContent"
            placeholder="your task content"
          ></el-input>
        </div>
        <div class="calender-box">
          <p>Calender</p>
          <Calendar
            backgroundText
            class-name="select-mode"
            :select-mode="'range'"
            :remarks="remarks"
            :select-data="createTask.taskDate"
            @onSelect="selectDate"
            :language="'en'"
          />
        </div>
      </el-col>
      <el-col class="template-box" :span="10" :offset="2">
        <p>Task template</p>
        <el-card class="task-box">
          <template #header>
            {{ createTask.taskName }}
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
                      <el-tag
                        type="success"
                        v-if="createTask.completed == 'finished'"
                        >Finished</el-tag
                      >
                      <el-tag
                        type="danger"
                        v-else-if="createTask.completed == 'missed'"
                        >Missed</el-tag
                      >
                      <el-tag
                        type="warning"
                        v-else-if="createTask.completed == 'notFinish'"
                        >Haven't Finished</el-tag
                      >
                    </el-timeline-item>
                  </el-timeline>
                </el-col>
              </el-row>
              <div>
                <p>Task Content</p>
                <p>{{ createTask.taskContent }}</p>
              </div>
            </el-scrollbar>
          </template>
        </el-card>
        <el-button
          @click="ensureCreateTask"
          type="success"
          style="margin-top: 10px"
        >
          <i class="fa fa-check-circle"></i>
          <span style="margin-left: 10px">Create Task</span>
        </el-button>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
import Calendar from "mpvue-calendar";
import {
  CircleCheckFilled,
  CircleCloseFilled,
  WarningFilled,
  ColdDrink,
  Check,
} from "@element-plus/icons-vue";
import {ElNotification} from "element-plus";

import qs from "qs";

export default {
  name: "CreateTaskPage",
  data() {
    return {
      createTask: {
        taskName: "",
        taskContent: "",
        taskDate: { start: "", end: "" },
        completed: "notFinish",
      },
    };
  },
  props: {
    teamId: {
      type: Number,
      default: -1,
    },
  },
  methods: {
    selectDate(selectDate) {
      this.createTask.taskDate.start = selectDate.start;
      this.createTask.taskDate.end = selectDate.end;
      console.log(selectDate);
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
    // 创建任务
    ensureCreateTask() {
      var startTimeStamp = new Date(
        this.createTask.taskDate.start.replace(/-/g, "/")
      ).getTime();
      var deadlineStamp = new Date(
        this.createTask.taskDate.end.replace(/-/g, "/")
      ).getTime();
      this.axios({
        url: "/createTask",
        method: "post",
        data: qs.stringify(
          {
            teamId: this.teamId,
            context: this.createTask.taskContent,
            taskName: this.createTask.taskName,
            deadline: deadlineStamp,
            startTime: startTimeStamp,
            studentId: this.$store.state.signInStudent.name,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        if (res.data == "create") {
          if (res.data == "failed") {
            var errorMessage = this.$notify({
              type: "error",
              title: "Error",
              message: "You task create has some problem",
              duration: 10000,
              position: "top-left",
            });
          } else if (res.data == "create") {
            var successMessage = this.$notify({
              type: "success",
              title: "Success",
              message: "Your task create success",
              duration: 10000,
              position: "top-left",
            });
          }
        }
      });
    },
  },
  computed: {
    icon() {
      if (this.createTask.completed == "finished") {
        return CircleCheckFilled;
      } else if (this.createTask.completed == "missed") {
        return CircleCloseFilled;
      } else if (this.createTask.completed == "notFinish") {
        return WarningFilled;
      }
    },
    color() {
      if (this.createTask.completed == "finished") {
        return "#0bbd87";
      } else if (this.createTask.completed == "missed") {
        return "#f5222d";
      } else if (this.createTask.completed == "notFinish") {
        return "#faad14";
      }
    },
    startTime() {
      var date = this.createTask.taskDate.start.replace(/-/g, "/");
      var startTime = new Date(date).getTime();
      return this.changeTimeFormat(startTime);
    },
    deadline() {
      var date = this.createTask.taskDate.end.replace(/-/g, "/");
      var deadline = new Date(date).getTime();
      return this.changeTimeFormat(deadline);
    },
  },
  components: {
    Calendar,
    CircleCheckFilled,
    CircleCloseFilled,
    ColdDrink,
    WarningFilled,
    Check,
    ElNotification
  },
};
</script>

<style scoped>
.input-box {
  display: flex;
}
.calender-box {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.template-box {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>