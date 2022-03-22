<template>
  <div class="toolbar">
    <!-- TODO - toolbar 应该有两部分 -->
    <!-- 下首先应该有总览，上会出现各种排序方式加一个搜索框，右边是搜索结果
    下有加入，上面有搜素框，右边是搜索结果 -->
    <div class="toolbar-list">
      <sign-input-item
        :name="'Search'"
        v-model:inputValue="SearchContent"
        :fontSize="'1.2rem'"
        :backgroundColor="'#e6e1e1'"
        :inputHeight="2"
      ></sign-input-item>
      <div v-if="isStudent" style="background-color: #fff">
        <el-cascader-panel
          placeholder="Try searching Guide"
          :options="options"
          v-model="value"
          :props="props"
          filterable
        >
          <template #default="{ data }" v-if="isModule">
            <span>{{ data.teacherId }}</span>
            <span> ({{ data.teacherNumber }}) </span>
          </template>
          <template #default="{ data }" v-else>
            <span>{{ data.leaderId }}</span>
            <span> ({{ data.leaderNumber }}) </span>
          </template>
        </el-cascader-panel>
        <el-button style="width: 100%" @click="ensureChoice">
          <el-icon><document-checked /></el-icon>
          Filter your team
        </el-button>
      </div>
    </div>
    <div class="toolbar-list">
      <div class="btn-box">
        <transition name="team-detail" mode="in-out">
          <button class="btn-all-team" @click="showDetail" v-if="isDetail">
            <i class="fa fa-bars"></i>
            <span>See details</span>
          </button>
          <button class="btn-all-team" @click="showList" v-else>
            <i class="fa fa-bars"></i>
            <span>ALL teams</span>
          </button>
        </transition>
      </div>
      <!-- TODO对于老师要执行添加的效果，可以提交不同的事件到上层当中就可以实现 -->
      <!-- 点击提示 -->
      <div class="btn-box">
        <transition name="team-detail" mode="in-out">
          <div v-if="isDetail">
            <button class="btn-all-team" @click="vote">
              <i class="fa fa-ticket"></i>
              <span>Vote</span>
            </button>
          </div>
          <div v-else class="hint-box">
            <p>You can see more detail by choose a team</p>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import SignInputItem from "../sign/SignInputItem.vue";
import { DocumentChecked } from "@element-plus/icons-vue";
export default {
  components: { SignInputItem, DocumentChecked },
  name: "TeamToolBar",
  data() {
    return {
      SearchContent: "",

      value: [],
      isTeacher: false,
      isStudent: false,
    };
  },
  props: {
    options: {
      type: Array,
    },
    isModule: {
      type: Boolean,
      default: true,
    },
    isDetail: {
      type: Boolean,
      default: false,
    },
  },
  computed: {
    props() {
      if (this.isModule) {
        return {
          multiple: true,
          label: "teacherId",
          value: "teacherId",
        };
      } else {
        return {
          multiple: true,
          label: "leaderId",
          value: "leaderId",
        };
      }
    },
  },
  methods: {
    ensureChoice: function () {
      if (this.value.length == 0) {
        // 取消了选择，改为全选
        for (let index = 0; index < this.options.length; index++) {
          var temp = this.options[index];
          if (this.isModule) {
            //课程全选老师否则全选leader
            this.value.push(temp.teacherId);
          } else {
            this.value.push(temp.leaderId);
          }
        }
      }
      console.log("级联选择器的值为" + this.value);
      this.$emit("ensureChoice", this.value);
    },
    add: function () {
      if (this.isTeacher) {
        this.$emit("registerteam");
      } else {
        this.$emit("addteam");
      }
    },
    create: function () {
      if (this.isTeacher) {
        this.$emit("createteam");
      }
    },
    showList: function () {
      this.$emit("showList");
    },
  },
  mounted() {
    // 用路由信息判断是老师还是学生
    if (this.$route.name == "StudentTeamPage") {
      this.isTeacher = false;
      this.isStudent = true;
    } else {
      this.isTeacher = true;
      this.isStudent = false;
    }
  },
};
</script>

<style>
.toolbar {
  height: 100%;
  width: 100%;
  /* margin-bottom: 5%;
  margin-top: 5%; */
  background-color: #e6e1e1;
}
.toolbar-list {
  height: 45%;
  padding: 5%;
  width: 90%;
}

.btn-all-team {
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  color: #df7599;
  background-color: #fac1d3;
  border: 2px solid #df7599;
  width: 100%;
  display: block;
  text-align: start;
  padding: 0.3rem;
  padding-left: 1rem;
  border-radius: 10px;
  font-size: 1.2rem;
  margin-top: 1rem;
  position: absolute;
}
.el-button {
  margin-left: 0px !important;
}
</style>
<style scoped>
i {
  margin-right: 5px;
}
.hint-box {
  position: absolute;
  width: 100%;
  color: white;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  margin-top: 1rem;
}
.hint-box > p {
  margin: 1rem;
}
.team-detail-enter-active,
.team-detail-leave-active {
  transition: opacity 0.5s;
}

.team-detail-enter-from,
.team-detail-leave-to {
  opacity: 0;
}

.btn-box {
  position: relative;
  height: 3.2rem;
}
</style>
