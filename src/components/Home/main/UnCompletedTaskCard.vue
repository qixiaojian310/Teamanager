<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <el-row style="width: 100%">
          <el-col :span="12"><p class="task-name">Task name</p></el-col>
          <el-col :span="12"
            ><p class="task-name">{{ unCompletedTaskName }}</p></el-col
          >
        </el-row>
        <el-row style="width:100%">
            <el-col :span="12"><p class="team-name">Team name</p></el-col>
            <el-col :span="12"><p class="team-name">{{teamName}}</p></el-col>
        </el-row>
        <div></div>
      </div>
    </template>
    <div class="card-body">
      <el-row class="card-body-content">
        <el-col :xs="24" :sm="12" class="card-body-content-title">
          <div class="content-box">
            <p>Task content</p>
            <el-scrollbar class="no-scroll-thumb" :height="200">
              <span>{{ unCompletedTaskContent }}</span>
            </el-scrollbar>
          </div>
          <!-- <768时的情况 -->
          <div v-if="widthLess768">
            <p style="height: 40px">Your Cooperator</p>
            <el-scrollbar :style="{ width: cardWidth + 'px' }">
              <div class="head-box">
                <head-icon
                  v-for="cooperatorObj in cooperatorObjs"
                  :key="cooperatorObj.studentId"
                  :userIconHeight="50"
                  :userIconWidth="50"
                  :userIconSrc="cooperatorObj.userIconSrc"
                  :userName="cooperatorObj.name"
                  :userInfor="cooperatorObj.Infor"
                  :widthLess768="widthLess768"
                  :widthMore768Less1200="widthMore768Less1200"
                ></head-icon>
              </div>
            </el-scrollbar>
          </div>
          <div v-else-if="widthMore768Less1200">
            <p style="height: 40px">Your Cooperator</p>
            <div style="height: 260px">
              <el-scrollbar :height="200">
                <head-icon
                  v-for="cooperatorObj in cooperatorObjs"
                  :key="cooperatorObj.studentId"
                  :userIconHeight="50"
                  :userIconWidth="50"
                  :userIconSrc="cooperatorObj.userIconSrc"
                  :userName="cooperatorObj.studentId"
                  :userInfor="cooperatorObj.studentInfo"
                  :widthLess768="widthLess768"
                  :widthMore768Less1200="widthMore768Less1200"
                ></head-icon>
              </el-scrollbar>
            </div>
          </div>
          <div v-else>
            <p style="height: 40px">Your Cooperator</p>
            <div style="height: 260px">
              <el-scrollbar :height="200">
                <head-icon
                  v-for="cooperatorObj in cooperatorObjs"
                  :key="cooperatorObj.studentId"
                  :userIconHeight="50"
                  :userIconWidth="50"
                  :userIconSrc="cooperatorObj.userIconSrc"
                  :userName="cooperatorObj.studentId"
                  :userInfor="cooperatorObj.studentInfo"
                ></head-icon>
              </el-scrollbar>
            </div>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" class="card-body-content-cooperator">
          <p>Time line</p>
          <el-timeline>
            <el-timeline-item
              :color="'#0bbd87'"
              :icon="startIcon"
              :size="'large'"
              :timestamp="startTime"
              placement="top"
            >
              <el-card>
                <template #header>
                  <span>Start time</span>
                </template>
                <div class="date-box">
                  <date-shower
                    :date="startTime.replace(/-/g, '')"
                  ></date-shower>
                </div>
              </el-card>
            </el-timeline-item>
            <el-timeline-item
              :color="color"
              :icon="icon"
              :size="'large'"
              :timestamp="deadline"
              placement="top"
            >
              <el-card>
                <template #header>
                  <span>Deadline</span>
                </template>
                <div class="date-box">
                  <date-shower :date="deadline.replace(/-/g, '')"></date-shower>
                </div>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
import DateShower from "./DateShower.vue";
import HeadIcon from "../../HeadIcon.vue";
export default {
  components: { DateShower, HeadIcon },
  name: "UnCompletedTaskCard",
  mounted() {},
  props: {
    unCompletedTaskName: {
      type: String,
      required: true,
    },
    unCompletedTaskContent: {
      type: String,
      required: true,
    },
    cooperator: {
      type: Array,
      required: true,
    },
    startTime: {
      type: String,
      required: true,
    },
    deadline: {
      type: String,
      required: true,
    },
    teamName: {
      type: String,
      required: true,
    },
    progress: {
      type: Number,
      required: true,
    },
  },
  computed: {
    cooperatorObjs() {
      return this.cooperator;
    },
    widthLess768() {
      if (this.$store.state.windowSize.windowSizeWidth < 768) {
        return true;
      } else {
        return false;
      }
    },
    widthMore768Less1200() {
      if (
        this.$store.state.windowSize.windowSizeWidth > 768 &&
        this.$store.state.windowSize.windowSizeWidth < 1200
      ) {
        return true;
      } else {
        return false;
      }
    },
    cardWidth() {
      return this.$store.state.windowSize.windowSizeWidth - 60;
    },
  },
};
</script>

<style scoped>
.head-box {
  display: flex;
  align-items: center;
}
.date-box {
  display: flex;
  justify-content: center;
  align-items: center;
}
.card-header {
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-direction: column;
}
.task-name {
  color: #242424;
  font-size: 20px;
  font-weight: 900;
}
.team-name {
  color: #242424;
  font-size: 16px;
  font-weight: 900;
}
.card-body-content-title {
  line-height: 1.8;
}
.box-card {
  box-shadow: 0px 0px 5px #888888;
  margin-top: 30px;
  width: 100%;
}
.box-card {
  background: rgba(236, 236, 236, 0.5);
}
.content-box {
  height: 200px;
}
.el-timeline {
  padding-left: 10px !important;
}
.el-timeline{
  --el-timeline-node-color:#000 !important;
}
</style>

