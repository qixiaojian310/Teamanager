<template>
  <el-scrollbar>
    <aside-completed-task-item
      v-for="completedTask in completedTasks"
      :key="completedTask.index"
      :progress="completedTask.progress"
      :taskTitle="completedTask.taskTitle"
      :taskContent="completedTask.taskContent"
      :taskTeamName="completedTask.taskTeamName"
    ></aside-completed-task-item>
  </el-scrollbar>
</template>

<script>
import AsideCompletedTaskItem from "./AsideCompletedTaskItem.vue";

export default {
  name: "AsideCompletedTasks",
  components: {
    AsideCompletedTaskItem,
  },
  props: ["completedTasks"],
};
</script>

<style></style>
