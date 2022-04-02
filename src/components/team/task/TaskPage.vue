<template>
  <template v-if="!isCreateTask">
    <el-container>
      <div class="tasks-box" v-if="tasks.length > 0">
        <task-card v-for="task in tasks" :key="task.taskId" :task="task">
        </task-card>
      </div>
      <div class="tasks-box" :style="{ height: height + 'px' }" v-else>
        <div class="no-data">
          <p>No task</p>
        </div>
      </div>
    </el-container>
  </template>
  <template v-else>
    <create-task-page :team-id="teamId"></create-task-page>
  </template>
</template>

<script>
import TaskCard from "@/components/team/task/TaskCard";
import CreateTaskPage from "@/components/team/task/CreateTaskPage";

export default {
  name: "TaskPage",
  components: {
    TaskCard,
    CreateTaskPage,
  },
  data() {},
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
</style>