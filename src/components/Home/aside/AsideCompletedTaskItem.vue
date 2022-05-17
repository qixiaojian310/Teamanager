<template>
  <div>
    <p class="task-title">{{ taskTitle }}</p>
    <el-row :justify="'center'" :align="'middle'">
      <!-- task title -->
      <el-col :span="6" :offset="2">
        <el-progress :width="50" type="dashboard" :percentage="progress" />
      </el-col>
      <el-col :span="16">
        <!-- 以后写成routerlink -->
        <p>{{ taskTeamName }}</p>
        <p>{{ taskContent }}</p>
      </el-col>
    </el-row>
    <el-divider></el-divider>
  </div>
</template>

<script>
export default {
  name: "AsideCompletedTaskItem",
  props: {
    progress: {
      type: Number,
      required: true,
    },
    taskTitle: {
      type: String,
      required: true,
    },
    taskContent: {
      type: String,
      required: true,
    },
    taskTeamName: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
.task-title {
  font-size: 1.2em;
  font-weight: bold;
  text-align: center;
  margin-bottom: 10px !important;
}

p {
  margin: 2px !important;
  color: var(--right-link-color);
}
</style>
