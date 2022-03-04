<template>
  <div class="scroll-bar-right" :style="{ height: asideHeight - 100 + 'px' }">
    <div :style="{ height: asideHeight - 160 + 'px' }">
      <el-scrollbar>
        <p>{{ focusModuleObj.moduleName }}</p>
        <p class="teacher-name-big">{{ searchTeacher.teacherId }}</p>
        <div class="student-box"></div>
        <div class="team-box">
          <!-- NOTE 警告用户已经加入组了 -->
          <div class="have-join-team-notify" v-if="haveJoinTeam">
            <strong>WARRING</strong>
            <p>You have join the team in this module</p>
          </div>
          <el-collapse v-model="activeName" accordion>
            <el-collapse-item
              v-for="(teamObj, index) in teamObjs"
              :key="index"
              :name="(index + 1).toString()"
            >
              <template #title>
                <div class="team-name">
                  <span>{{ teamObj.teamName }}</span>
                  <span>{{
                    teamObj.studentList.length + "/" + teamObj.teamSize
                  }}</span>
                </div>
              </template>
              <module-detail-collapse-content
                :studentObjs="teamObj.studentList"
                :studentListLength="teamObj.studentList.length"
                :teamSize="teamObj.teamSize"
                :haveJoinTeam="haveJoinTeam"
                :teamId="teamObj.teamId"
                @join-team="joinTeam"
              ></module-detail-collapse-content>
            </el-collapse-item>
          </el-collapse>
        </div>
      </el-scrollbar>
    </div>
    <div class="btn-box">
      <el-button
        :style="{ height: 40 + 'px', marginBottom: 20 + 'px' }"
        :disabled="haveJoinModule"
        @click="joinModule"
        >{{ joinModuleBtnTitle }}</el-button
      >
    </div>
  </div>
</template>

<script>
import ModuleDetailCollapseContent from "./ModuleDetailCollapseContent.vue";
import { ElMessage, ElMessageBox } from "element-plus";
import qs from "qs";

export default {
  name: "ModuleDetail",
  props: {
    // 点击的课程对象
    focusModuleObj: {
      type: Object,
    },
    asideHeight: {
      type: Number,
    },
    //点击的课程对象中含有的所有的team的对象列表
    teamObjs: {
      type: Array,
    },
  },
  data() {
    return {
      activeName: "1",
      // 被选中的课
      studentIDsChoose: [],
      //表示用户已经加入了课程，只有加入课程之后才可以加入组
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
    joinTeam: function (teamId) {
      if (this.haveJoinModule == false) {
        ElMessage.error("Please join the module first");
        return;
      } else {
        //teamId 是子组件点击的组的ID
        if (teamId == 0) {
          ElMessageBox.prompt(
            "This is a new team, enter your team name",
            "Tip",
            {
              confirmButtonText: "OK",
              cancelButtonText: "Cancel",
            }
          )
            .then(({ value }) => {
              //点到了一个空组上面
              this.axios({
                url: "/createTeam",
                method: "post",
                data: qs.stringify(
                  {
                    moduleId: this.focusModuleObj.moduleId,
                    leaderId: this.$store.state.signInStudent.name,
                    teamName: value,
                  },
                  { indices: false }
                ),
              }).then((res)=>{
                //TODO 需要插入一个动态修改的功能，可以直接修改module detail里面的值
                this.focusModuleObj.teamIds.push(res.data.teamId);
                this.$store.commit("pushStudentTeams", res.data);
              });
              ElMessage({
                type: "success",
                message: `Your team name is:${value}`,
              });
            })
            .catch(() => {
              ElMessage({
                type: "info",
                message: "Input canceled",
              });
            });
        }else{
          //点到了一个已经存在的组上面
          this.axios({
            url: "/joinTeam",
            method: "post",
            data: qs.stringify(
              {
                studentId: this.$store.state.signInStudent.name,
                teamId: teamId,
              },
              { indices: false }
            ),
          }).then((res)=>{
            this.$store.commit("pushStudentTeams", res.data);
          });
        }
      }
    },
  },
  computed: {
    joinModuleBtnTitle() {
      return this.haveJoinModule ? "Have Join Module" : "Joining Module";
    },
    haveJoinModule() {
      return this.$store.state.signInStudentModule.some((module) => {
        return module.moduleId == this.focusModuleObj.moduleId;
      });
    },
    haveJoinTeam() {
      var flag = false;
      for (let index = 0; index < this.focusModuleObj.teamIds.length; index++) {
        if (
          // teams包含了用户所有的组的情况
          this.$store.state.teams.some((team) => {
            return team.teamId == this.focusModuleObj.teamIds[index];
          })
        ) {
          //找到一个相同的组就直接弹出循环
          flag = true;
          break;
        }
      }
      return flag;
    },
    // 选中的老师
    searchTeacher() {
      return this.focusModuleObj.teacher;
    },
  },
  components: {
    ModuleDetailCollapseContent,
    ElMessage,
    ElMessageBox,
  },
};
</script>

<style scoped>
.have-join-team-notify {
  width: calc(100% - 5px);
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px;
  color: rgb(0, 0, 0);
  border-left: 5px solid #f56c6c;
  background-color: #f56c6c41;
  margin-bottom: 30px;
}
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
.team-name {
  margin-left: 10px;
}
.team-name span {
  margin-right: 10px;
  color: #409eff;
}
</style>