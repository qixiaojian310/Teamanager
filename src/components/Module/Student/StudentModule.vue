<template>
  <el-container>
    <!-- 工具栏 -->
    <el-aside
      :width="asideLeftWidth + 'px'"
      class="hidden-sm-and-down"
      :style="{ height: asideHeight + 'px' }"
    >
      <student-toolbar></student-toolbar>
    </el-aside>
    <el-container :style="{ height: asideHeight + 'px' }">
      <el-scrollbar :height="asideHeight" :style="{width:100+'%'}">
        <el-main>
          <div ref="skele">
            <el-skeleton animated :rows="skeletonRow" v-if="loading">
            </el-skeleton>
            <el-container v-else>
              <div class="card-box">
                <el-card
                  class="card"
                  v-for="moduleSearche in moduleSearches"
                  :key="moduleSearche.index"
                >
                  <template #header>
                    <div class="card-header">
                      <div class="card-header-title">
                        <span>{{ moduleSearche.moduleName }}</span>
                      </div>
                    </div>
                  </template>
                </el-card>
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
      <el-scrollbar>
        <div
          class="scroll-bar-right"
          :style="{ height: asideHeight - 50 + 'px' }"
        >
          <el-scrollbar :wrap-class="'scroll-bar-right'">
            <p>{{ ModuleTitle }}</p>
          </el-scrollbar>
        </div>
      </el-scrollbar>
    </el-aside>
  </el-container>
</template>

<script>
import StudentToolbar from "./StudentToolbar.vue";

export default {
  name: "StudentModule",
  data() {
    return {
      ModuleTitle: "Module Title",
      cardFocus: false,
      loading: true,
      // NOTE: 后端获取module所有人员使用id返回一个idList
      moduleSearches: [
        {
          moduleName: "Module 1",
          teacher: "Module 1 teacher",
          studentIdList: [1, 2, 4],
          teamIdList: [1],
        },
        {
          moduleName: "Module 1",
          teacher: "Module 1 teacher",
          studentIdList: [1, 2, 4],
          teamIdList: [1],
        },
        {
          moduleName: "Module 1",
          teacher: "Module 1 teacher",
          studentIdList: [1, 2, 4],
          teamIdList: [1],
        },
        {
          moduleName: "Module 1",
          teacher: "Module 1 teacher",
          studentIdList: [1, 2, 4],
          teamIdList: [1],
        },
        {
          moduleName: "Module 1",
          teacher: "Module 1 teacher",
          studentIdList: [1, 2, 4],
          teamIdList: [1],
        },
        {
          moduleName: "Module 1",
          teacher: "Module 1 teacher",
          studentIdList: [1, 2, 4],
          teamIdList: [1],
        },
      ],
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
  },
  components: {
    StudentToolbar,
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
}
.scroll-bar-right {
  background: #e0e0e0;
  margin-left: 60px;
  margin-right: 60px;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 20px;
  border-radius: 10px;
  overflow-x: hidden;
  overflow-y: auto;
}
.scroll-bar-right > div p {
  font-size: 20px;
  font-weight: 700;
  margin-left: 30px;
}
.card-box {
  display: flex;
  flex-wrap: wrap;
  align-content: center;
}
.card {
  width: 200px;
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 20px;
  border-radius: 10px;
}
</style>