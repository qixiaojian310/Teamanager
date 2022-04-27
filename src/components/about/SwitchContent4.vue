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
// import contentpic4 from "@/assets/picture/content4.jpg";

export default {
  name: "switchContent4",
  data() {
    return {
      contentPic: require("@/assets/picture/content4.png"),
      contentText: "Turn your team into a more effective alliance"
    };
  }
};
</script>