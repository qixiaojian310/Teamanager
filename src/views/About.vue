<template>
  <el-scrollbar :height="700" @scroll="scrollProxy">
    <div class="about">
      <!-- <main-header></main-header> -->
      <div style="height: 600px; width: 600px; background: rgba(0,0,0,0.1)"></div>
      <p
        data-aos="fade-down"
        data-aos-offset="200"
        data-aos-delay="50"
        data-aos-duration="1000"
        data-aos-easing="ease-in-out"
      >
        dassadsadsadsadsad
      </p>
    </div>
  </el-scrollbar>
</template>
<script>
import MainHeader from "../components/Home/header/MainHeader.vue";

export default {
  components: {
    MainHeader,
  },
  methods: {
    scrollProxy(scrollParams) {
      window.pageYOffset = scrollParams.scrollTop;
      // this.$emit("scroll", scrollParams);
      console.log(window.pageYOffset);
    },
  },
};
</script>
<style scoped>
.about {
  position: relative;
  height: 2000px;
}
</style>
