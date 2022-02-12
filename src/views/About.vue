<template>
  <div>
      <el-scrollbar :height="500" @scroll="scrollProxy">
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
  </div>
</template>
<script>
import MainHeader from "../components/Home/header/MainHeader.vue";
import AOS from 'aos';

export default {
  components: {
    MainHeader,
  },
  methods: {
    scrollProxy(scrollParams) {
      window.pageYOffset = scrollParams.scrollTop;
      AOS.refresh();
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
