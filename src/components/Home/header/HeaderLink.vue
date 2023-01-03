<template>
  <!-- <el-select id="color-select" v-model="color" class="m-2" placeholder="Select" size="large" @change="changeColor">
    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
      <div :style="{ background: item.color }" style="width: 100%; height: 100%"></div>
    </el-option>
  </el-select> -->
  <el-button @click.stop="changeTheme">
    <i class="fa fa-2x fa-sun-o"></i>
  </el-button>
  <el-button @click.stop="this.$router.push(linkBase+'/chat')">
    <i class="fa fa-wechat"></i>
    <span> Chat</span>
  </el-button>
  <el-button @click.stop="this.$router.push(linkBase)">
    <i class="fa fa-home"></i>
    <span> User</span>
  </el-button>
</template>

<script>
import { useDark, usePreferredDark, useToggle } from "@vueuse/core";
import { addColor } from "../../../theme/theme";

export default {
  name: "HeaderLink",
  data() {
    return {
      dark: useDark({
        valueDark:'dark',
        valueLight:'white'
      }),
      color: "green",
      options: [
        {
          label: "green",
          color: "#16a951",
          value: "green",
        },
        {
          label: "red",
          color: "#f20c00",
          value: "red",
        },
        {
          label: "yellow",
          color: "#ffa400",
          value: "yellow",
        },
        {
          label: "blue",
          color: "#44cef6",
          value: "blue",
        },
      ],
    };
  },
  computed: {
    linkBase() {
      var username =
        this.$store.state.role == "student"
          ? this.$store.state.signInStudent.name
          : this.$store.state.signInTeacher.name;
      return "/" + this.$store.state.role + "/" + username;
    },
  },
  methods: {
    changeTheme() {
      // useToggle(this.dark);
      this.dark = !this.dark;
      addColor(this.dark, this.color);
      this.$store.commit("updateThemeColor", this.color);
      this.$store.commit("updateIsDark", this.dark);

    },
    changeColor() {
      addColor(this.dark, this.color);
      this.$store.commit("updateThemeColor", this.color);
      this.$store.commit("updateIsDark", this.dark);
    },
  },
};
</script>

<style scoped>
a {
  color: var(--header-link-color);
  background: var(--header-link-bgcolor);
  text-decoration: none;
  text-align: center;
  text-decoration: none;
  line-height: 60px;
  width: 25%;
  transition: background 0.5s cubic-bezier(0.4, 0, 0.2, 1);
  border-radius: 5px;
}

a:hover {
  transition: background 0.5s cubic-bezier(0.4, 0, 0.2, 1);
  background: var(--header-link-hover-bgcolor);
  color: var(--header-link-hover-color);
}

.el-button {
  height: 100%;
  width: 12.5%;
  border: none;
  border-radius: 5px;
  background-color:var(--header-link-bgcolor);
}
</style>
<style lang="scss" scoped>
#color-select {
  ::v-deep {
    .el-input__inner {
      height: 30px;
      background: rgba(29, 214, 226, 0.1) !important;
      border: 1px solid #01deef !important;
    }

    .el-input__prefix,
    .el-input__suffix {
      height: 30px;
    }

    /* 下面设置右侧按钮居中 */
    .el-input__suffix {
      top: 5px;
    }

    .el-input__icon {
      line-height: inherit;
    }

    .el-input__suffix-inner {
      display: inline-block;
    }
  }
}
</style>
