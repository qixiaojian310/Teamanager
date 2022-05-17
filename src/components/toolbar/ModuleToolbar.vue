<template>
  <div class="toolbar">
    <!-- TODO - toolbar 应该有两部分 -->
    <!-- 下首先应该有总览，上会出现各种排序方式加一个搜索框，右边是搜索结果
    下有加入，上面有搜素框，右边是搜索结果 -->
    <div class="toolbar-list" v-if="this.$store.state.role != 'teacher'">
      <!-- <sign-input-item
        :name="'Search'"
        v-model:inputValue="SearchContent"
        :fontSize="'1.2rem'"
        :backgroundColor="'#e6e1e1'"
        :inputHeight="2"
      ></sign-input-item> -->
      <div v-if="isStudent">
        <el-cascader
          placeholder="Try searchingL Guide"
          :options="options"
          v-model="value"
          :props="props"
          filterable
        >
          <template #default="{ data }">
            <span>{{ data.teacherId }}</span>
            <span> ({{ data.teacherNumber }}) </span>
          </template>
        </el-cascader>
        <el-button @click="ensureChoice">
          <el-icon><document-checked /></el-icon>
        </el-button>
      </div>
    </div>
    <div class="toolbar-list">
      <button class="btn-all-module" @click="showList">
        <i class="fa fa-bars"></i>
        <span>Your Modules</span>
      </button>
      <!-- TODO对于老师要执行添加的效果，可以提交不同的事件到上层当中就可以实现 -->
      <button v-if="isTeacher" class="btn-all-module" @click="add">
        <i class="fa fa-edit"></i>
        <span >Edit a Module</span>
      </button>
      <button v-else class="btn-all-module" @click="add">
        <i class="fa fa-edit"></i>
        <span >All Modules</span>
      </button>
      <button v-if="isTeacher" class="btn-all-module" @click="create">
        <i class="fa fa-building-o"></i>
        <span >Create a Module</span>
      </button>
    </div>
  </div>
</template>

<script>
import SignInputItem from "../sign/SignInputItem.vue";
import { DocumentChecked } from "@element-plus/icons-vue";
export default {
  components: { SignInputItem, DocumentChecked },
  name: "ModuleToolbar",
  data() {
    return {
      SearchContent: "",
      props: {
        multiple: true,
        label: "teacherId",
        value: "teacherId",
      },
      value: [],
      isTeacher: false,
      isStudent: false,
    };
  },
  props: {
    options: {
      type: Array,
    },
  },
  methods: {
    ensureChoice: function () {
      if (this.value.length == 0) {
        // 取消了选择，改为全选
        for (let index = 0; index < this.options.length; index++) {
          var temp = this.options[index];
          this.value.push(temp.teacherId);
        }
      }
      console.log("级联选择器的值为" + this.value);
      this.$emit("ensureChoice", this.value);
    },
    add: function () {
      if (this.isTeacher) {
        this.$emit("registerModule");
      } else {
        this.$emit("addModule");
      }
    },
    create:function(){
      if(this.isTeacher){
        this.$emit("createModule");
      }
    },
    showList: function () {
      this.$emit("showList");
    },
  },
  mounted() {
    // 用路由信息判断是老师还是学生
    if (this.$route.name == "StudentModulePage" || this.$route.name == "StudentModule") {
      this.isTeacher = false;
      this.isStudent = true;
    } else {
      this.isTeacher = true;
      this.isStudent = false;
    }
  },
};
</script>

<style scoped>
.toolbar {
  height: 100%;
  background-color: var(--team-toolbar-bgcolor);
  width: 100%
}
.toolbar-list {
  height: 45%;
  padding: 5%;
  width: 90%;
}

.btn-all-module {
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  color: #000000;
  background-color: #ffcc00;
  border: 2px solid #000000;
  width: 80%;
  display: block;
  text-align: center;
  padding: 2%;
  margin: 10px;
  border-radius: 10px;
  font-size: 1.2rem;
}

.btn-add-module {
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  color: #066e14;
  background-color: #65b54d56;
  border: 2px solid #65b54d;
  width: 80%;
  display: block;
  text-align: center;
  padding: 2%;
  margin-top: 10px;
  border-radius: 10px;
  font-size: 1.2rem;
}
.el-button {
  margin-left: 0px !important;
}
</style>
<style scoped>
i{
  margin-right: 5px;
}
</style>
<style>
.el-cascader-panel{
  background: var(--panel-bg-color);
}
</style>
