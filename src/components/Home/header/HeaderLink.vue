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
  color: #000000;
  background: #DF7599;
  text-decoration: none;
  text-align: center;
  text-decoration: none;
  line-height: 60px;
  width: 25%;
  transition: background 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}
a:hover{
  transition: background 0.5s cubic-bezier(0.4, 0, 0.2, 1);
  background: #fac1d3;
  color: #000000;
}

</style>