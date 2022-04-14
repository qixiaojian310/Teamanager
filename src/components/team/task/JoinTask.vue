<template>
  <el-container>
    <el-row v-if="tasks.length > 0" style="width: 100%">
      <el-col :span="24">
        <el-scrollbar :wrap-class="'slide-page'" :height="asideHeight - 240">
          <el-button @click="getAllTasksInTeam"> Refresh tasks </el-button>
          <div class="tasks-box">
            <task-card v-for="task in tasks" :key="task.taskId" :task="task">
              <template #join>
                <el-button type="text" size="mini"> Join task </el-button>
              </template>
            </task-card>
          </div>
        </el-scrollbar>
      </el-col>
    </el-row>
    <div class="tasks-box" :style="{ height: height + 'px' }" v-else>
      <div class="no-data">
        <p>No task</p>
      </div>
    </div>
  </el-container>
</template>

<script>
import qs from "qs";
import TaskCard from "@/components/team/task/TaskCard";
import { ElNotification } from "element-plus";

export default {
  components: {
    TaskCard,
    ElNotification,
  },
  props: {
    height: {
      type: Number,
    },
    teamId: {
      type: Number,
      default: "",
    },
    asideHeight: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      tasks: [],
    };
  },
  methods: {
    getAllTasksInTeam() {
      this.axios({
        url: `getAllTasksInTeam`,
        method: "POST",
        data: qs.stringify(
          {
            teamId: this.teamId,
            studentId: this.$store.state.signInStudent.name,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        this.tasks = res.data;
      });
    },
  },
  mounted() {
    this.getAllTasksInTeam();
  },
};
</script>

<style></style>
