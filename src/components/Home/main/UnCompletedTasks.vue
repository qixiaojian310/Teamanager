<template>
  <div>
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
import UnCompletedTaskCard from "@/components/Home/main/UnCompletedTaskCard.vue";
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

<style>
</style>