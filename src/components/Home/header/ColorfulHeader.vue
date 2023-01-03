<template>
  <el-header>
    <Transition name="slide-down" mode="out-in">
      <main-header v-if="color == 'green'" :color="color" @change-color="changeColor()"></main-header>
      <main-header v-else-if="color == 'blue'" :color="color" @change-color="changeColor()"></main-header>
      <main-header v-else-if="color == 'red'" :color="color" @change-color="changeColor()"></main-header>
      <main-header v-else-if="color == 'yellow'" :color="color" @change-color="changeColor()"></main-header>
    </Transition>
  </el-header>
</template>

<script>
import MainHeader from "./MainHeader.vue";
import { addColor } from "@/theme/theme.js";
import { useDark } from "@vueuse/core";

export default {
  name: "ColorfulHeader",
  components: {
    MainHeader,
  },
  data() {
    return {
      color: "green",
      dark: useDark({
        valueDark: 'dark',
        valueLight: 'white'
      }),
    };
  },
  mounted() {
    console.log("触发了mounted");
    addColor(this.dark, this.color);
    this.$store.commit("updateThemeColor", this.color);
    this.$store.commit("updateIsDark", this.dark);
  },
  methods: {
    changeColor() {
      this.color = this.color == "green" ? "blue" : this.color == "blue" ? "red" : this.color == "red" ? "yellow" : "green";
      addColor(this.$store.state.isDark, this.color);
      this.$store.commit("updateThemeColor", this.color);
    },
  },
};
</script>

<style scoped>
.el-header {
  padding: 0px;
  /* align-content: center;
  justify-content: center; */
}

.slide-down-enter-active,
.slide-down-leave-active {
  transition: all 0.1s ease-out;
}

.slide-down-enter-from {
  opacity: 0;
  transform: translateY(-30px);
}

.slide-down-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
</style>