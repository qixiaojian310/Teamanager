<template>
  <div>
    <home-face ref="HomeFace" :showcontent="this.showcontent"></home-face>
    <home-intro></home-intro>
  </div>
</template>

<script>
import "@/assets/css/homepage.css";
import HomeFace from "@/components/about/HomeFace";
import HomeIntro from '@/components/about/HomeIntro'
import backPic1 from "@/assets/picture/background1.jpg";
import backPic2 from "@/assets/picture/efficient.png";
import backPic3 from "@/assets/picture/dataBack.png";
import backPic4 from "@/assets/picture/information.png";
// import 'bootstrap/dist/css/bootstrap.css';

export default {
  name: "HomePage",
  data() {
    return {
      opacity1: 1.0,
      opacity2: 0.0,
      src1: backPic1,
      src2: backPic4,
      rollTexts: [
        "content1",
        "content2",
        "content3",
        "content4"
      ],
      showcontent: "",
      idOfSetinterval: ""
    };
  },
  mounted: function() {
    var width;
    var height = $(window).height();
    let faceTop = 0;

    window.addEventListener("scroll", this.scrollSwitch, true);
    console.log($('#htc-1'));
    $("#htc-1").css("height", height * 0.9 + "px");
    $("#htc-2").css("height", height * 0.9 + "px");

    function autochange_base() {
      //var height = $(window).height();
      width = $(window).width();
      var half_width = width / 2 + "px";
      // var homepage_height = width*0.4+"px"
      $("#htc-1").css("height", height * 0.9 + "px");
      $("#htc-2").css("height", height * 0.9 + "px");
      //调整每个块（content）的高让其刚好覆盖页面
      $(".homepage-background").css("height", height * 1.2 + "px");
      $(".homepage-background").css("width", width * 1.2 + "px");
      $(".homepage-content-nopicture").css("height", height * 0.9 + "px");
      $(".homepage-content-picture").css("height", height * 0.9 + "px");
      if (width > 576) {
        $("#htc-1").css("width", half_width);
        $("#htc-2").css("width", half_width);
      } else {
        $("#htc-1").css("width", width + "px");
        $("#htc-2").css("width", width + "px");
      }
      //$("#homepage-image1").css("height",height*0.6+"px")
    }
    autochange_base();
    //轮播
    $(window).resize(autochange_base);
    var count = 0;
    var hompageVm = this;
    function show1(i) {
      hompageVm.showcontent = hompageVm.rollTexts[i]
      $("#homepage-roll-text").fadeIn(300);
      setTimeout(function() {
        $("#homepage-roll-text").fadeOut(300);
      }, 4000);
      return i + 1;
    }
    function autochange() {
      count = show1(count);
      if (count > hompageVm.rollTexts.length - 1) {
        count = 0;
      }
      return autochange;
    }
    // js中函数参数如果是”函数名+()“，则会先执行函数，然后将返回值作为真正的参数
    // 。相反参数没加“（）”则会被作为一个函数块指针，不先执行。
    hompageVm.idOfSetinterval =  setInterval(autochange(), 4600);
    faceTop = document.getElementById("face").getBoundingClientRect().top;
    $(".homepage-background").css(
      "bottom",
      (-height * 0.9 - faceTop) * 0.15 + "px"
    );
  },
  beforeDestroy: function() {
    window.removeEventListener("scroll", this.scrollSwitch, true);
    clearInterval(this.idOfSetinterval)
  },
  methods: {
    scrollSwitch: function() {
      var height = $(window).height();
      let faceTop = 0;
      faceTop = document.getElementById("face").getBoundingClientRect().top;
      let faceBottom = document.getElementById("face").getBoundingClientRect()
        .bottom;
      let faceBottomCalc = faceBottom+height*0.9;
      if (faceTop < height * 0.1 && faceBottom > 0) {
        $(".homepage-background").css(
          "bottom",
          (-height * 0.9 - faceTop) * 0.15 + "px"
        );
        $(".head-bar").css("background", "rgba(39,39,39,1)")
        // $(".homepage-background").css("background", "url(\"../picture/background1.jpg\") no-repeat");
        $(".homepage-background").css("background-size", "100% 120%");
        $(".login-head-box").show()
      }
      if(faceBottom < 0){
        $(".head-bar").css("background", "rgba(39,39,39,0.7)")
        $(".login-head-box").hide()
      }
              // bottom从height到-0.9height所以我们对他进行归一化,这里要注意进入条件
      if(faceTop < height * 0.1 && faceBottomCalc > 0){
        this.src1 = backPic1;
        this.src2 = backPic4;
        this.opacity2 = Math.pow(1-Math.pow((faceBottomCalc/1.9/height),1.2),5/6)
        this.opacity1 = 1-Math.pow(1-Math.pow((faceBottomCalc/1.9/height),1.2),5/6)
      }
      //获取intro相对于视窗的高
      let offsetIntro1Bottom = document
        .getElementById("intro1")
        .getBoundingClientRect().bottom;
      let offsetIntro1BottomCalc = offsetIntro1Bottom+height*0.9
      let offsetIntro1Top = document
        .getElementById("intro1")
        .getBoundingClientRect().top;
      if (offsetIntro1Top < height * 0.1 && offsetIntro1Bottom > 0) {
        $(".homepage-background").css(
          "bottom",
          (0 - height * 1.8 - offsetIntro1Top) * 0.15 + "px"
        );
        // $(".homepage-background").css("background", "url(\"../picture/background4.jpg\") no-repeat");
        
        $(".homepage-background").css("background-size", "100% 120%");
      }
      if(offsetIntro1Top+0.9*height < height * 0.1 && offsetIntro1BottomCalc > 0){
        this.src2 = backPic4;
        this.src1 = backPic2;
        this.opacity1 = Math.pow(1-Math.pow((offsetIntro1BottomCalc/1.9/height),1.2),5/6)
        this.opacity2 = 1-Math.pow(1-Math.pow((offsetIntro1BottomCalc/1.9/height),1.2),5/6)
      }
      let offsetIntro2Bottom = document
        .getElementById("intro2")
        .getBoundingClientRect().bottom;
      let offsetIntro2BottomCalc = offsetIntro2Bottom+height*0.9
      let offsetIntro2Top = document
        .getElementById("intro2")
        .getBoundingClientRect().top;
      if (offsetIntro2Top < height * 0.1 && offsetIntro2Bottom > 0) {
        $(".homepage-background").css(
          "bottom",
          (0 - height * 1.8 - offsetIntro2Top) * 0.15 + "px"
        );
        // $(".homepage-background").css("background", "url(\"../picture/background2.jpg\") no-repeat");
        
        $(".homepage-background").css("background-size", "100% 120%");
      }
      if(offsetIntro2Top+0.9*height < height * 0.1 && offsetIntro2BottomCalc > 0){
        this.src1 = backPic2;
        this.src2 = backPic3;
        this.opacity2 = Math.pow(1-Math.pow((offsetIntro2BottomCalc/1.9/height),1.2),5/6)
        this.opacity1 = 1-Math.pow(1-Math.pow((offsetIntro2BottomCalc/1.9/height),1.2),5/6);
      }
      let offsetIntro3Bottom = document
        .getElementById("intro3")
        .getBoundingClientRect().bottom;
      let offsetIntro3BottomCalc = offsetIntro3Bottom+height*0.9
      let offsetIntro3Top = document
        .getElementById("intro3")
        .getBoundingClientRect().top;
      if (offsetIntro3Top < height * 0.1 && offsetIntro3Bottom > 0) {
        $(".homepage-background").css(
          "bottom",
          (0 - height * 1.8 - offsetIntro3Top) * 0.15 + "px"
        );
        // $(".homepage-background").css("background", "url(\"../picture/background3.jpg\") no-repeat");
        console.log("change3");
        $(".homepage-background").css("background-size", "100% 120%");
      }
      if(offsetIntro3Top+0.9*height < height * 0.1 && offsetIntro3BottomCalc > 0){
        this.src1 = backPic3;
        this.opacity1 = Math.pow(1-Math.pow((offsetIntro3BottomCalc/1.9/height),1.2),5/6);
        this.opacity2 = 1-Math.pow(1-Math.pow((offsetIntro3BottomCalc/1.9/height),1.2),5/6);
      }
    }
  },
  components: {
    HomeFace,
    HomeIntro
  }
};
</script>
<style src="../assets/css/main.css">

</style>
<style scoped src="bootstrap/dist/css/bootstrap.css">

</style>