<template>
  <!-- 宽度小于500时不显示背景图 -->
  <div
    class="bg-box"
    :style="{ width: bigBoxWidth + 'px', height: bigBoxHeight + 'px' }"
  >
    <!-- :style="{width:bigBoxWidth*0.4+'px',height:bigBoxHeight*0.5+'px',marginLeft:-bigBoxWidth*0.2+'px',marginTop:-bigBoxHeight*0.25+'px'}" -->
    <div class="sign-box">
      <div class="sign-in-out-box-title">
        <p>Teamanager</p>
      </div>
      <div class="sign-in-box-content">
        <el-form
          :model="form"
          :rules="rules"
          ref="form"
          label-width="0px"
          class="login-form"
        >
          <el-form-item>
            <el-input
              v-model="form.username"
              :placeholder="placeholder.username"
              prefix-icon="el-icon-user"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-input
              v-model="form.password"
              type="password"
              :placeholder="placeholder.password"
              prefix-icon="el-icon-lock"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">Create</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="sign-toggle-box-link">
        <router-link to="/signin">Sign in</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Signup",
  computed: {
    bigBoxWidth: function () {
      return this.$store.state.windowSize.windowSizeWidth;
    },
    bigBoxHeight: function () {
      return this.$store.state.windowSize.windowSizeHeight;
    },
  },
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      url: "/hello",
      placeholder: {
        username: "Enter your user name",
        password: "Enter your password",
      },
    }
  },
  methods: {
    submitForm() {
      this.axios({
        url: this.url,
        data: {
          user: {
            username: this.form.username,
            password: this.form.password,
          },
        },
        method: "post",
        baseURL: "http://localhost:8088/api",
      }).then((response) => {
        console.log(response);
      });
    },
  },
};
</script>

<style scoped src="../assets/css/sign-css.css">
.bg-box {
  background-color: #4d58b5;
}
.sign-in-out-box {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 500px;
  margin-left: -250px;
  min-width: 500px;
  height: 700px;
  margin-top: -350px;
  min-height: 300px;
  z-index: 1;
  background-color: rgba(0, 0, 0, 0);
}
</style>