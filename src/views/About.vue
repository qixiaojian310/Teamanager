<template>
  <el-scrollbar
    :height="this.$store.state.windowSize.windowSizeHeight"
    @scroll="scrollProxy"
  >
    <div style="z-index: 1; position: sticky; top: 0px">
      <main-header> </main-header>
    </div>

    <el-row>
      <el-col :span="12">
        <div
          data-aos="fade-right"
          data-aos-offset="200"
          data-aos-delay="50"
          data-aos-duration="1000"
          data-aos-easing="ease-in-out"
          style="height: 700px; width: 100% "
        
        >
          <el-carousel indicator-position="none" height=700px> 
            <el-carousel-item v-for="item in 4" :key="item">
              <h3>{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
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
            box-shadow: 0px 0px 20px rgb(0, 0, 0);
          "
          
        >
          <div
            data-aos="fade-up"
            data-aos-offset="200"
            data-aos-delay="1000"
            data-aos-duration="1000"
            data-aos-easing="ease-in-out"
            style="
              height: 400px;
              font-size: 50px;
              color: white;
              line-height: 500px;
              margin: 0;
              text-align: center;
              
            "
          >
            This is an app for team.
            
          </div>
          <el-button color="#626aef" style="color: white" size="large" >Start now</el-button>
        </div>
      </el-col>
    </el-row>

    <div
      data-aos="fade-up"
      data-aos-offset="200"
      data-aos-delay="50"
      data-aos-duration="1000"
      data-aos-easing="ease-in-out"
      style="height: 700px; width: 100%; background-color: #4d58b5;box-shadow: 0px 0px 20px rgb(0, 0, 0);"
    >
      <div
        data-aos="fade-up"
        data-aos-offset="200"
        data-aos-delay="1000"
        data-aos-duration="1000"
        data-aos-easing="ease-in-out"
        style="
          font-size: 50px;
          color: white;
          line-height: 500px;
          margin: 0;
          text-align: center;
        "
      >
        absfndjsakewasuenvjdi
      </div>
    </div>
    <div
      data-aos="fade-up"
      data-aos-offset="200"
      data-aos-delay="50"
      data-aos-duration="1000"
      data-aos-easing="ease-in-out"
      style="height: 700px; width: 100%; background-color: #99a9bf;box-shadow: 0px 0px 20px rgb(0, 0, 0);"
    >
      <p
        data-aos="fade-up"
        data-aos-offset="200"
        data-aos-delay="1000"
        data-aos-duration="1000"
        data-aos-easing="ease-in-out"
        style="
          height: 100px;
          font-size: 50px;
          color: white;
          line-height: 500px;
          margin: 0;
          text-align: center;
          
        "
      >
        absfndjsakewasuenvjdi
      </p>
      <p
        data-aos="fade-up"
        data-aos-offset="200"
        data-aos-delay="1500"
        data-aos-duration="1000"
        data-aos-easing="ease-in-out"
        style="
          height: 100px;
          font-size: 30px;
          color: white;
          line-height: 500px;
          margin: 0;
          text-align: center;
          
        "
      >
        123nfdjuwejf
      </p>
    </div>
  </el-scrollbar>
</template>
<script>
import MainHeader from "../components/Home/header/MainHeader.vue";
import AOS from "aos";

export default {
  components: {
    MainHeader,
  },
  methods: {
    scrollProxy: function (scrollParams) {
      window.pageYOffset = scrollParams.scrollTop;
      window.scrollY = scrollParams.scrollTop;
      AOS.refresh();
    },
  },
};
</script>
<style scoped>
.about {
  position: relative;
  height: 2000px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 50px;
  opacity: 0.75;
  line-height: 500px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
