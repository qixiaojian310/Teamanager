<template>
  <ul>
    <team-item
      v-for="teamItem in teamItems"
      :key="teamItem.index"
      :team-name="teamItem.item"
    ></team-item>
  </ul>
</template>

<script>
import TeamItem from "./TeamItem.vue";

export default {
  name: "AsideTeams",
  props: {
    teamItems: {
      type: Array,
      required: true,
    },
  },
  components: {
    TeamItem,
  },
};
</script>

<style>
</style>