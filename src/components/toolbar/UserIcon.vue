<template>
  <div class="icon-box">
    <router-link :to="address" color="#626aef" type="primary">
      <slot></slot>
    </router-link>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.icon-box{
  width: 2rem;
  height: 2rem;
  padding: 1rem;
}
</style>