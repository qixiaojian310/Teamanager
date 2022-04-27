<template>
  <div class="switch-content">
    <div
      class="switch-content-pic"
      :style="{ backgroundImage: 'url(' + contentPic + ')' }"
    ></div>
    <div class="switch-content-text">
      <p>{{this.contentText}}</p>
    </div>
  </div>
</template>

<script>
// import contentpic2 from "@/assets/picture/content2.jpg";

export default {
  name: "switchContent2",
  data() {
    return {
      contentPic: require("@/assets/picture/content2.png"),
      contentText: "Use Teamanager to control the task in the team"
    };
  }
};
</script>