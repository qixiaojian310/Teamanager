<template>
  <div class="space-box">
    <p>Unfinished Task</p>
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
.space-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
p {
  color: #fff;
  margin-top: 0.5em;
  font-size: 30px;
  font-weight: bold;
  text-align: center;
}
.el-space {
  width: 90%;
}
</style>