<template>
  <li class="item-class">
    <div>
      <svg
        t="1644353206526"
        class="icon"
        viewBox="0 0 1024 1024"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        p-id="2776"
        width="20"
        height="20"
      >
        <path
          d="M400.695652 578.782609a44.521739 44.521739 0 0 1 44.521739 44.521739v356.173913a44.521739 44.521739 0 0 1-44.521739 44.521739H44.521739a44.521739 44.521739 0 0 1-44.521739-44.521739v-356.173913a44.521739 44.521739 0 0 1 44.521739-44.521739h356.173913z m578.782609 0a44.521739 44.521739 0 0 1 44.521739 44.521739v356.173913a44.521739 44.521739 0 0 1-44.521739 44.521739h-356.173913a44.521739 44.521739 0 0 1-44.521739-44.521739v-356.173913a44.521739 44.521739 0 0 1 44.521739-44.521739h356.173913zM378.434783 645.565217h-311.652174v311.652174h311.652174v-311.652174z m578.782608 0h-311.652174v311.652174h311.652174v-311.652174zM400.695652 0a44.521739 44.521739 0 0 1 44.521739 44.521739v356.173913a44.521739 44.521739 0 0 1-44.521739 44.521739H44.521739a44.521739 44.521739 0 0 1-44.521739-44.521739V44.521739a44.521739 44.521739 0 0 1 44.521739-44.521739h356.173913z m-22.260869 66.782609h-311.652174v311.652174h311.652174v-311.652174z"
          fill="#000000"
          p-id="2777"
        ></path>
        <path
          d="M801.391304 222.608696m-222.608695 0a222.608696 222.608696 0 1 0 445.217391 0 222.608696 222.608696 0 1 0-445.217391 0Z"
          fill="#36E0FF"
          p-id="2778"
        ></path>
      </svg>
    </div>

    <router-link :to="routerLink" style="color:var(--right-link-color)">{{ moduleName }}</router-link>
  </li>
</template>

<script>
export default {
  name: "ModuleItem",
  props: {
    moduleName: {
      type: String,
      required: true,
    },
    moduleId:{
      type:Number
    }
  },
  computed:{
    routerLink(){
      var linkObj = new Object();
      linkObj.name = this.$store.state.role=='student'?"StudentModulePage":"TeacherModulePage"
      linkObj.params = {
        moduleId:this.moduleId
      }
      return linkObj
    }
  }
};
</script>

<style scoped>
.item-class {
  display: flex;
  /* 如果不写flex-start会有一个警告 */
  justify-content: flex-start;
  align-items: center;
  height: 100%;
}
p {
  margin: 4px;
}
</style>
