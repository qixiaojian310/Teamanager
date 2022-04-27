<template>
  <el-container>
    <!-- 工具栏 -->
    <strip-toolbar></strip-toolbar>
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="hidden-sm-and-down"
      :style="{ height: asideHeight + 'px' }"
    >
      <module-toolbar
        :options="teacherObjs"
        @show-list="showList"
        @register-module="registerModule"
        @ensure-choice="selectModule"
        @create-module="createModule"
      ></module-toolbar>
    </el-aside>
    <el-container :style="{ height: asideHeight - 50 + 'px' }">
      <div :height="asideHeigh - 50" :style="{ width: 100 + '%' }">
        <el-main>
          <div ref="skele">
            <el-skeleton animated :rows="skeletonRow" v-if="loading">
            </el-skeleton>
            <el-container v-else id="moduleDetail" ref="moduleDetail">
              <el-header class="title">{{ title }}</el-header>
              <el-scrollbar
                :height="asideHeight - 80 + 'px'"
                :style="{ width: 100 + '%' }"
              >
                <el-scrollbar class="main-box">
                  <el-scrollbar v-if="!editBox">
                    <div
                      :class="{
                        'all-module-card-box': !editBox,
                        'edit-box-card-box': editBox,
                      }"
                      v-if="mainBox == 1"
                    >
                      <module-card
                        v-for="moduleSearche in moduleSearches"
                        :key="moduleSearche.index"
                        @focus-module="focusModule"
                        :module-searche="moduleSearche"
                      ></module-card>
                    </div>
                  </el-scrollbar>
                  <swiper
                    v-else
                    :modules="modules"
                    :scrollbar="{ draggable: true }"
                    :slides-per-view="slidesPerView"
                    navigation
                    :pagination="{ clickable: true }"
                    :free-mode="true"
                    :mousewheel="{releaseOnEdges: true}"
                  >
                    <swiper-slide
                      v-for="moduleSearche in moduleSearches"
                      :key="moduleSearche.index"
                    >
                      <module-card
                        @focus-module="focusModule"
                        :module-searche="moduleSearche"
                      ></module-card>
                    </swiper-slide>
                  </swiper>
                  <!-- edit部分 -->
                  <register-module
                    v-if="editBox"
                    :module-name="focusModuleObj.moduleName"
                    :focusModuleId="focusModuleObj.moduleId"
                  ></register-module>
                  <!-- create -->
                  <create-module v-if="mainBox == 2"></create-module>
                </el-scrollbar>
              </el-scrollbar>
            </el-container>
          </div>
        </el-main>
      </div>
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
      <module-detail
        :focus-module-obj="focusModuleObj"
        :aside-height="asideHeight"
        :team-objs="focusModuleTeams"
      ></module-detail>
    </el-aside>
  </el-container>
</template>

<script>
// import SwiperTest from "../../swiperTest/SwiperComponent.vue";
import ModuleToolbar from "../../toolbar/ModuleToolbar.vue";
import ModuleCard from "../../card/ModuleCard.vue";
import ModuleDetail from "../slide/ModuleDetail.vue";
import { CloseBold } from "@element-plus/icons-vue";
import SwiperComponent from "../../swiperTest/SwiperComponent.vue";
import RegisterModule from "../joinModule/RegisterModule.vue";
import qs from "qs";
import CreateModule from "../joinModule/CreateModule.vue";
import StripToolbar from "../../toolbar/StripToolbar.vue";

import { Navigation, Pagination, Scrollbar, FreeMode,Mousewheel } from "swiper";

// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue/swiper-vue.js";

// Import Swiper styles
import "swiper/swiper.min.css";
import "swiper/modules/navigation/navigation.min.css";
import "swiper/modules/pagination/pagination.min.css";
import "swiper/modules/scrollbar/scrollbar.min.css";
import "swiper/modules/free-mode/free-mode.min.css";
import "swiper/modules/mousewheel/mousewheel.min.css";

export default {
  name: "TeacherModule",
  mounted() {
    this.containerWidth = this.$refs.skele.clientWidth;
    this.getTeamUrl();
  },
  methods: {
    deleteModule(){
      this.cardFocusObj = {};
      this.cardFocusId = -1;
    },
    getTeamUrl() {
      var moduleFocusId = this.$route.params.moduleId;
      this.$store.state.signInTeacherModule.forEach((element) => {
        if (element.moduleId == moduleFocusId) {
          this.focusModule(element);
        }
      });
    },
    showList: function () {
      this.isCardBox = true;
      this.title = "Your Module";
    },
    //该方法通过点击的module的组信息获取组对象
    setTeamObj(cardFocusObjInject) {
      var injectData = {};
      injectData.teamIds = cardFocusObjInject.teamIds;
      this.axios({
        url: "/getAllTeamByTeamIds",
        data: qs.stringify(injectData, { indices: false }),
        method: "post",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        this.focusModuleTeams = res.data;
      }).then(() => {
        setTimeout(()=>{
          console.log(this.$refs.skele.clientWidth);
        this.containerWidth = this.$refs.skele.clientWidth;
        },500)
      });
    },
    focusModule(cardFocusObjInject) {
      var cardFocusObj = cardFocusObjInject;
      if (this.cardFocusId == cardFocusObj.moduleId && this.cardFocusId != 0) {
        // this.cardFocus = this.cardFocus ? false : true;
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
      setTimeout(()=>{
        console.log(this.$refs.skele.clientWidth);
        this.containerWidth = this.$refs.skele.clientWidth;
      },500)
    },
    createTeacherOption: function (id, number) {
      var teacherOption = new Object();
      teacherOption.teacherNumber = number;
      teacherOption.teacherId = id;
      return teacherOption;
    },
    searchTeacher: function (teacherFind) {
      for (var index = 0; index < this.$store.state.teacher.length; index++) {
        //在store中用户的具体索引;
        var teacher = this.$store.state.teacher.filter((teacher) => {
          return teacher.teacherId == teacherFind;
        })[0];
        console.log(teacher);
        return teacher;
      }
    },
    selectModule: function (teacherIds) {
      //NOTE 检查级联选择器的值
      // NOTE teacherIds是一个数组
      var res = [];
      res = this.$store.state.signInStudentModule.filter((module) => {
        for (let index = 0; index < teacherIds.length; index++) {
          if (module.teacherId == teacherIds[index]) {
            return module;
          }
        }
      });
      this.moduleSearches = res;
    },
    registerModule: function () {
      this.editBox = true;
      this.mainBox = 1;
      this.title = "Register Center";
    },
    createModule: function () {
      this.editBox = false;
      this.mainBox = 2;
      this.title = "Create Center";
    },
    showList: function () {
      this.editBox = false;
      this.mainBox = 1;
      this.title = "Your Module";
    },
  },
  data() {
    return {
      mainBox: 1,
      ModuleTitle: "Module Title",
      cardFocus: false,
      cardFocusId: 0,
      loading: false,
      activeName: "1",
      // NOTE: 后端获取module所有人员使用id返回一个idList
      moduleSearches: this.$store.state.signInTeacherModule,
      title: "Your Teams",
      cardFocusObj: {},
      focusModuleTeams: [],
      editBox: false,
      modules: [Navigation, Pagination, Scrollbar, FreeMode,Mousewheel],
      containerWidth:0
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
    slidesPerView(){
      this.containerWidth = this.$refs.skele.clientWidth;
      return Math.floor(this.containerWidth / 200);
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
          studentS: [],
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
      var noRepeatTeachers = [];
      console.log("length" + noRepeatTeachers.length);
      //flag 为假表示这个数组从没有找到过这个元素
      var flag = false;
      for (var i = 0; i < this.$store.state.signInStudentModule.length; i++) {
        var key = this.$store.state.signInStudentModule[i].teacherId;
        //flag 为假表示这个数组从没有找到过这个元素
        flag = false;
        // console.log(this.searchTeacher(key).teacherName);
        // var teacherObj = this.createTeacherOption(
        //   key,
        //   this.searchTeacher(key).teacherName,
        //   1
        // );
        // for (var index = 0; index < noRepeatTeachers.length; index++) {
        //   if (noRepeatTeachers[index].teacherId == key) {
        //     noRepeatTeachers[index].teacherNumber += 1;
        //     var flag = true;
        //   }
        // }
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
  },
  components: {
    ModuleToolbar,
    ModuleCard,
    ModuleDetail,
    SwiperComponent,
    SwiperComponent,
    CloseBold,
    RegisterModule,
    CreateModule,
    StripToolbar,
    Swiper,
    SwiperSlide,
  },
};
</script>

<style scoped>
.title {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: bold;
  color: rgb(15, 15, 15);
}
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
  position: relative;
}
.all-module-card-box {
  height: 100%;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}
.edit-box-card-box {
  height: 200px;
  width: 100%;
  display: flex;
  flex-wrap: nowrap;
}
.swiper-container {
  width: 100%;
  height: 200px;
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
.main-box {
  display: flex;
  flex-direction: column;
}
</style>
