<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <div>
          <p class="task-name">{{ unCompletedTaskName }}</p>
          <p class="team-name">{{ teamName }}</p>
        </div>
        <date-shower :date="deadline.replace(/-/g, '')"></date-shower>
      </div>
    </template>
    <div class="card-body">
      <el-row class="card-body-content">
        <el-col :span="18" class="card-body-content-title">
          <span>{{ unCompletedTaskContent }}</span>
        </el-col>
        <el-col :span="6" class="card-body-content-cooperator">
          <el-scrollbar :height="400">
            <head-icon
            v-for="cooperatorObj in cooperatorObjs"
            :key="cooperatorObj.index"
            :userIconHeight="50"
            :userIconWidth="50"
            :userIconSrc="cooperatorObj.userIconSrc"
            :userName="cooperatorObj.name"
          ></head-icon>
          </el-scrollbar>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
import DateShower from "@/components/Home/main/DateShower.vue";
import HeadIcon from "../../HeadIcon.vue";
export default {
  components: { DateShower, HeadIcon },
  name: "UnCompletedTaskCard",
  mounted() {},
  props: {
    unCompletedTaskName: {
      type: String,
      required: true,
    },
    unCompletedTaskContent: {
      type: String,
      required: true,
    },
    cooperator: {
      type: Array,
      required: true,
    },
    deadline: {
      type: String,
      required: true,
    },
    teamName: {
      type: String,
      required: true,
    },
    progress: {
      type: Number,
      required: true,
    },
  },
  computed: {
    cooperatorObjs() {
      var temp = [];
      for (let index = 0; index < this.cooperator.length; index++) {
        //在store中用户的具体索引
        const userIndex = this.cooperator[index] - 1;
        const user = this.$store.state.users[userIndex];
        temp.push(user);
      }
      return temp;
    },
  },
};
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.task-name {
  color: #376fbd;
  font-size: 20px;
  font-weight: 900;
}
.team-name {
  color: #1d4f96;
  font-size: 16px;
  font-weight: 900;
}
.card-body-content-title {
  line-height: 1.8;
}
</style>