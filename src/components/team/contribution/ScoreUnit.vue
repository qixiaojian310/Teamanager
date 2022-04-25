<template>
  <!-- 打分 -->
  <div class="score-box">
    <score-item
      v-for="scoreObj in students"
      :key="scoreObj.student.studentId"
      :student="scoreObj.student"
      :team-id="teamId"
      :score="scoreObj.score"
    ></score-item>
  </div>
</template>

<script>
import ScoreItem from "./ScoreItem.vue";
export default {
  components: { ScoreItem },
  props: {
    students: {
      type: Array,
      default: () => [],
    },
    teamId: {
      type: Number,
      default: "",
    },
  },
};
</script>

<style scoped>
.score-box{
  margin: 20px;
}
</style>
