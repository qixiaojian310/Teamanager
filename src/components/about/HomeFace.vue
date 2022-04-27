<template>
  <div>
    <div class="homepage-background" id="background1" :style="{'zIndex':'2', 'background': 'url('+src1+') no-repeat', 'backgroundSize': '100% 120%', opacity: opacity1}"></div>
    <div class="homepage-background" id="background2" :style="{'zIndex':'1', 'background': 'url('+src2+') no-repeat', 'backgroundSize': '100% 120%', opacity: opacity2}"></div>
    <div class="homepage-title d-block d-sm-flex" style="z-index: 3; position: relative;" id="face">
      <div class="homepage-title-content1 d-flex" id="htc-1">
        <div class="container d-flex">
          <div class="row align-content-center">
            <div class="col-12">
              <div
                class="col-8 offset-2 d-flex flex-column justify-content-center"
              >
                <h1 class="homepage-h1 pb-5">
                  Teamanger
                </h1>
                <h5 class="homepage-h1 pb-5">
                  A transformative teamwork support platform
                </h5>
                <router-link
                  class="mb-5 col-8 text-center btn-lg btn-homepage1 btn-block pb-3 pt-3"
                  to="/signin"
                >
                  Start trail
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class=" homepage-title-content2" id="htc-2">
        <div class="container p-0" style="height:100%">
            <div
              id="homepage-roll-text"
              class="col-12 text-center switch-content-box"
              style="font-size: 120%; color: black; font-weight: 700; display: none;"
            >
              <switch-content1 v-if="showcontent === 'content1'"></switch-content1>
              <switch-content2 v-else-if="showcontent === 'content2'"></switch-content2>
              <switch-content3 v-else-if="showcontent === 'content3'"></switch-content3>
              <switch-content4 v-else-if="showcontent === 'content4'"></switch-content4>             
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import switchContent1 from "./SwitchContent1"
import switchContent2 from "./SwitchContent2"
import switchContent3 from "./SwitchContent3"
import switchContent4 from "./SwitchContent4"
// import 'bootstrap/dist/css/bootstrap.css';


export default {
  name: "HomeFace",
  components:{
    switchContent1,
    switchContent2,
    switchContent3,
    switchContent4,
  },
  computed: {
    src1: function(){
      return this.$parent.src1
    },
    src2: function(){
      return this.$parent.src2
    },
    opacity1: function(){
      return this.$parent.opacity1
    },
    opacity2: function(){
      return this.$parent.opacity2
    }
  },
  methods: {
    test: function(){
      this.$emit('tousercenter')
    }
  },
  props:['showcontent']
  // mounted: function () {
  //   console.log(this);
  // }
}
</script>


<style>
.switch-content{
  width: 100%;
  height: 100%;
}
.switch-content-box{
  width: 100%;
  height: 100%;
  padding: 0% 0%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.switch-content-text{
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: 600;
  height: 30%;
}
.switch-content-pic {
  width: 100%;
  height: 70%;
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
</style>
<style scoped src="bootstrap/dist/css/bootstrap.css">

</style>