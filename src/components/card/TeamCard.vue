<template>
  <el-card class="card" :body-style="{ padding: '10px' }">
    <template #header>
      <div class="card-header">
        <div class="card-header-title">
          <span>{{ teamSearche.teamName }}</span>
        </div>
      </div>
    </template>
    <template #default>
      <div>
        <div class="teacher-name">
          this team is register by {{ searchLeader }}
        </div>
        <div>
          <el-button @click="focusTeam">
            <el-icon :size="20">
              <edit></edit>
            </el-icon>
            <p>View it</p>
          </el-button>
        </div>
      </div>
    </template>
  </el-card>
</template>

<script>
import { Edit } from "@element-plus/icons-vue";

export default {
  name: "TeamCard",
  props: {
    teamSearche: {
      type: Object,
    },
  },
  data() {
    return {
      leader: this.$store.state.leader,
    };
  },
  methods: {
    focusTeam: function () {
      //返回具体点击了哪个课程
      this.$emit("focusTeam", this.teamSearche);
      this.$router.push({
        name:'StudentTeamPage',
        params:{
          teamId:this.teamSearche.teamId
        }
      })
    },
  },
  components: {
    Edit,
  },
  computed: {
    searchLeader: function () {
      // for (var index = 0; index < this.teachers.length; index++) {
      //   //在store中用户的具体索引;
      //   var teacher = this.teachers.filter((teacher) => {
      //     return teacher.teacherId == this.teamSearche.teacherId;
      //   })[0];
      //   console.log(teacher);
      //   return teacher;
      // }
      return this.teamSearche.leaderId;
    },
  },
};
</script>

<style scoped>
.card {
  width: 190px;
  margin-left: 10px;
  margin-right: 10px;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 20px;
  border-radius: 10px;
  flex-shrink: 0;
}
.teacher-name {
  font-size: 0.5rem;
  color: #414040;
  margin-bottom: 5px;
}
</style>