<template>
  <div :id="id">
    <div class="homepage-title" style="z-index: 3; height: 90%">
      <div
        class="homepage-content-nopicture align-items-center"
        :style="{
          background: this.backgroundimg,
          'background-size': '100% 100%',
        }"
      >
        <div
          :style="{
            backgroundColor: this.backgroundcolor,
            width: '100%',
            height: '100%',
          }"
        >
          <div class="container">
            <div class="row">
              <div class="col-md-3 col-10 offset-1 content-box px-5 px-md-2">
                <p class="title-box" data-aos="fade-right">{{ this.title1 }}</p>
                <p class="description-box" data-aos="fade-right">
                  {{ this.description1 }}
                </p>
              </div>
              <div class="col-md-8 col-10 offset-md-0 offset-1 content-box">
                <div class="tag-box">
                  <img :src="pic1" data-aos="fade-left" alt="homepage" style="width:80%" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="homepage-title" style="z-index: 2; height: 90%">
      <div class="homepage-content-picture align-items-center">
        <div class="container">
          <div class="row">
            <div class="col-md-8 col-10 offset-md-0 offset-1 content-box">
              <div class="tag-box">
                <img :src="pic2" data-aos="fade-right" alt="homepage" style="width:80%" />
              </div>
            </div>
            <div class="col-md-3 col-10 offset-1 content-box px-5 px-md-2">
              <p class="title-box" data-aos="fade-left" style="color:black">{{ this.title2 }}</p>
              <p class="description-box" data-aos="fade-left" style="color:black">
                {{ description2 }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import 'bootstrap/dist/css/bootstrap.css';
export default {
  name: "HomeIntroBlock",
  props: [
    "id",
    "pic1",
    "pic2",
    "description1",
    "description2",
    "title1",
    "title2",
    "backgroundimg",
    "backgroundcolor",
  ],
};
</script>

<style scoped>
@media (max-width: 768px) {
  .tag-box {
    width: 60%;
  }
}
@media (min-width: 768px) {
  .tag-box {
    width: 80%;
  }
}
.tag-box {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 3% 0%;
}
.content-box {
  display: flex;
  position: relative;
  justify-content: center;
  align-items: center;
  color: white;
  flex-direction: column;
}
.container {
  height: 100%;
}
.row {
  height: 100%;
}
/* .description-box {

} */
.title-box {
  font-size: 45px;
  font-weight: 800;
}
</style>
<style scoped src="bootstrap/dist/css/bootstrap.css">

</style>