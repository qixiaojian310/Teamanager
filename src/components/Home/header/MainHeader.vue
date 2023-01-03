<template>
    <el-row @click="changeColor">
      <!-- icon -->
      <el-col :xs="9" :sm="9" :md="9" :lg="6">
        <el-row align="middle" justify="start">
          <button class="header-badge">
            <span class="icon-ico-2">
              <span class="path1"></span><span class="path2"></span><span class="path3"></span>
            </span>
          </button>
          <template v-if="!windowSizeBoolean">
            <button class="header-badge">
              <span class="icon-ico-1"><span class="path1"></span><span class="path2"></span></span>
            </button>
            <button class="header-badge">
              <!-- <svg t="1672625510908" class="icon" viewBox="0 0 1024 1024" version="1.1"
                xmlns="http://www.w3.org/2000/svg" p-id="14920" width="30" height="30">
                <path
                  d="M0 512C0 229.23264 229.23264 0 512 0s512 229.23264 512 512-229.23264 512-512 512S0 794.76736 0 512z"
                  fill="#53BC43" p-id="14921"></path>
                <path
                  d="M707.27 458.34L552.35 310.61c-21.46-20.46-59.25-20.46-80.71 0L316.73 458.34c-8.74 8.34-13.57 19.1-13.57 30.24v194.57c0 25.18 24.14 45.58 53.93 45.58h85.96c16.49 0 29.85-11.3 29.85-25.24v-69.98c0-15.77 12.54-30.18 30.8-33.41 25.09-4.44 47.41 11.85 47.41 32.32v71.07c0 13.94 13.37 25.24 29.85 25.24h85.96c29.78 0 53.93-20.41 53.93-45.58V488.58c-0.01-11.14-4.83-21.9-13.58-30.24z"
                  fill="#FFFFFF" p-id="14922"></path>
              </svg> -->
              <span class="icon-ico-uniE900">
                <span class="path1"></span><span class="path2"></span><span class="path3"></span><span class="path4"></span><span class="path5"></span><span class="path6"></span>
              </span>
            </button>
            <button @click.stop="changeTheme" circle class="header-badge" id="dark-toggle">
              <i class="fa fa-sun-o"></i>
            </button>
          </template>
        </el-row>
        <!-- <i class="fa fa-2x fa-snowflake-o fa-spin"></i> -->
      </el-col>
      <!-- 选项卡 -->
      <el-col :xs="{ span: 13, offset: 2 }" :sm="{ span: 13, offset: 2 }" :md="{ span: 13, offset: 2 }"
        :lg="{ span: 13, offset: 5 }">
        <el-row class="header-link-box" align="middle">
          <header-link v-if="windowSizeBoolean"></header-link>
          <el-button @click="drawer = true" style="margin-right: 20px" v-else>
            <i class="fa fa-bars"></i>
          </el-button>
        </el-row>
      </el-col>
    </el-row>
</template>

<script>
import { RouterLink } from "vue-router";
import HeaderLink from "./HeaderLink.vue";
import badgeTheme from "./headerBadgeColor.json"
import { useDark } from "@vueuse/core";
import { addColor } from "@/theme/theme.js";

export default {
  name: "MainHeader",
  components: {
    HeaderLink,
    RouterLink
  },
  data() {
    return {
      drawer: false,
      dark: useDark({
        valueDark:'dark',
        valueLight:'white'
      }),
    };
  },
  props:{
    color:{
      type:String,
      default:'green'
    }
  },
  mounted(){
    // addColor(this.dark, this.color);
    // this.$store.commit("updateThemeColor", this.color);
    // this.$store.commit("updateIsDark", this.dark);
  }, 
  methods: {
    changeTheme() {
      // useToggle(this.dark);
      this.dark = !this.dark;
      addColor(this.dark, this.color);
      this.$store.commit("updateThemeColor", this.color);
      this.$store.commit("updateIsDark", this.dark);
    },
    changeColor() {
      console.log("changeColor");
      this.$emit("changeColor");
    },
  },
  computed: {
    //判断设备大小是否小于700
    drawerBtnJustify() {
      if (this.$store.state.windowSize.windowSizeWidth > 768) {
        return "space-around";
      } else {
        return "end";
      }
    },
    badgeTheme() {

    },
    windowSizeBoolean() {
      if (this.$store.state.windowSize.windowSizeWidth > 980) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.header-link-box {
  justify-content: flex-end;
}

.el-row {
  height: 100%;
  width: 100%;
  background: var(--header-bg-color);
  /* background: rgb(91, 113, 167); */
  /* background: rgb(167, 93, 93); */
}
.header-badge {
  height: 30px;
  width: 30px;
  background-size: 100% auto;
  margin-left: 10px;
  border-radius: 50%;
  border: 0px;
  padding: 0px;
}

.header-badge > span >span{
  font-size: 30px;
  vertical-align: middle;
}

#dark-toggle {
  background-color: var(--badge-background-color);
}
#dark-toggle:hover {
  background-color: var(--badge-hover-background-color);
}
</style>