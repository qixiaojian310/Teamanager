<template>
  <el-tooltip placement="right" effect="dark">
    <template #default>
      <div class="exCircle">
      <div class="inCircle">
        <p class="storyPoint">{{ storyPoint }}</p>
      </div>
    </div>
    </template>
    <template #content>
      <span style="font-size: 15px;color: #000;">Story point: the number of subtask</span>
    </template>
  </el-tooltip>
</template>

<script>
export default {
  name: "StoryPointBox",
  props: {
    storyPoint: Number,
  },
};
</script>

<style scoped>
.el-popper.is-customized {
  /* Set padding to ensure the height is 32px */
  padding: 6px 12px;
  background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #b2e68d, #bce689);
  right: 0;
}

.exCircle {
  margin: auto;
  width: 150px;
  height: 150px;
  border-radius: 50%;
  background-color: #409eff;
}

.inCircle {
  margin-top: 15px;
  margin-left: 15px;
  position: absolute;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}
.storyPoint {
  font-size: 50px;
  color: #409eff;
  font-weight: 900;
}
</style>
