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
        <el-row style="width: 100%; margin-bottom: 10px">
          <el-col :span="5" :offset="1">{{ user.studentId }}</el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-button
              :type="!haveVote ? 'primary' : 'danger'"
              :disabled="haveVote"
              @click="vote"
            >
              <i class="fa fa-ticket"></i>
              {{ haveVote ? "You have vote" : "Vote him" }}
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
                  :percentage="((user.voteTicket / teamLength) * 100).toFixed(2)"
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
import {ElNotification} from "element-plus";
import qs from 'qs'

export default {
  name: "VoteCard",
  components: {
    HeadIcon,
  },
  methods: {
    vote() {
      this.axios({
        url:'/voteLeader',
        method: "post",
        data:qs.stringify({
          teamId: this.teamId,
          identify: this.$store.state.signInStudent.name,
          voteStu: this.user.studentId
        }),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then(res=>{
        if(!res.data){
          var errorMessage = this.$notify({
            type: "error",
            title: "Error",
            message: "You vote has some problem",
            duration: 10000,
            position: "top-left"
          });
        }else{
          var successMessage = this.$notify({
            type: "success",
            title: "Success",
            message: "Your vote success",
            duration: 10000,
            position: "top-left"
          });
          this.$emit("voteSuccess");
        }
      });
    },
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
    teamId:{
      type:Number,
      default:0
    },
    haveVote: {
      type: Boolean,
      default: false,
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
button {
  width: 100%;
}
.el-row {
  margin-bottom: 10px;
}
</style>