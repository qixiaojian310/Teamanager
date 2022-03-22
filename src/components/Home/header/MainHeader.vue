<template>
  <el-header>
    <el-row>
      <!-- icon -->
      <el-col :xs="6" :sm="6" :md="6" :lg="3">
        <el-row align="middle" justify="start">
          <div
            :style="{
              height: '30px',
              width: '100px',
              background:
                'url(' +
                require('@/assets/icon.png') +
                ') no-repeat center center',
              backgroundSize: '100% auto',
              marginLeft: '20px',
            }"
          ></div>
        </el-row>
        <!-- <i class="fa fa-2x fa-snowflake-o fa-spin"></i> -->
      </el-col>
      <!-- 选项卡 -->
      <el-col
        :xs="{ span: 9, offset: 9 }"
        :sm="{ span: 9, offset: 9 }"
        :md="{ span: 9, offset: 9 }"
        :lg="{ span: 9, offset: 12 }"
      >
        <el-row class="header-link-box" :justify="drawerBtnJustify" align="middle">
          <header-link v-if="windowSizeBoolean"></header-link>
          <el-button @click="drawer = true" style="margin-right:20px" v-else>
            <i class="fa fa-bars"></i>
          </el-button>
        </el-row>
      </el-col>
    </el-row>
    <el-drawer v-model="drawer"> </el-drawer>
  </el-header>
</template>

<script>
import HeaderLink from "./HeaderLink.vue";

export default {
  name: "MainHeader",
  components: {
    HeaderLink,
  },
  data() {
    return {
      drawer: false,
    };
  },
  computed: {
    //判断设备大小是否小于700
    drawerBtnJustify() {
      if (this.$store.state.windowSize.windowSizeWidth > 768) {
        return "space-around";
      } else {
        return "end";
      }
    },
    windowSizeBoolean() {
      if (this.$store.state.windowSize.windowSizeWidth > 768) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.header-link-box{
  justify-content: end;
}
.el-row {
  height: 100%;
  width: 100%;
  background: #DF7599;
  /* background: rgb(91, 113, 167); */
  /* background: rgb(167, 93, 93); */
}
.el-header {
  padding: 0px;
  /* align-content: center;
  justify-content: center; */
}
</style>