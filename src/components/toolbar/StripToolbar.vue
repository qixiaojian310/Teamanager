<template>
  <div class="strip-toolbar">
    <div class="btn-top">
      <!-- team -->
      <toolbar-icon :address="linkBase + '/teamMain'">
        <template #icon>
          <svg
            t="1646884960860"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="3763"
            width="32"
            height="32"
          >
            <path
              d="M895.888014 960.007999v-191.976003a64.887889 64.887889 0 0 0-63.992001-63.992001h-63.992001a63.992001 63.992001 0 0 1 0-127.984002h63.992001a192.551931 192.551931 0 0 1 191.976003 191.976003v191.976003a63.992001 63.992001 0 1 1-127.984002 0z m-383.952006 0v-191.976003a64.887889 64.887889 0 0 0-63.992001-63.992001H191.976003a64.887889 64.887889 0 0 0-63.992001 63.992001v191.976003a63.992001 63.992001 0 1 1-127.984002 0v-191.976003a192.551931 192.551931 0 0 1 191.976003-191.976003h255.968004a192.551931 192.551931 0 0 1 191.976003 191.976003v191.976003a63.992001 63.992001 0 1 1-127.984002 0z m127.984002-511.936008a63.992001 63.992001 0 0 1 63.992001-63.992001 127.984002 127.984002 0 1 0 0-255.968004 63.992001 63.992001 0 1 1 0-127.984002 255.968004 255.968004 0 1 1 0 511.936008 63.992001 63.992001 0 0 1-63.992001-63.992001z m-575.928009-191.976003a255.968004 255.968004 0 1 1 255.968004 255.968004 255.968004 255.968004 0 0 1-255.968004-255.968004z m127.984002 0a127.984002 127.984002 0 1 0 127.984002-127.984002 127.984002 127.984002 0 0 0-127.984002 127.984002z"
              :fill="color"
              p-id="3764"
            ></path>
          </svg>
        </template>
        <template #content>
          <p>Your Team</p>
        </template>
      </toolbar-icon>
      <!-- 课程 -->
      <toolbar-icon :address="linkBase + '/module'">
        <template #icon>
          <svg
            t="1646885067028"
            class="icon"
            viewBox="0 0 1171 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="5704"
            width="32"
            height="32"
          >
            <path
              d="M1121.034 947.942c0 0-142.543-24.912-274.03-24.912-130.08 0-249.119 24.912-249.119 24.912-4.327 0-8.453-0.725-12.456-1.761-4.003 1.036-8.129 1.761-12.456 1.761 0 0-142.543-24.912-274.03-24.912-130.08 0-249.119 24.912-249.119 24.912-27.518 0-49.824-22.306-49.824-49.824L0 175.674c0 33.215 29.565-99.647 309.892-99.647 157.587 0 236.431 40.771 275.57 71.502 38.97-30.731 117.131-71.502 272.491-71.502 284.344 0 312.905 132.862 312.905 99.647l0 722.444C1170.858 925.636 1148.552 947.942 1121.034 947.942zM548.061 225.498c0 0-49.824-74.736-249.119-74.736-166.081 0-224.207 74.736-224.207 74.736l0 622.797c0 27.518 22.306 24.912 49.824 24.912 0 0 85.433-24.912 174.384-24.912 97.495 0 199.295 24.912 199.295 24.912 27.518 0 49.824 2.606 49.824-24.912L548.062 225.498zM1096.122 225.498c0 0-27.802-41.543-124.559-62.676l0 386.53-99.647-74.736-99.647 74.736L772.269 156.537c-109.032 17.411-149.471 68.961-149.471 68.961l0 622.797c0 27.518 22.306 24.912 49.824 24.912 0 0 85.433-24.912 174.383-24.912 97.495 0 199.295 24.912 199.295 24.912 27.518 0 49.824 2.606 49.824-24.912L1096.124 225.498zM423.502 673.911 199.295 673.911c-13.759 0-24.912-11.153-24.912-24.912l0-24.912c0-13.759 11.153-24.912 24.912-24.912l224.207 0c13.759 0 24.912 11.153 24.912 24.912L448.414 649C448.414 662.758 437.261 673.911 423.502 673.911zM423.502 449.705 199.295 449.705c-13.759 0-24.912-11.153-24.912-24.912l0-24.912c0-13.759 11.153-24.912 24.912-24.912l224.207 0c13.759 0 24.912 11.153 24.912 24.912l0 24.912C448.414 438.552 437.261 449.705 423.502 449.705z"
              :fill="color"
              p-id="5705"
            ></path>
          </svg>
        </template>
        <template #content>
          <p>Your Module</p>
        </template>
      </toolbar-icon>
    </div>
    <div class="btn-bottom">
      <head-icon
        :forceDirection="'right'"
        :userIconWidth="32"
        :userIconSrc="''"
        :userName="username"
        :userInfor="userInfor"
      ></head-icon>
      <el-button circle type="info" style="height:40px;width:40px;margin-bottom:10px;">
        <el-icon :size="30"><SwitchButton /></el-icon>
      </el-button>
    </div>
  </div>
</template>

<script>
import ToolbarIcon from "./ToolbarIcon.vue";
import HeadIcon from "../HeadIcon.vue";
import { SwitchButton } from "@element-plus/icons-vue";

export default {
  name: "StripToolbar",
  data(){
    return{
      color:"#ffffff"
    }
  },
  components: {
    ToolbarIcon,
    HeadIcon,
    SwitchButton
  },
  computed: {
    userInfor() {
      return this.$store.state.role == "student"
        ? this.$store.state.signInStudent.info
        : "teacher have no info";
    },
    username() {
      return this.$store.state.role == "student"
        ? this.$store.state.signInStudent.name
        : this.$store.state.signInTeacher.name;
    },
    linkBase() {
      return "/" + this.$store.state.role + "/" + this.username;
    },
    moduleLink(){
      return this.$store.state.role=='student'?"StudentModulePage":"TeacherModulePage"
    }
  },
};
</script>

<style>
.strip-toolbar {
  width: 4rem;
  background-color: var(--main-left-stripe-toolbar-bgcolor);
}
.btn-top {
  display: flex;
  flex-direction: column;
  align-content: center;
  height: 70%;
}
.btn-bottom {
  display: flex;
  flex-direction: column;
  height: 30%;
  justify-content: flex-end;
  align-items: center;
  margin-bottom: 10px;
}
</style>