<template>
  <el-card class="card" :body-style="{ padding: '10px' }">
    <template #header>
      <div class="card-header">
        <div class="card-header-title">
          <span>{{ moduleSearche.moduleName }}</span>
        </div>
      </div>
    </template>
    <template #default>
      <div>
        <div class="teacher-name">
          this module is register by {{ searchTeacher.teacherName }}
        </div>
        <div>
          <el-button @click="focusModule">
            <el-icon :size="20">
              <edit></edit>
            </el-icon>
            <p>View it</p>
          </el-button>
        </div>
      </div>
    </template>
  </el-card>
</template>

<script>
import { Edit } from "@element-plus/icons-vue";

export default {
  name: "ModuleCard",
  props: {
    moduleSearche: {
      type: Object,
    },
  },
  data(){
    return {
      teachers : this.$store.state.teacher
}
  },
  methods: {
    focusModule: function () {
      this.$emit("focusModule", this.moduleSearche.moduleId);
    },
  },
  components: {
    Edit,
  },
  computed: {
    searchTeacher: function () {
      for (var index = 0; index < this.teachers.length; index++) {
        //在store中用户的具体索引;
        var teacher = this.teachers.filter((teacher) => {
          return teacher.teacherId == this.moduleSearche.teacherId;
        })[0];
        console.log(teacher);
        return teacher;
      }
    },
  },
};
</script>

<style scoped>
.card {
  width: 190px;
  margin-left: 10px;
  margin-right: 10px;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 20px;
  border-radius: 10px;
}
.teacher-name {
  font-size: 0.5rem;
  color: #414040;
  margin-bottom: 5px;
}
</style>