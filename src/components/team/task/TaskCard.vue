<template>
  <el-card class="task-box">
    <template #header>
      {{ task.taskName }}
    </template>
    <template #default>
      <el-scrollbar :height="340">
        <el-row>
          <el-col :span="24">
            <el-timeline>
              <el-timeline-item
                :timestamp="startTime"
                :color="'#0bbd87'"
                :icon="startIcon"
                :size="'large'"
              >
                Start Time
              </el-timeline-item>
              <el-timeline-item
                :color="color"
                :timestamp="deadline"
                :icon="icon"
                :size="'large'"
              >
                <span>Deadline</span>
                <el-tag type="success" v-if="completed == 'finished'"
                  >Finished</el-tag
                >
                <el-tag type="danger" v-else-if="completed == 'missed'"
                  >Missed</el-tag
                >
                <el-tag type="warning" v-else-if="completed == 'notFinish'"
                  >Haven't Finished</el-tag
                >
              </el-timeline-item>
            </el-timeline>
          </el-col>
        </el-row>
        <div>
          <p>Task Content</p>
          <p>{{ task.context }}</p>
        </div>
        <div class="cooperator">
          <p>Task cooperator</p>
          <swiper
            :modules="modules"
            :enabled="detailStatus"
            :slides-per-view="2"
            :space-between="50"
            :free-mode="true"
            :scrollbar="{ draggable: true }"
            :mousewheel="true"
          >
            <swiper-slide
              v-for="student in task.studentList"
              :key="student.studentId"
            >
              <head-icon
                :user-name="student.studentId"
                :user-icon-src="student.userIconSrc"
                :user-icon-width="50"
                :user-icon-height="50"
                :user-infor="student.studentInfo"
              ></head-icon>
            </swiper-slide>
          </swiper>
        </div>
      </el-scrollbar>
    </template>
  </el-card>
</template>

<script>
import {
  CircleCheckFilled,
  CircleCloseFilled,
  WarningFilled,
  ColdDrink,
} from "@element-plus/icons-vue";
import { Swiper, SwiperSlide } from "swiper/vue/swiper-vue.js";
import { FreeMode, Scrollbar, Mousewheel } from "swiper";
import HeadIcon from "../../HeadIcon.vue";

import "swiper/modules/free-mode/free-mode.min.css";
import "swiper/modules/scrollbar/scrollbar.min.css";
import "swiper/modules/mousewheel/mousewheel.min.css";

export default {
  name: "TaskCard",
  components: {
    ColdDrink,
    CircleCheckFilled,
    CircleCloseFilled,
    WarningFilled,
    Swiper,
    SwiperSlide,
    HeadIcon,
  },
  data() {
    return {
      startIcon: ColdDrink,
      modules: [FreeMode, Scrollbar, Mousewheel],
    };
  },
  props: {
    // task completed icon startTime deadline taskContent
    task: {
      type: Object,
      default: {},
    },
  },
  computed: {
    icon() {
      if (this.completed == "finished") {
        return CircleCheckFilled;
      } else if (this.completed == "missed") {
        return CircleCloseFilled;
      } else if (this.completed == "notFinish") {
        return WarningFilled;
      }
    },
    color() {
      if (this.completed == "finished") {
        return "#0bbd87";
      } else if (this.completed == "missed") {
        return "#f5222d";
      } else if (this.completed == "notFinish") {
        return "#faad14";
      }
    },
    deadline() {
      return this.changeTimeFormat(this.task.deadline);
    },
    startTime() {
      return this.changeTimeFormat(this.task.startTime);
    },
    completed() {
      if (this.task.completed) {
        //完成
        return "finished";
      } else {
        //未完成
        //TODO - 判断是否超时
        var todayTime = new Date().getTime();
        if (todayTime > this.task.deadline) {
          //超时
          return "missed";
        } else {
          //未超时
          return "notFinish";
        }
      }
    },
  },
  methods: {
    changeTimeFormat(timestamp) {
      let _date = new Date(parseInt(timestamp));
      let y = _date.getFullYear();
      let m = _date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      let d = _date.getDate();
      d = d < 10 ? "0" + d : d;
      // console.log( y + '-' + m + '-' + d + ' ' + '　' + h + ':' + minute + ':' + second)
      let dates = y + "-" + m + "-" + d;

      return dates;
    },
  },
};
</script>

<style scoped>
.task-box {
  width: 300px;
  height: 400px;
  margin: 10px;
}
.cooperator{
  margin-bottom: 20px;
}
</style>