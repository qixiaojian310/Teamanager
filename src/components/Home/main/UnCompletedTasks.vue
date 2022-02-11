<template>
  <div>
    <p>Unfinished Task</p>
    <el-space :direction="'vertical'" size="large">
      <un-completed-task-card
        v-for="unCompletedTask in unCompletedTasks"
        :key="unCompletedTask.index"
        :unCompletedTaskName="unCompletedTask.unCompletedTaskName"
        :unCompletedTaskContent="unCompletedTask.unCompletedTaskContent"
        :cooperator="unCompletedTask.cooperator"
        :deadline="unCompletedTask.deadline"
        :teamName="unCompletedTask.unCompletedTaskTeamName"
        :progress="unCompletedTask.progress"
      >
      </un-completed-task-card>
    </el-space>
  </div>
</template>

<script>
import UnCompletedTaskCard from "./UnCompletedTaskCard.vue";
export default {
  name: "UnCompletedTasks",
  components: { UnCompletedTaskCard },
  props: {
    unCompletedTasks: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped>
p{
  margin-top: 0.5em;
  font-size: 30px;
  font-weight: bold;
  text-align: center;
}
.el-space{
  width: 100%;
}
</style>