<template>
  <!-- TODO    
    private String moduleName;
    private Teacher teacher;
    private List<Student> studentList;
    private List<Team> teamList;
 -->
  <el-container class="overflow:hidden">
    <el-row style="width: 100%">
      <el-col :span="20" :offset="2" style="height: 100%">
        <div class="input-item-box">
          <div class="input-item">
            <span>Your Module Name</span>
            <el-input
              v-model="module.moduleName"
              placeholder="Your Module's Name"
            ></el-input>
          </div>
          <el-scrollbar
            :native="false"
            :height="transferHeight"
            style="overflow-y: hidden"
          >
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
                <span>{{ option.studentId }} - {{ option.name }}</span>
              </template>
              <template #left-footer>
                <el-button class="transfer-footer" size="small"
                  >Operation</el-button
                >
              </template>
              <template #right-footer>
                <el-button class="transfer-footer" size="small"
                  >Operation</el-button
                >
              </template>
            </el-transfer>
            <div>
              <span>Your leader in your team</span>
              <el-cascader
                :options="cascaderOptions"
                v-model="leaderIds"
                :props="cascaderProps"
              />
            </div>
          </el-scrollbar>
        </div>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
export default {
  name: "RegisterModule",
  data() {
    return {
      module: {
        moduleName: "",
        teacherId: this.$store.state.signInTeacher.teacherId,
        studentIdList: [],
        teamIds: [],
      },
      cascaderProps: {
        multiple: true,
        label: "leaderName",
        value: "leaderId",
      },
      leaderIds: [],
      StudentListAll: this.$store.state.users,
    };
  },
  methods: {
    createLeaderOption: function (id, name) {
      var leader = new Object();
      leader.leaderName = name;
      leader.leaderId = id;
      return leader;
    },
  },
  computed: {
    transferHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 120;
    },
    cascaderOptions() {
      var temp = [];
      for (let index = 0; index < this.module.studentIdList.length; index++) {
        var studentId = this.module.studentIdList[index];
        var studentObj = this.$store.state.users.filter((user)=>{
          return user.studentId == studentId;
        })[0];
        temp.push(this.createLeaderOption(studentObj.studentId, studentObj.name));        
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
  align-content: center;
  margin-bottom: 20px;
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
  align-items: center;
}
</style>