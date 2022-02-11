<template>
  <ul>
    <module-item
      v-for="moduleItem in moduleItems"
      :key="moduleItem.index"
      :module-name="moduleItem.item"
    ></module-item>
  </ul>
</template>

<script>
import ModuleItem from "./ModuleItem.vue";
export default {
  name: "AsideModules",
  props:{
    moduleItems: {
      type: Array,
      required: true,
    },
  },
  components: {
    ModuleItem,
  },
};
</script>

<style></style>
