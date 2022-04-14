<template>
  <el-drawer
    :model-value="drawerState"
    :direction="'ltr'"
    :with-header="false"
    @opened="calendarState = true"
    @closed="calendarState = false"
  >
    <template #default>
      <div>
        <p>Calender</p>
        <SubTaskCalendar
          @selectDate="selectDate"
          :sub-task-begin="subTaskBegin"
          :sub-task-end="subTaskEnd"
          v-if="calendarState"
        ></SubTaskCalendar>
      </div>
      <div>
        <p>Subtask name</p>
        <el-input v-model="createSubTask.subtaskName" placeholder="subtaskName"></el-input>
      </div>
      <div>
        <p>Subtask content</p>
        <el-input
          v-model="createSubTask.subtaskContent"
          placeholder="subtaskContent"
        ></el-input>
      </div>
    </template>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="createSubTaskFunc" type="primary" style="margin-right: 10px;">Create</el-button>
        <el-button @click="cancelClick">cancel</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script>
import SubTaskCalendar from "@/components/team/task/SubTaskCalendar";
import { ElNotification } from "element-plus";
import qs from 'qs'

export default {
  name: "CreateSubTaskPage",
  components: {
    SubTaskCalendar,
    ElNotification,
  },
  data() {
    return {
      createSubTask: {
        taskDate: { start: "", end: "" },
        subtaskName: "",
        subtaskContent: "",
        taskId:this.task.taskId,
        subtaskId:0
      },
      calendarState: false,
    };
  },
  props: {
    task: {
      type: Object,
    },
    drawerState: {
      type: Boolean,
      default: false,
    },
  },
  computed: {
    subTaskBegin() {
      return this.changeTimeFormat(this.task.startTime);
    },
    subTaskEnd() {
      return this.changeTimeFormat(this.task.deadline);
    },
  },
  methods: {
    createSubTaskFunc(){
      var startTimeStamp = new Date(
        this.createSubTask.taskDate.start.replace(/-/g, "/")
      ).getTime();
      var deadlineStamp = new Date(
        this.createSubTask.taskDate.end.replace(/-/g, "/")
      ).getTime();
      this.axios({
        method: "post",
        url: "/createSubTask",
        data: qs.stringify({
          taskId: this.task.taskId,
          subtaskName: this.createSubTask.subtaskName,
          subtaskContent: this.createSubTask.subtaskContent,
          subtaskStartTime: startTimeStamp,
          subtaskDeadline: deadlineStamp,
        }),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
        .then((res) => {
          if(res.data != 0){
            this.createSubTask.subtaskId = res.data;
            this.createSubTask.taskId = this.task.taskId;
            this.$emit("createSubTask", this.createSubTask);
          }
        }).then(()=>{
          this.$notify({
            title: "Success",
            message: "Create Subtask Success",
            type: "success",
            duration: 10000,
            position: "top-left"
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    selectDate(selectDate) {
      this.createSubTask.taskDate.start = selectDate.start;
      this.createSubTask.taskDate.end = selectDate.end;
    },
    changeTimeFormat(timestamp) {
      let _date = new Date(parseInt(timestamp));
      let y = _date.getFullYear();
      let m = _date.getMonth() + 1;
      let d = _date.getDate();
      // console.log( y + '-' + m + '-' + d + ' ' + '　' + h + ':' + minute + ':' + second)
      let dates = y + "-" + m + "-" + d;
      return dates;
    },
    cancelClick() {
      this.$emit("cancel");
    },
  },
};
</script>

<style></style>
