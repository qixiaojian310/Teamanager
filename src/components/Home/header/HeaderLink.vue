<template>
  <router-link to="/chat">
    <i class="fa fa-user"></i>
    <span>Chat</span>
  </router-link>
  <router-link to="/module">
    <i class="fa fa-user"></i> 
    <span>Module</span>
    </router-link>
  <router-link to="/team">
    <i class="fa fa-user"></i> 
    <span>Team</span>
    </router-link>
  <router-link to="/user">
    <i class="fa fa-user"></i>
    <span>User</span>
  </router-link>
</template>

<script>
export default {
  name: "HeaderLink",
};
</script>

<style scoped>
a {
  color: rgb(91, 113, 167);
  background: #e0e0e0;
  text-decoration: none;
  text-align: center;
  text-decoration: none;
  line-height: 60px;
  width: 25%;
}

</style>