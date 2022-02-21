<template>
  <div class="scroll-bar-right" :style="{ height: asideHeight - 100 + 'px' }">
    <el-scrollbar >
      <p>{{ focusModuleObj.moduleName }}</p>
      <p class="teacher-name-big">{{ searchTeacher.teacherName }}</p>
      <div class="student-box"></div>
      <div class="team-box">
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item
            v-for="(teamObj, index) in teamObjs"
            :key="teamObj.id"
            :title="teamObj.name"
            :name="(index + 1).toString()"
          >
            <module-detail-collapse-content
              :studentIds="teamObj.studentIds"
            ></module-detail-collapse-content>
          </el-collapse-item>
        </el-collapse>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import ModuleDetailCollapseContent from "./ModuleDetailCollapseContent.vue";

export default {
  name: "ModuleDetail",
  props: {
    focusModuleObj: {
      type: Object,
    },
    asideHeight: {
      type: Number,
    },
  },
  data() {
    return {
      activeName: "1",
      // 被选中的课
      studentIDsChoose: [],
      teachers : this.$store.state.teacher
    };
  },
  computed: {
    // 一个课程所含有的所有的组
    teamObjs() {
      var temp = [];
      for (var index = 0; index < this.focusModuleObj.teamIds.length; index++) {
        //在store中用户的具体索引;
        var teams = this.$store.state.teams;
        console.log(teams);
        var team = teams.filter((team) => {
          console.log("team.studentID" + team.id);
          return team.id == this.focusModuleObj.teamIds[index];
        })[0];
        console.log(team);
        temp.push(team);
      }
      return temp;
    },
    // 选中的老师
    searchTeacher() {
      for (var index = 0; index < this.teachers.length; index++) {
        //在store中用户的具体索引;
        var teacher = this.teachers.filter((teacher) => {
          return teacher.teacherId == this.focusModuleObj.teacherId;
        })[0];
        console.log(teacher);
        return teacher;
      }
    },
  },
  components: {
    ModuleDetailCollapseContent,
  },
};
</script>

<style scoped>
.scroll-bar-right {
  background: #e0e0e0;
  margin-left: 60px;
  margin-right: 60px;
  margin-top: 50px;
  margin-bottom: 50px;
  border-radius: 10px;
  overflow-x: hidden;
  overflow-y: auto;
}
.scroll-bar-right > div p {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
</style>