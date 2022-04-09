<template>
  <router-link :to="linkBase + '/chat'">
    <i class="fa fa-wechat"></i>
    <span>Chat</span>
  </router-link>
  <router-link :to="linkBase">
    <i class="fa fa-user"></i>
    <span>User</span>
  </router-link>
</template>

<script>
export default {
  name: "HeaderLink",
  computed: {
    linkBase() {
      var username =
        this.$store.state.role == "student"
          ? this.$store.state.signInStudent.name
          : this.$store.state.signInTeacher.name;
      return (
        "/" +
        this.$store.state.role +
        "/" +
        username
      );
    },
  },
};
</script>

<style scoped>
a {
  color: #000000;
  background: #53BC43;
  text-decoration: none;
  text-align: center;
  text-decoration: none;
  line-height: 60px;
  width: 25%;
  transition: background 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}
a:hover {
  transition: background 0.5s cubic-bezier(0.4, 0, 0.2, 1);
  background: #3a852f;
  color: #000000;
}
</style>