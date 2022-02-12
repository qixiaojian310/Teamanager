<template>
  <el-scrollbar :height="this.$store.state.windowSize.windowSizeHeight" @scroll="scrollProxy">
    <main-header></main-header>
    <el-row>
      <el-col :span="12">
        <div
          data-aos="fade-right"
          data-aos-offset="200"
          data-aos-delay="50"
          data-aos-duration="1000"
          data-aos-easing="ease-in-out"
          style="
            height: 700px;
            width: 100%;
            background-color: rgb(0, 0, 0, 0.5);
            text-align: center;
          "
        >
          abcdjsajejwuiajw3qnferw4juiadsfav breaiwehwabfehlfehwahue4w7nsdh
        </div>
      </el-col>
      <el-col :span="12">
        <div
          data-aos="fade-left"
          data-aos-offset="200"
          data-aos-delay="50"
          data-aos-duration="1000"
          data-aos-easing="ease-in-out"
          style="
            height: 700px;
            width: 100%;
            background-color: rgb(0, 0, 0, 0.8);
            text-align: center;
          "
        >
          abcdjsajejwuiajw3qnferw4juiadsfav breaiwehwabfehlfehwahue4w7nsdh
        </div>
      </el-col>
    </el-row>

    <div
      data-aos="fade-right"
      data-aos-offset="200"
      data-aos-delay="50"
      data-aos-duration="1000"
      data-aos-easing="ease-in-out"
      style="height: 700px; width: 100%; background-color: #4d58b5"
    ></div>
    <div
      data-aos="fade-left"
      data-aos-offset="200"
      data-aos-delay="50"
      data-aos-duration="1000"
      data-aos-easing="ease-in-out"
      style="height: 700px; width: 100%; background-color: #000"
    ></div>
  </el-scrollbar>
</template>
<script>
import MainHeader from "../components/Home/header/MainHeader.vue";
import AOS from "aos"

export default {
  components: {
    MainHeader,
  },
  methods:{
    scrollProxy: function(scrollParams){
      window.pageYOffset = scrollParams.scrollTop;
      window.scrollY = scrollParams.scrollTop;
      AOS.refresh();
    }
  }

};
</script>
<style scoped>
.about {
  position: relative;
  height: 2000px;
}
</style>
