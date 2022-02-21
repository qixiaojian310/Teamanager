<template>
  <swiper
    :modules="modules"
    :slides-per-view="1"
    :space-between="50"
    :pagination="{ clickable: true }"
    :scrollbar="{ draggable: true }"
    @swiper="onSwiper"
    @slideChange="onSlideChange"
  >
    <swiper-slide>Slide 1</swiper-slide>
    <swiper-slide>Slide 2</swiper-slide>
    <swiper-slide>Slide 3</swiper-slide>
  </swiper>
</template>
<script>
  // import Swiper core and required modules
  import { Navigation, Pagination, Scrollbar, A11y } from 'swiper';

  // Import Swiper Vue.js components
  import { Swiper, SwiperSlide } from 'swiper/vue/swiper-vue.js';

  // Import Swiper styles
  import 'swiper/swiper.min.css';
  import 'swiper/modules/navigation/navigation.min.css';
  import 'swiper/modules/pagination/pagination.min.css';
  import 'swiper/modules/scrollbar/scrollbar.min.css';

  // Import Swiper styles
  export default {
    components: {
      Swiper,
      SwiperSlide,
    },
    data(){
      return{
        modules:[Pagination,Scrollbar,A11y]
      }
    },
    methods:{
      onSwiper(swiper){
        console.log(swiper);
      },
      onSlideChange(swiper){
        console.log(swiper);
      }
    },
    name: 'SwiperComponent',
  };
</script>
