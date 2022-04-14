<template>
  <template v-if="!isCreateTask">
    <el-container>
      <el-row v-if="tasks.length > 0" style="width: 100%">
        <el-col :span="isDetail ? 16 : 24">
          <el-scrollbar :wrap-class="'slide-page'" :height="asideHeight - 240">
            <div class="tasks-box">
              <task-card @cancel="isDetail = true" v-for="task in tasks" :key="task.taskId" :task="task" @see-detail="seeDetail" @refresh-subtask="refreshSubtask">
                <template #detail>
                  <el-button type="text"> See detail </el-button>
                </template>
                <template #create>
                  <el-button type="text" style="margin-right: 10px;"> Create task </el-button>
                </template>
              </task-card>
            </div>
          </el-scrollbar>
        </el-col>
        <el-col :span="isDetail ? 8 : 0" v-if="isDetail">
          <div style="width: 100%; border-left: 1px dotted #000;">
            <!-- <el-button type="primary" @click="openDrawer">Create a subtask</el-button> -->
            <el-scrollbar :height="asideHeight - 240">
              <sub-task @finish-sub-task="refreshSubtask" @delete-sub-task="refreshSubtask" v-for="subtask in subtasks" :key="subtask.subTaskId" :sub-task="subtask"></sub-task>
            </el-scrollbar>
          </div>
        </el-col>
      </el-row>
      <div class="tasks-box" :style="{ height: height - 60 + 'px' }" v-else>
        <div class="no-data">
          <p>No task</p>
          <el-button type="primary" @click="refreshTask"
            >Refresh task</el-button
          >
        </div>
      </div>
    </el-container>
  </template>
  <template v-else>
    <el-scrollbar :height="asideHeight - 240">
      <el-switch
        v-model="isCreate"
        active-color="#8499f5"
        inactive-color="#ff4949"
        active-text="Create task"
        inactive-text="Join task"
      />
      <create-task-page :team-id="teamId" v-if="isCreate"></create-task-page>
      <join-task
        :height="height"
        :aside-height="asideHeight"
        :team-id="teamId"
        v-else
      ></join-task>
    </el-scrollbar>
  </template>
</template>

<script>
import TaskCard from "@/components/team/task/TaskCard";
import CreateTaskPage from "@/components/team/task/CreateTaskPage";
import JoinTask from "@/components/team/task/JoinTask";
import qs from 'qs'
import SubTask from "@/components/team/task/SubTask";

export default {
  name: "TaskPage",
  components: {
    TaskCard,
    CreateTaskPage,
    JoinTask,
    SubTask
},
  data() {
    return {
      isCreate: false,
      subtasks:[],
      taskId: 0,
    };
  },
  methods: {
    // 刷新subtask，更新task
    refreshSubtask(taskId) {
      this.taskId = taskId
      this.axios({
        method:'post',
        url:'/getAllSubtask',
        data: qs.stringify({
          taskId:taskId
        }),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded'
        }
      }).then((res)=>{
        this.subtasks = res.data
        this.refreshTask()
      })
    },
    refreshTask() {
      this.$emit("refreshTask", this.teamId);
    },
    seeDetail(taskId){
      this.$emit("seeDetail");
      this.refreshSubtask(taskId)
      // this.axios({
      //   url: `getTaskDetail`,
      //   method: "POST",
      //   data: qs.stringify({
      //     taskId: taskId,
      //   }),
      //   headers:{
      //     "Content-Type": "application/x-www-form-urlencoded",
      //   }
      // }).then((res) => {

      // });
    },
    openDrawer(taskId){
      this.$emit("openDrawer");
    }
  },
  props: {
    // task completed icon startTime deadline taskContent
    tasks: {
      type: Array,
      default: [],
    },
    height: {
      type: Number,
    },
    isCreateTask: {
      type: Boolean,
      default: false,
    },
    teamId: {
      type: Number,
      default: "",
    },
    isDetail: {
      type: Boolean,
      default: false,
    },
    asideHeight: {
      type: Number,
      default: 0,
    },
  },
};
</script>

<style scoped>
.tasks-box {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  position: relative;
}
.no-data {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  background: rgb(37, 37, 37);
  color: #ccc;
  position: absolute;
}
.el-container {
  width: 100%;
  height: 100%;
  position: relative;
}
.el-switch__label {
  font-size: 1.5rem;
}
</style>
