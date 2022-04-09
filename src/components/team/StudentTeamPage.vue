<template>
  <el-container>
    <!-- 列表栏，细节展示需要收回去 -->
    <el-container
      :style="{ height: asideHeight - 50 + 'px' }"
      v-if="!detailState"
    >
      <el-scrollbar :height="asideHeight - 50" :style="{ width: 100 + '%' }">
        <el-main>
          <div ref="skele">
            <el-skeleton animated :rows="skeletonRow" v-if="loading">
            </el-skeleton>
            <el-container v-else>
              <el-header class="title">{{ title }}</el-header>
              <div class="card-box" v-if="mainCard == 1 || mainCard == 2">
                <team-card
                  v-for="teamSearche in teamSearches"
                  :key="teamSearche.index"
                  @focus-team="focusTeam"
                  :team-searche="teamSearche"
                ></team-card>
              </div>
            </el-container>
          </div>
        </el-main>
      </el-scrollbar>
    </el-container>
    <el-aside
      :width="asideRightWidth + 'px'"
      class="aside-box-right hidden-md-and-down"
      :style="{
        height: asideHeight + 'px',
        transition: 'width 0.5s cubic-bezier(0.4, 0, 0.2, 1)',
      }"
    >
      <!-- 关闭细节 -->
      <el-button class="close-btn" @click="closeDetail">
        <el-icon :size="30" :color="'#ffffff'">
          <close-bold></close-bold>
        </el-icon>
      </el-button>
      <team-detail
        v-if="cardFocus"
        :focus-team-obj="focusTeamObj"
        :team-objs="focusModuleTeams"
        :aside-height="asideHeight"
        :aside-width="asideRightWidth"
        :swiper-slide-index="swiperSlideIndex"
        @see-detail="seeDetail"
      ></team-detail>
      <router-view>
        
      </router-view>
    </el-aside>
  </el-container>
</template>



<script>
// import SwiperTest from "../../swiperTest/SwiperComponent.vue";
import TeamToolBar from "./TeamToolBar.vue";
import TeamCard from "../card/TeamCard.vue";
import { CloseBold } from "@element-plus/icons-vue";
import TeamDetail from "./TeamDetail.vue";
import StripToolbar from "../toolbar/StripToolbar.vue";
import qs from "qs";

export default {
  // id leaderId name students
  name: "StudentTeamPage",
  mounted(){
    this.getTeamUrl();
  },
  methods: {
    getTeamUrl(){
      var teamFocusId = this.$route.params.teamId
      this.$store.state.teams.forEach(element => {
        if(element.teamId == teamFocusId){
          this.focusTeam(element)
        }
      });
    },
    //该方法用来显示组的详细信息
    seeDetail() {
      this.$emit("see-detail");
      this.detailState = true;
    },
    //该方法通过点击的team的组信息获取组对象
    focusTeam(cardFocusObjInject) {
      var cardFocusObj = cardFocusObjInject;
      console.log(cardFocusObj);
      if (this.cardFocusId == cardFocusObj.teamId && this.cardFocusId != 0) {
        //点击了相同的id
        if (this.cardFocus == true) {
          this.cardFocus = false;
        } else {
          //打开了详细信息
          this.cardFocusObj = cardFocusObj;
          this.cardFocus = true;
        }
      } else {
        //点击了另一个课程，先要关闭原来的再打开新的
        this.cardFocus = false;
        this.cardFocusId = cardFocusObj.teamId;
        this.cardFocusObj = cardFocusObj;
        setTimeout(() => {
          this.cardFocus = true;
        }, 400);
      }
    },
    closeDetail: function () {
      this.$emit("close-detail");
      this.cardFocus = false;
      this.detailState = false;
    },
    createTeacherOption: function (id, number) {
      var teacherOption = new Object();
      teacherOption.teacherNumber = number;
      teacherOption.teacherId = id;
      return teacherOption;
    },
    //该函数返回一个老师对象
    searchTeacher: function (teacherFind) {
      for (
        var index = 0;
        index < this.$store.state.signInStudentTeam.length;
        index++
      ) {
        //在store中用户的具体索引;
        var teacher = this.$store.state.signInStudentTeam.filter((teacher) => {
          return teacher.teacherId == teacherFind;
        })[0];
        console.log(teacher);
        return teacher;
      }
    },
  },
  props: {
    teamSearches: {
      type: Array,
      default: function () {
        return this.$store.state.teams;
      },
    },
  },
  data() {
    return {
      TeamTitle: "Team Title",
      cardFocus: false,
      loading: false,
      activeName: "1",
      // NOTE: 后端获取team所有人员使用id返回一个idList
      cardFocusId: 0,
      cardFocusObj: {},
      focusTeamTeams: [],
      //默认的队伍卡片
      mainCard: 1,
      title: "Your Team",
      allTeam: [],
      //判断是不是在detail窗格内
      detailState: false,
      swiperSlideIndex:0,
    };
  },
  computed: {
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
    asideRightWidth() {
      if (this.cardFocus) {
        if (this.detailState) {
          if (this.$store.state.windowSize.windowSizeWidth > 1200) {
            return this.$store.state.windowSize.windowSizeWidth * 0.8 - 60;
          } else {
            return this.$store.state.windowSize.windowSizeWidth * 0.7 - 60;
          }
        } else {
          if (this.$store.state.windowSize.windowSizeWidth > 992) {
            return this.$store.state.windowSize.windowSizeWidth * 0.55;
          } else {
            return this.$store.state.windowSize.windowSizeWidth * 0.45;
          }
        }
      } else {
        return 0;
      }
    },
    skeletonRow() {
      var boxHeight = 0;
      var row = parseInt(this.asideHeight / 38) - 1;
      console.log(this.asideHeight);
      return row;
      // let boxHeight = 300
    },
    asideMainWidth() {
      if (this.$store.state.windowSize.windowSizeWidth > 1200) {
        return this.$store.state.windowSize.windowSizeWidth * 0.5;
      } else if (
        this.$store.state.windowSize.windowSizeWidth > 992 &&
        this.$store.state.windowSize.windowSizeWidth < 1200
      ) {
        return this.$store.state.windowSize.windowSizeWidth * 0.4;
      } else {
        return this.$store.state.windowSize.windowSizeWidth * 0.35;
      }
    },
    focusTeamObj() {
      if (this.cardFocusId == 0) {
        return {
          teamId: -1,
          name: "",
          leaderId: "",
          students: [],
          taskList: [],
        };
      } else {
        // var focusObj = this.teamSearches.filter((teamSearche) => {
        //   return teamSearche.teamId == this.cardFocusId;
        // });
        // console.log(focusObj);
        // return focusObj[0];
        return this.cardFocusObj;
      }
    },
  },
  components: {
    TeamToolBar,
    TeamCard,
    CloseBold,
    TeamDetail,
    StripToolbar,
  },
};
</script>

<style scoped>
#skele {
  position: absolute;
  z-index: 3;
  width: calc(100% - 40px);
  height: calc(100% - 40px);
}
.el-main {
  position: relative;
}
.aside-box-left {
  /* box-shadow: 1px 0px 7px #888888; */
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(#df7599, #3994be);
}
.aside-box-left > div {
  height: 80%;
  width: 80%;
  /* box-shadow: 0px 0px 14px 2px #3994BE inset; */
  background: #fff;
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 20px;
  border-radius: 10px;
  overflow-x: hidden;
  overflow-y: auto;
}
.aside-box-left > div p {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.aside-box-right {
  background: #515151;
  box-shadow: -1px 0px 7px #888888;
  overflow: hidden;
  position: absolute;
  right: 0px;
}
.card-box {
  display: flex;
  flex-wrap: wrap;
  align-content: center;
}
.swiper-container {
  width: 100%;
  height: 200px;
}
.swiper-slide {
  width: 100% !important;
  background: red;
}
.close-btn {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 2;
  background: rgba(0, 0, 0, 0);
  border: none;
}
.close-btn:hover {
  background: rgba(0, 0, 0, 0.1);
}
.title {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: bold;
  color: rgb(15, 15, 15);
}
</style>