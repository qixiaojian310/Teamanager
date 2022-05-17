<template>
    <el-select v-model="color" class="m-2" placeholder="Select" size="large" @change="changeColor">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
    >
      <div :style="{background:item.color}" style="width:100%;height:100%"></div>
    </el-option>
  </el-select>
  <el-button @click="changeTheme" style="background-color:var(--header-link-bgcolor)">
    <i class="fa fa-2x fa-sun-o fa-spin"></i>
  </el-button>
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
import { useDark,useToggle } from "@vueuse/core";
import { addColor } from "../../../theme/theme";

export default {
  name: "HeaderLink",
  data(){
    return{
      dark: useDark(),
      color:"green",
      options:[
        {
          label:"green",
          color:"#16a951",
          value:"green"
        },
        {
          label:"red",
          color:"#f20c00",
          value:"red"
        },
        {
          label:"yellow",
          color:"#ffa400",
          value:"yellow"
        },
        {
          label:"blue",
          color:"#44cef6",
          value:"blue"
        },
      ]
    }
  },
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
  methods: {
    changeTheme(){
      // useToggle(this.dark);
      this.dark = !this.dark
      addColor(this.dark,this.color)
    },
    changeColor(){
      addColor(this.dark,this.color)
    }
  }
};
</script>

<style scoped>
a {
  color: var(--header-link-color);
  background: var(--header-link-bgcolor);
  text-decoration: none;
  text-align: center;
  text-decoration: none;
  line-height: 60px;
  width: 25%;
  transition: background 0.5s cubic-bezier(0.4, 0, 0.2, 1);
  border-radius: 5px;
}
a:hover {
  transition: background 0.5s cubic-bezier(0.4, 0, 0.2, 1);
  background: var(--header-link-hover-bgcolor);
  color: var(--header-link-hover-color);
}
.el-button{
  height:100%;
  width: 12.5%;
  border:none;
  border-radius: 5px;
}
</style>