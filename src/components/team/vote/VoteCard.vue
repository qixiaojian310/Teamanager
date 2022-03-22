<template>
  <el-card class="vote-card">
    <template #header>
      <div class="head-box">
        <head-icon
          :userIconHeight="50"
          :userIconWidth="50"
          :userIconSrc="user.userIconSrc"
          :userInfor="user.studentInfo"
          :userName="user.studentId"
          :forceDirection="'bottom'"
        ></head-icon>
      </div>
    </template>
    <template #default>
      <el-container :direction="'vertical'">
        <el-row style="width:100%;margin-bottom:10px">
          <el-col :span="11" :offset="1">{{ user.studentId }}</el-col>
                        <el-col :span="11">
                <el-button type="primary">
                  <i class="fa fa-ticket"></i>
                  Vote him
                </el-button>
              </el-col>
        </el-row>
        <el-row>
          <el-col :span="23">
            <el-row>
              <el-col :span="23" :offset="1">
                <el-progress
                  :text-inside="true"
                  :stroke-width="30"
                  :percentage="user.voteTicket/teamLength*100"
                />
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-container>
    </template>
  </el-card>
</template>

<script>
import HeadIcon from "../../HeadIcon.vue";

export default {
  name: "VoteCard",
  components: {
    HeadIcon,
  },
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          userIcon: "",
          userName: "",
          userInfor: "",
          voteTicket: 0,
        };
      },
    },
    teamLength: {
      type: Number,
      default: 100,
    },
  },
};
</script>

<style scoped>
.head-box {
  display: flex;
  justify-content: flex-start;
  align-content: center;
}
.vote-card {
  margin-bottom: 20px;
  width: 30%;
  margin-left: 10px;
}
</style>