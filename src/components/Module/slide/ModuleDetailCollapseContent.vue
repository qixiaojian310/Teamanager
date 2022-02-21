<template>
  <div>
    <!-- 所有学生的姓名 -->
    <div v-for="studentObj in studentObjs" :key="studentObj.index" class="student-box">
      <head-icon
        :key="studentObj.studentId"
        :userIconHeight="30"
        :userIconWidth="30"
        :userIconSrc="studentObj.userIconSrc"
        :userName="studentObj.name"
        :userInfor="studentObj.Infor"
        :disabled="false"
        :forceDirection="'top'"
      >
      </head-icon>
      {{ studentObj.name }}
    </div>
  </div>
</template>

<script>
import HeadIcon from "../../HeadIcon.vue";

export default {
  name: "ModuleDetailCollapseContent",
  props: {
    studentIds: {
      type: Array,
    },
  },
  computed: {
    studentObjs() {
      var temp = [];
      for (var index = 0; index < this.studentIds.length; index++) {
        //在store中用户的具体索引
        var users = this.$store.state.users;
        var user = users.filter((user) => {
          return user.studentId == this.studentIds[index];
        })[0];
        temp.push(user);
      }
      return temp;
    },
  },
  components: { HeadIcon },
};
</script>

<style scoped>
.student-box {
  display: flex;
  align-items: center;
}
</style>