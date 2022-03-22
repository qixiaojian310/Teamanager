<template>
  <el-container>
    <!-- 工具栏 -->
    <strip-toolbar></strip-toolbar>
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="hidden-sm-and-down"
      :style="{ height: asideHeight + 'px' }"
    >
      <team-tool-bar
        :options="leaderObjs"
        @show-list="showList"
        @ensure-choice="selectTeam"
        :isModule="false"
        :isDetail="isDetail"
      ></team-tool-bar>
    </el-aside>
    <router-view :team-searches="teamSearches" @see-detail="seeDetail" @close-detail="closeDetail"></router-view>
  </el-container>
</template>



<script>
// import SwiperTest from "../../swiperTest/SwiperComponent.vue";
import StripToolbar from "../toolbar/StripToolbar.vue";
import TeamToolBar from "./TeamToolBar.vue"
import {ElMessage} from "element-plus"


export default {
  // id leaderId name students
  name: "TeamHome",
  components: {
    StripToolbar,
    TeamToolBar
  },
  computed:{
    asideHeight() {
      return this.$store.state.windowSize.windowSizeHeight - 60;
    },
    asideLeftWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 1200) {
        return this.$store.state.windowSize.windowSizeWidth * 0.2;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.3;
      }
    },
    leaderObjs() {
      return this.leaderObjsGet(this.$store.state.teams);
    },
  },
  data(){
    return{
      teamSearches: this.$store.state.teams,
      isDetail:false,
    }
  },
  methods:{
    seeDetail(){
      this.isDetail = true;
    },
    closeDetail(){
      this.isDetail = false;
      this.$message.closeAll();
    },
    selectTeam: function (teacherIds) {
      //NOTE 检查级联选择器的值
      // NOTE teacherIds是一个数组
      var allTeam = [];
      allTeam = this.$store.state.teams;
      var res = [];
      res = allTeam.filter((team) => {
        for (let index = 0; index < teacherIds.length; index++) {
          if (team.leaderId == teacherIds[index]) {
            return team;
          }
        }
      });
      this.teamSearches = res;
    },
    showList: function () {
      this.title = "Your Team";
      this.teamSearches = this.$store.state.teams;
    },
    leaderObjsGet(showList) {
      var noRepeatLeaders = [];
      console.log("length" + noRepeatLeaders.length);
      //flag 为假表示这个数组从没有找到过这个元素
      var flag = false;
      //this.$store.state.signInStudentModule
      for (var i = 0; i < showList.length; i++) {
        // 学生上课的老师
        var leaderId = showList[i].leaderId;
        //flag 为假表示这个数组从没有找到过这个元素
        flag = false;
        var leaderObj = this.createLeaderOption(leaderId, 1);
        for (var index = 0; index < noRepeatLeaders.length; index++) {
          if (noRepeatLeaders[index].leaderId == leaderId) {
            noRepeatLeaders[index].leaderNumber += 1;
            var flag = true;
          }
        }
        if (!flag) {
          noRepeatLeaders.push(leaderObj);
        }
      }
      return noRepeatLeaders;
    },
    createLeaderOption: function (id, number) {
      var leaderOption = new Object();
      leaderOption.leaderNumber = number;
      leaderOption.leaderId = id;
      return leaderOption;
    },
  }
};
</script>

<style scoped>

</style>