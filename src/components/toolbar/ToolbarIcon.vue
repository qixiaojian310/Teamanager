<template>
  <div class="icon-box">
    <el-popover placement="right">
      <template #reference>
        <router-link :to="address">
          <slot name="icon"></slot>
        </router-link>
      </template>
      <template #default>
        <slot name="content"></slot>
      </template>
    </el-popover>

    
  </div>
</template>

<script>
export default {
  name: "ToolbarIcon",
  props:{
    address:{
      type:String,
      required:true
    }
  },
  mounted(){
  }
}
</script>

<style scoped>
.icon-box{
  width: 2rem;
  height: 2rem;
  padding: 1rem;
}
a{
  text-decoration: none;
  width: 100%;
  height: 100%;
  display: block;
}
</style>