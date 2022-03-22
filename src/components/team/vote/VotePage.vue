<template>
  <el-container>
    <vote-card
      v-for="student in injectVoteValueTeam.students"
      :key="student.studentId"
      :user="student"
      :team-length="injectVoteValueTeam.students.length"
    >
    </vote-card>
  </el-container>
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
  },
  methods: {
    //
    injectVoteValue(data) {
      var teamTemp = this.focusTeam;
      for (const key in data) {
        var studentTemp = teamTemp.students.find(
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
      }).then((res) => {
        // key是用户名
        // voteLeader 偷了谁
        // voteTicket 被投了多少票
        this.injectVoteValueTeam = this.injectVoteValue(res.data);
        console.log(this.voteObjs);
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
          this.$emit("voted",this.voteStudent);
        });
      });
    },
  },
  mounted() {
    this.getVoteState();
  },
};
</script>

<style>
</style>