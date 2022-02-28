<template>
  <div class="scroll-bar-right" :style="{ height: asideHeight - 100 + 'px' }">
    <div :style="{ height: asideHeight - 160 + 'px' }">
      <el-scrollbar>
        <p>{{ focusModuleObj.moduleName }}</p>
        <p class="teacher-name-big">{{ searchTeacher.teacherId }}</p>
        <div class="student-box"></div>
        <div class="team-box">
          <el-collapse v-model="activeName" accordion>
            <el-collapse-item
              v-for="(teamObj, index) in teamObjs"
              :key="teamObj.id"
              :title="teamObj.teamName"
              :name="(index + 1).toString()"
            >
              <module-detail-collapse-content
                :studentObjs="teamObj.studentList"
              ></module-detail-collapse-content>
            </el-collapse-item>
          </el-collapse>
        </div>
      </el-scrollbar>
    </div>
    <div class="btn-box">
      <el-button
        :style="{ height: 40 + 'px', marginBottom: 20 + 'px' }"
        :disabled="haveJoin"
        @click="joinModule"
        >{{ joinModuleBtnTitle }}</el-button
      >
    </div>
  </div>
</template>

<script>
import ModuleDetailCollapseContent from "./ModuleDetailCollapseContent.vue";
import qs from "qs";

export default {
  name: "ModuleDetail",
  props: {
    focusModuleObj: {
      type: Object,
    },
    asideHeight: {
      type: Number,
    },
    teamObjs: {
      type: Array,
    },
  },
  data() {
    return {
      activeName: "1",
      // 被选中的课
      studentIDsChoose: [],
    };
  },
  methods: {
    setTeamObj: function () {
      this.axios({
        method: "post",
        url: "/getAllTeamByTeamIds",
        data: {
          teamIds: this.focusModuleObj.teamIds,
        },
      }).then((res) => {
        console.log(res);
      });
    },
    joinModule: function () {
      this.axios({
        url: "/joinModule",
        method: "post",
        data: qs.stringify(
          {
            moduleId: this.focusModuleObj.moduleId,
            studentId: this.$store.state.signInStudent.name,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        if (res.data) {
          this.$store.commit("pushStudentModule", this.focusModuleObj);
        }
      });
    },
  },
  computed: {
    joinModuleBtnTitle() {
      return this.haveJoin ? "Have Join Module" : "Joining Module";
    },
    haveJoin() {
      return this.$store.state.signInStudentModule.some((module) => {
        return module.moduleId == this.focusModuleObj.moduleId;
      });
    },
    // 一个课程所含有的所有的组
    // teamObjs() {
    //   var temp = [];
    //   for (var index = 0; index < this.focusModuleObj.teamIds.length; index++) {
    //     //在store中用户的具体索引;
    //     var teams = this.$store.state.teams;
    //     console.log(teams);
    //     var team = teams.filter((team) => {
    //       console.log("team.studentID" + team.id);
    //       return team.id == this.focusModuleObj.teamIds[index];
    //     })[0];
    //     console.log(team);
    //     temp.push(team);
    //   }
    //   return temp;
    // },
    // 选中的老师
    searchTeacher() {
      return this.focusModuleObj.teacher;
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
.btn-box {
  width: 100%;
  display: flex;
  justify-content: center;
}
.team-box {
  margin-left: 20px;
  margin-right: 20px;
}
</style>