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
// import contentpic3 from "@/assets/picture/content3.jpg";

export default {
  name: "switchContent3",
  data() {
    return {
      contentPic: require("@/assets/picture/content3.png"),
      contentText: "Enjoy more direct communication in Teamanger"
    };
  }
};
</script>