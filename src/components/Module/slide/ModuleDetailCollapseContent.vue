<template>
  <div>
    <!-- 所有学生的姓名 -->
    <div
      v-for="studentObj in studentObjs"
      :key="studentObj.index"
      class="student-box"
    >
      <head-icon
        :key="studentObj.studentId"
        :userIconHeight="30"
        :userIconWidth="30"
        :userIconSrc="studentObj.userIconSrc"
        :userName="studentObj.studentId"
        :userInfor="studentObj.studentInfo"
        :disabled="false"
        :forceDirection="'top'"
      >
      </head-icon>
      {{ studentObj.studentId }}
    </div>
    <div class="button-box">
      <el-button
        type="primary"
        @click="joinTeam"
        :disabled="haveJoinTeam"
        v-if="this.$store.state.role != 'teacher'"
      >
        <el-icon><Edit /></el-icon>
        <span>Join this team</span>
        <span>{{ studentListLength + "/" + teamSize }}</span>
      </el-button>
      <el-button type="danger" v-else @click="deleteTeam">
        <i class="fa fa-trash-o"></i>
        Delete team
      </el-button>
    </div>
  </div>
</template>

<script>
import { Edit } from "@element-plus/icons-vue";
import HeadIcon from "../../HeadIcon.vue";

export default {
  name: "ModuleDetailCollapseContent",
  props: {
    // studentIds: {
    //   type: Array,
    // },
    teamId: {
      type: Number,
    },
    studentListLength: {
      type: Number,
    },
    teamSize: {
      type: Number,
    },
    studentObjs: {
      type: Array,
    },
    haveJoinTeam: {
      type: Boolean,
    },
  },
  methods: {
    joinTeam() {
      this.$emit("joinTeam", this.teamId);
    },
    deleteTeam() {
      this.$emit("deleteTeam", this.teamId);
    },
  },
  computed: {
    // studentObjs() {
    //   var temp = [];
    //   for (var index = 0; index < this.studentIds.length; index++) {
    //     //在store中用户的具体索引
    //     var users = this.$store.state.users;
    //     var user = users.filter((user) => {
    //       return user.studentId == this.studentIds[index];
    //     })[0];
    //     temp.push(user);
    //   }
    //   return temp;
    // },
  },
  components: { HeadIcon, Edit },
};
</script>

<style scoped>
.student-box {
  display: flex;
  align-items: center;
  padding: 10px;
}
span {
  padding-left: 5px;
}
.button-box {
  display: flex;
  justify-content: center;
}
</style>
