<template>
  <div>
    <template v-for="(IntroBlock, index) in IntroBlocks" :key="index">
      <home-intro-block
        :id="'intro' + (index + 1)"
        :pic1="IntroBlock.pic1"
        :pic2="IntroBlock.pic2"
        :description1="IntroBlock.description1"
        :description2="IntroBlock.description2"
        :backgroundimg="IntroBlock.backgroundimg"
        :backgroundcolor="IntroBlock.backgroundcolor"
        :title1="IntroBlock.title1"
        :title2="IntroBlock.title2"
      ></home-intro-block>
    </template>
  </div>
</template>

<script>
import HomeIntroBlock from '@/components/about/HomeIntroBlock';
import modulePic from "@/assets/picture/module.png";
import elementplus from "@/assets/picture/elementplus.png";
import dataPic from "@/assets/picture/data.png";
import teacher from "@/assets/picture/teacher.png";
import communication from "@/assets/picture/communication.png";
import Interface from "@/assets/picture/interface.png";
import backtag1 from "@/assets/picture/tag1.png";
import backtag2 from "@/assets/picture/idea.jpg";
import backtag3 from "@/assets/picture/unlockjpg.jpg";
// import 'bootstrap/dist/css/bootstrap.css';

export default {
  name: "HomeIntro",
  data() {
    return {
      IntroBlocks: [
        {
          pic1: elementplus,
          pic2: Interface,
          description1:
            "Teamanger's interface based on vue and element-plus, which is a front-end framework for building web applications. it can provide the user with a more interactive and user-friendly experience.",
          description2:
            "Teamanger have clear and concise interface, and it is easy for you to get the most important information of your task.",
          title1: "More Interaction",
          title2: "More Information",
          backgroundcolor: "rgba(0,0,0, 0.35)",
          backgroundimg: "url(" + backtag1 + ")" + " no-repeat",
        },
        {
          pic1: modulePic,
          pic2: teacher,
          description1:
            "Teamanger have the concept of module which is more more relevant to group work in practical situations. Team is the concept based on the module.",
          description2:
            "Teamanger is convenient for both teachers and students, and students and teachers will not waste their valuable time on managing projects",
          title1: "More reasonable",
          title2: "More Efficiency",
          backgroundcolor: "rgba(6, 40, 70, 0.35)",
          backgroundimg: "url(" + backtag2 + ")" + " no-repeat",
        },
        {
          pic1: communication,
          pic2: dataPic,
          description1:
            "Teamanger has a complete workflow so that teachers can communicate directly with students on Teamanager instead of wasting time switching applications.",
          description2:
            "Teamanger allows teachers to visualise the progress of a project without lack of supervision and Teamanger also provides charts to visualise more data.",
          title1: "More Communication",
          title2: "More Data",
          backgroundcolor: "rgba(96, 140, 170, 0.35)",
          backgroundimg: "url(" + backtag3 + ")" + " no-repeat",
        },
      ],
    };
  },
  components: {
    HomeIntroBlock,
  },
};
</script>
<style scoped src="bootstrap/dist/css/bootstrap.css">

</style>