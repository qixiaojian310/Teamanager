<template>
  <!-- TODO    
    private String moduleName;
    private Teacher teacher;
    private List<Student> studentList;
    private List<Team> teamList;
 -->
  <el-container class="overflow:hidden" style="margin-top: 20px;">
    <el-row style="width: 100%">
      <el-col :span="20" :offset="2" style="height: 100%">
        <div class="input-item-box">
          <span style="color: var(--el-text-color-primary);">Your Module Name</span>
          <div class="input-item">
            <el-input
              v-model="moduleName"
              placeholder="Your Module's Name"
            ></el-input>
            <el-button type="primary" @click="changeModuleName">
              <i class="fa fa-pencil-square-o"></i>
              Edit module Name
            </el-button>
          </div>
          <el-scrollbar
            :native="false"
            :height="transferHeight"
            style="overflow-y: hidden"
          >
            <p style="text-align: center; font-size: 2rem; color: var(--el-text-color-primary);">
              Delete some student
            </p>
            <el-transfer
              v-model="module.studentIdList"
              style="text-align: left; display: inline-block"
              filterable
              :left-default-checked="[]"
              :right-default-checked="[]"
              :titles="['Source', 'Target']"
              :button-texts="['To left', 'To right']"
              :format="{
                noChecked: '${total}',
                hasChecked: '${checked}/${total}',
              }"
              :props="{
                label: 'name ${studentId}',
                key: 'studentId',
              }"
              :data="StudentListAll"
            >
              <!-- NOTE 学生的name和id -->
              <template #default="{ option }">
                <span>{{ option.studentId }}</span>
              </template>
              <template #right-footer>
                <el-button
                  class="transfer-footer"
                  size="small"
                  @click="deleteSelectStudent"
                  >Delete them</el-button
                >
              </template>
            </el-transfer>
          </el-scrollbar>
        </div>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
import qs from "qs";

export default {
  name: "RegisterModule",
  props: {
    focusModuleId: {
      type: Number,
    },
    moduleName:{
      type:String,
      default:''
    }
  },
  data() {
    return {
      module: {
        teacherId: this.$store.state.signInTeacher.teacherId,
        studentIdList: [],
        teamIds: [],
      },
      cascaderProps: {
        multiple: true,
        label: "leaderId",
        value: "leaderId",
      },
      leaderIds: [],
    };
  },
  methods: {
    deleteSelectStudent() {
      this.axios({
        url: "/deleteSelectStudent",
        method: "post",
        data: qs.stringify(
          {
            moduleId: this.focusModuleId,
            studentIds: this.module.studentIdList,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        if (res.data) {
          this.$store.commit("deleteModuleStudents", {
            moduleId: this.focusModuleId,
            studentIdList: this.module.studentIdList,
          });
          this.module.studentIdList = [];
        }
      });
    },
    changeModuleName(){
      this.axios({
        url: "/changeModuleName",
        method: "post",
        data: qs.stringify(
          {
            moduleId: this.focusModuleId,
            moduleName: this.moduleName,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        if (res.data) {
          this.$store.commit("changeModuleName", {
            moduleId: this.focusModuleId,
            moduleName: this.moduleName,
          });
        }
      });
    },
    createLeaderOption: function (id) {
      var leader = new Object();
      leader.leaderId = id;
      return leader;
    },
  },
  computed: {
    StudentListAll() {
      var module = this.$store.state.signInTeacherModule.filter((item) => {
        return item.moduleId == this.focusModuleId;
      })[0];
      if (module == null) {
        return this.$store.state.users;
      }
      return module.students;
    },
    transferHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 120;
    },
    cascaderOptions() {
      var module = this.$store.state.signInTeacherModule.filter((item) => {
        return item.moduleId == this.focusModuleId;
      })[0];

      var temp = [];
      for (let index = 0; index < module.students.length; index++) {
        var studentObj = module.students[index];
        temp.push(this.createLeaderOption(studentObj.studentId));
      }
      return temp;
    },
  },
};
</script>

<style scoped>
.input-item {
  text-align: center;
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  width: 100%;
}
.el-input {
  align-self: center;
}
.el-transfer {
  width: 640px;
}
.input-item-box {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
</style>