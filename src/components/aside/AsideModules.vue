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
import ModuleItem from "@/components/aside/ModuleItem";
export default {
  name: "AsideModules",
  data() {
    return {
      moduleItems: [
        {
          item: "JS Class",
        },
        {
          item: "Java Class",
        },
        {
          item: "Matlab Class",
        },
        {
          item: "Python Class",
        },
      ],
    };
  },
  components: {
    ModuleItem,
  },
};
</script>

<style></style>
