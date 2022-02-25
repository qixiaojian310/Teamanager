<template>
  <el-container>
    <!-- 工具栏 -->
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="hidden-sm-and-down"
      :style="{ height: asideHeight + 'px' }"
    >
      <module-toolbar :options="teacherObjs" @show-list="showList" @add-module="addModule" @ensure-choice="selectModule"></module-toolbar>
    </el-aside>
    <el-container :style="{ height: asideHeight - 50 + 'px' }">
      <el-scrollbar :height="asideHeight - 50" :style="{ width: 100 + '%' }">
        <el-main>
          <div ref="skele">
            <el-skeleton animated :rows="skeletonRow" v-if="loading">
            </el-skeleton>
            <el-container v-else>
              <div class="card-box">
                <module-card
                  v-for="moduleSearche in moduleSearches"
                  :key="moduleSearche.index"
                  @focus-module="focusModule"
                  :module-searche="moduleSearche"
                ></module-card>
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
      <el-button class="close-btn" @click="closeDetail">
        <el-icon :size="30" :color="'#ffffff'">
          <close-bold></close-bold>
        </el-icon>
      </el-button>
      <module-detail
        :focusModuleObj="focusModuleObj"
        :asideHeight="asideHeight"
      ></module-detail>
    </el-aside>
  </el-container>
</template>

<script>
// import SwiperTest from "../../swiperTest/SwiperComponent.vue";
import ModuleToolbar from "../toolbar/ModuleToolbar.vue";
import ModuleCard from "../../card/ModuleCard.vue";
import ModuleDetail from "../slide/ModuleDetail.vue";
import { CloseBold } from "@element-plus/icons-vue";
import SwiperComponent from "../../swiperTest/SwiperComponent.vue";

export default {
  name: "StudentModule",
  methods: {
    showList:function(){
      this.isCardBox = true;
      this.title = "Your Module"
    },
    focusModule: function (cardFocusId) {
      if (this.cardFocusId == cardFocusId && this.cardFocusId != 0) {
        //点击了相同的id
        this.cardFocus = this.cardFocus ? false : true;
      } else {
        //点击了另一个课程，先要关闭原来的再打开新的
        this.cardFocus = false;
        this.cardFocusId = cardFocusId;
        this.cardFocus = true;
      }
      this.cardFocusId = cardFocusId;
    },
    closeDetail: function () {
      this.cardFocus = false;
    },
    createTeacherOption: function (id, name, number) {
      var teacherOption = new Object();
      teacherOption.teacherName = name;
      teacherOption.teacherNumber = number;
      teacherOption.teacherId = id;
      return teacherOption;
    },
    searchTeacher:function(teacherFind){
      for (var index = 0; index < this.$store.state.teacher.length; index++) {
        //在store中用户的具体索引;
        var teacher = this.$store.state.teacher.filter((teacher) => {
          return teacher.teacherId == teacherFind
        })[0];
        console.log(teacher);
        return teacher;
      }
    },
    selectModule:function(teacherIds){
      //NOTE 检查级联选择器的值
      // NOTE teacherIds是一个数组
      var res = [];
      res = this.$store.state.signInStudentModule.filter((module)=>{
        for (let index = 0; index < teacherIds.length; index++) {
          if(module.teacherId == teacherIds[index]){
            return module
          }
        }
      })
      this.moduleSearches = res
    }
  },
  data() {
    return {
      ModuleTitle: "Module Title",
      cardFocus: false,
      cardFocusId: 0,
      loading: false,
      activeName: "1",
      // NOTE: 后端获取module所有人员使用id返回一个idList
      moduleSearches: this.$store.state.signInStudentModule,
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
          teacherId: 1,
          studentIdList: [],
          teamIds: [],
        };
      } else {
        var focusObj = this.moduleSearches.filter((moduleSearche) => {
          return moduleSearche.moduleId == this.cardFocusId;
        });
        console.log(focusObj);
        return focusObj[0];
      }
    },
    teacherObjs() {
      var noRepeatTeachers = [];
      console.log("length"+noRepeatTeachers.length);
      //flag 为假表示这个数组从没有找到过这个元素
      var flag = false;
      for (var i = 0; i < this.$store.state.signInStudentModule.length; i++) {
        var key = this.$store.state.signInStudentModule[i].teacherId;
        //flag 为假表示这个数组从没有找到过这个元素
        flag = false;
        console.log(this.searchTeacher(key).teacherName);
        var teacherObj = this.createTeacherOption(key, this.searchTeacher(key).teacherName, 1);
        for (var index = 0; index < noRepeatTeachers.length; index++) {
          if (noRepeatTeachers[index].teacherId == key) {
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
  background: linear-gradient(#df7599, #3994be);
  box-shadow: -1px 0px 7px #888888;
  overflow: hidden;
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
</style>