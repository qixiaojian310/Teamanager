<template>
  <template v-if="focusTeam.studentList.length > 0">
    <el-button @click="resetVote" type="primary">
      <i class="fa fa-backward"></i>
      <span>Reset your vote</span>
    </el-button>
    <p v-if="voteFinish">We have leader</p>
    <p v-else>There is no leader</p>
    <el-container class="votes-box">
      <vote-card
        v-for="student in injectVoteValueTeam.studentList"
        :key="student.studentId"
        :user="student"
        :team-length="injectVoteValueTeam.studentList.length"
        :have-vote="haveVote"
        :team-id="teamId"
        @vote-success="getVoteState"
      >
      </vote-card>
    </el-container>
  </template>
  <template v-else>
    <div class="votes-box" :style="{ height: height + 'px' }">
      <div class="no-data">
        <p>No vote</p>
      </div>
    </div>
  </template>
</template>

<script>
import VoteCard from "./VoteCard.vue";
import qs from "qs";

//利用router的信息获取
export default {
  name: "VotePage",
  components: {
    VoteCard,
  },
  data() {
    return {
      voteStudent: "",
      injectVoteValueTeam: [],
      voteFinish: false,
      haveVote: false,
    };
  },
  props: {
    teamId: {
      type: Number,
      default: "",
    },
    focusTeam: {
      type: Object,
    },
    height: {
      type: Number,
    },
  },
  watch: {
    voteFinish(newVal, oldVal) {
      console.log(newVal + "voteFinish的值");
      var temp = new Object();
      temp.studentId = "";
      temp.voteTicket = 0;
      if (newVal) {
        for (var i = 0; i < this.injectVoteValueTeam.studentList.length; i++) {
          if (
            this.injectVoteValueTeam.studentList[i].voteTicket >=
            temp.voteTicket
          ) {
            temp = this.injectVoteValueTeam.studentList[i];
            //修改leader
          }
        }
        this.$store.state.teams.forEach((item, index) => {
          // item是一个team对象
          if (item.teamId == this.teamId) {
            item.leaderId = temp.studentId;
          }
        });
        this.axios({
          url: "/updateLeader",
          method: "post",
          data: qs.stringify({
            teamId: this.teamId,
            leader: temp.studentId,
          }),
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }).then((res) => {
          if (!res.data) {
            var errorMessage = this.$notify({
              type: "error",
              title: "Error",
              message: "You leader select has some problem",
              duration: 10000,
              position: "top-left",
            });
          } else {
            var successMessage = this.$notify({
              type: "success",
              title: "Success",
              message: "Your leader select success",
              duration: 10000,
              position: "top-left",
            });
            //抛出修改事件
            this.$emit("changeLeader", {
              leaderId: temp.studentId,
              teamId: this.teamId,
            });
          }
        });
      } else {
        this.axios({
          url: "/updateLeader",
          method: "post",
          data: qs.stringify({
            teamId: this.teamId,
            leader: null,
          }),
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }).then((res) => {
          if (!res.data) {
            var errorMessage = this.$notify({
              type: "error",
              title: "Error",
              message: "You leader reset have some problem",
              duration: 10000,
              position: "top-left",
            });
          } else {
            var successMessage = this.$notify({
              type: "success",
              title: "Success",
              message: "Your leader delete",
              duration: 10000,
              position: "top-left",
            });
            this.$emit("changeLeader", { leaderId: null, teamId: this.teamId });
          }
        });
      }
    },
  },
  methods: {
    voteFinishMethod() {
      var voteSum = 0;
      for (var i = 0; i < this.injectVoteValueTeam.studentList.length; i++) {
        voteSum += this.injectVoteValueTeam.studentList[i].voteTicket;
      }
      console.log(voteSum + "voteSum");
      if (this.injectVoteValueTeam.studentList.length == voteSum) {
        this.voteFinish = true;
        console.log("voteFinish" + true);
        return true;
      } else {
        this.voteFinish = false;
        console.log("voteFinish" + false);
        return false;
      }
    },
    //
    resetVote() {
      this.axios({
        method: "post",
        url: "/resetVote",
        data: qs.stringify({
          teamId: this.teamId,
          identify: this.$store.state.signInStudent.name,
        }),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        if (!res.data) {
          var errorMessage = this.$notify({
            type: "error",
            title: "Error",
            message: "You haven't vote",
            duration: 10000,
            position: "top-left",
          });
        } else {
          var successMessage = this.$notify({
            type: "success",
            title: "Success",
            message: "Reset vote success",
            duration: 10000,
            position: "top-left",
          });
          this.axios({
            method: "post",
            url: "/getVoteState",
            data: qs.stringify(
              {
                teamId: this.teamId,
              },
              { indices: false }
            ),
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
          })
            .then((res) => {
              this.injectVoteValueTeam = this.injectVoteValue(res.data);
              this.voteStudent = "";
              this.haveVote = false;
            })
            .then((res) => {
              this.voteFinishMethod();
            });
        }
      });
    },

    injectVoteValue(data) {
      var teamTemp = this.focusTeam;
      for (const key in data) {
        var studentTemp = teamTemp.studentList.find(
          (student) => student.studentId == key
        );
        studentTemp.voteTicket = data[key].voteTicket;
      }
      return teamTemp;
    },
    getAllTeamMember(teamId) {},
    getVoteState() {
      this.axios({
        method: "post",
        url: "/getVoteState",
        data: qs.stringify(
          {
            teamId: this.teamId,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
        .then((res) => {
          // key是用户名
          // voteLeader 偷了谁
          // voteTicket 被投了多少票
          this.injectVoteValueTeam = this.injectVoteValue(res.data);
          this.axios({
            method: "post",
            url: "/getVoteStudent",
            data: qs.stringify(
              {
                teamId: this.teamId,
                studentId: this.$store.state.signInStudent.name,
              },
              { indices: false }
            ),
          }).then((res) => {
            this.voteStudent = res.data;
            this.$emit("voted", this.voteStudent);
            if (this.voteStudent != "") {
              this.haveVote = true;
            }
          });
        })
        .then((res) => {
          this.voteFinishMethod();
        });
    },
  },
  mounted() {
    this.getVoteState();
  },
};
</script>

<style scoped>
.el-button {
  margin-left: 10px !important;
  margin-bottom: 10px !important;
}
.votes-box {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  position: relative;
}
.no-data {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  background: rgb(37, 37, 37);
  color: #ccc;
  position: absolute;
}
</style>
