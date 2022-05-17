<template>
  <el-container>
    <!-- 工具栏 -->
    <strip-toolbar></strip-toolbar>
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="hidden-sm-and-down left-aside"
      :style="{ height: asideHeight + 'px' }"
    >
      <module-toolbar
        :options="teacherObjs"
        @show-list="showList"
        @add-module="addModule"
        @ensure-choice="selectModule"
      ></module-toolbar>
    </el-aside>
    <el-container class="main-page" :style="{ height: asideHeight + 'px' }">
      <el-scrollbar :height="asideHeight - 50" :style="{ width: 100 + '%' }">
        <el-main>
          <div ref="skele">
            <el-skeleton animated :rows="skeletonRow" v-if="loading">
            </el-skeleton>
            <el-container v-else>
              <el-header class="title">{{ title }}</el-header>
              <div class="card-box" v-if="mainCard == 1 || mainCard == 2">
                <module-card
                  v-for="moduleSearche in moduleSearches"
                  :key="moduleSearche.index"
                  @focus-module="focusModule"
                  :module-searche="moduleSearche"
                ></module-card>
              </div>
              <!-- 加入课程需要显示所有的课程 -->
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
      <el-button class="close-btn" @click="closeDetail">
        <el-icon :size="30" :color="'#ffffff'">
          <close-bold></close-bold>
        </el-icon>
      </el-button>
      <el-button class="refresh-btn" @click="refreshModule">
        <el-icon :size="30" :color="'#ffffff'">
          <refresh></refresh>
        </el-icon>
      </el-button>
      <!-- NOTE - 会传入一个被点击的课程对象 -->
      <module-detail
        :focusModuleObj="focusModuleObj"
        :teamObjs="focusModuleTeams"
        :asideHeight="asideHeight"
        @change-team="addTeamRefresh"
      ></module-detail>
    </el-aside>
  </el-container>
</template>

<script>
// import SwiperTest from "../../swiperTest/SwiperComponent.vue";
import ModuleToolbar from "../../toolbar/ModuleToolbar.vue";
import ModuleCard from "../../card/ModuleCard.vue";
import ModuleDetail from "../slide/ModuleDetail.vue";
import { CloseBold,Refresh } from "@element-plus/icons-vue";
import SwiperComponent from "../../swiperTest/SwiperComponent.vue";
import StripToolbar from '../../toolbar/StripToolbar.vue'
import qs from "qs";

export default {
  name: "StudentModule",
  mounted(){
    this.getTeamUrl()
  },
  methods: {
    refreshModule(){
      this.setTeamObj(this.cardFocusObj)
    },
    getTeamUrl(){
      var moduleFocusId = this.$route.params.moduleId
      this.$store.state.signInStudentModule.forEach(element => {
        if(element.moduleId == moduleFocusId){
          this.focusModule(element)
        }
      });
    },
    teacherObjsGet(showList) {
      var noRepeatTeachers = [];
      console.log("length" + noRepeatTeachers.length);
      //flag 为假表示这个数组从没有找到过这个元素
      var flag = false;
      //this.$store.state.signInStudentModule
      for (var i = 0; i < showList.length; i++) {
        // 学生上课的老师
        var teacher = showList[i].teacher;
        //flag 为假表示这个数组从没有找到过这个元素
        flag = false;
        var teacherObj = this.createTeacherOption(teacher.teacherId, 1);
        for (var index = 0; index < noRepeatTeachers.length; index++) {
          if (noRepeatTeachers[index].teacherId == teacher.teacherId) {
            noRepeatTeachers[index].teacherNumber += 1;
            var flag = true;
          }
        }
        if (!flag) {
          noRepeatTeachers.push(teacherObj);
        }
      }
      return noRepeatTeachers;
    },
    showList: function () {
      this.mainCard = 1;
      this.title = "Your Module";
      this.moduleSearches = this.$store.state.signInStudentModule;
    },
    addModule: function () {
      this.mainCard = 2;
      this.title = "All Module";
      this.axios({
        url: "/getAllModuleSearch",
        method: "post",
      }).then((res) => {
        this.moduleSearches = res.data;
        //存一份拷贝，方便在同步修改的时候可以确保基础的总选项不会发生改变
        this.allModule = res.data;
        
        //moduleId moduleName students teacher teamIds
      });
    },

    //该方法通过点击的module的组信息获取组对象
    setTeamObj(cardFocusObjInject) {
      var injectData = {};
      injectData.teamIds = cardFocusObjInject.teamIds;

      // NOTE - 通过id获取组对象的列表
      this.axios({
        url: "/getAllTeamByTeamIds",
        data: qs.stringify(injectData, { indices: false }),
        method: "post",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
        .then((res) => {
          //比较一下课程定义的组数量和获取到的总的组的数量

          if (res.data.length < this.focusModuleObj.teamNum) {
            for (
              let index = res.data.length;
              index < this.focusModuleObj.teamNum;
              index++
            ) {
              //如果获取到的组数量小于定义的组数量，说明组的数量不够，手动添加组
              var teamInject = new Object();
              teamInject.teamId = 0;
              teamInject.taskList = [];
              teamInject.moduleId = this.focusModuleObj.moduleId;
              teamInject.teamName = "a empty team";
              //算出teamSize
              teamInject.teamSize = Math.ceil(
                this.focusModuleObj.moduleSize / this.focusModuleObj.teamNum
              );
              teamInject.studentList = [];
              res.data.push(teamInject);
            }
          }
          this.focusModuleTeams = res.data;
        })
        .catch((error) => {
          this.focusModuleTeams = [];
        });
    },
    focusModule(cardFocusObjInject) {
      var cardFocusObj = cardFocusObjInject;
      console.log(cardFocusObj);
      if (this.cardFocusId == cardFocusObj.moduleId && this.cardFocusId != 0) {
        //点击了相同的id
        if (this.cardFocus == true) {
          this.cardFocus = false;
        } else {
          //打开了详细信息
          this.cardFocus = true;
          this.setTeamObj(cardFocusObj);
        }
      } else {
        //点击了另一个课程，先要关闭原来的再打开新的
        this.cardFocus = false;
        this.cardFocusId = cardFocusObj.moduleId;
        this.cardFocus = true;
        this.setTeamObj(cardFocusObj);
      }
      this.cardFocusObj = cardFocusObj;
    },
    closeDetail: function () {
      this.cardFocus = false;
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
        index < this.$store.state.signInStudentModule.length;
        index++
      ) {
        //在store中用户的具体索引;
        var teacher = this.$store.state.signInStudentModule.filter(
          (teacher) => {
            return teacher.teacherId == teacherFind;
          }
        )[0];
        console.log(teacher);
        return teacher;
      }
    },
    selectModule: function (teacherIds) {
      //NOTE 检查级联选择器的值
      // NOTE teacherIds是一个数组
      var allModule = [];
      if (this.mainCard == 1) {
        allModule = this.$store.state.signInStudentModule;
      } else {
        allModule = this.allModule;
      }
      var res = [];
      res = allModule.filter((module) => {
        for (let index = 0; index < teacherIds.length; index++) {
          if (module.teacher.teacherId == teacherIds[index]) {
            return module;
          }
        }
      });
      this.moduleSearches = res;
    },
  },
  data() {
    return {
      ModuleTitle: "Module Title",
      cardFocus: false,
      loading: false,
      activeName: "1",
      // NOTE: 后端获取module所有人员使用id返回一个idList
      moduleSearches: this.$store.state.signInStudentModule,
      cardFocusId: 0,
      cardFocusObj: {},
      focusModuleTeams: [],
      //默认的队伍卡片
      mainCard: 1,
      title: "Your Module",
      allModule: [],
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
        if (this.$store.state.windowSize.windowSizeWidth > 992) {
          return this.$store.state.windowSize.windowSizeWidth * 0.3;
        } else {
          return this.$store.state.windowSize.windowSizeWidth * 0.35;
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
    focusModuleObj() {
      if (this.cardFocusId == 0) {
        return {
          moduleId: -1,
          moduleName: "",
          teacher: {},
          students: [],
          teamIds: [],
        };
      } else {
        // var focusObj = this.moduleSearches.filter((moduleSearche) => {
        //   return moduleSearche.moduleId == this.cardFocusId;
        // });
        // console.log(focusObj);
        // return focusObj[0];
        return this.cardFocusObj;
      }
    },
    teacherObjs() {
      if (this.mainCard == 1) {
        return this.teacherObjsGet(this.$store.state.signInStudentModule);
      } else if (this.mainCard == 2) {
        return this.teacherObjsGet(this.allModule);
      } else {
        return null;
      }
    },
  },
  components: {
    ModuleToolbar,
    ModuleCard,
    ModuleDetail,
    SwiperComponent,
    SwiperComponent,
    CloseBold,
    StripToolbar,
    Refresh
  },
};
</script>

<style scoped src="../../../assets/css/module.css">

</style>

<style scoped src="@/assets/css/team.css">
/* #skele {
  position: absolute;
  z-index: 3;
  width: calc(100% - 40px);
  height: calc(100% - 40px);
}
.el-main {
  position: relative;
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
  color: var(--el-text-color-primary);
}
.left-aside{
  display: flex;
} */
</style>
<style scoped>
.refresh-btn{
  position: absolute;
  top: 0;
  right: 0;
  z-index: 2;
  background: rgba(0, 0, 0, 0);
  border: none;
}
</style>