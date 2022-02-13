<template>
  <!-- 宽度小于500时不显示背景图 -->
  <div
    class="bg-box"
    :style="{ width: bigBoxWidth + 'px', height: bigBoxHeight + 'px' }"
  >
    <!-- :style="{width:bigBoxWidth*0.4+'px',height:bigBoxHeight*0.5+'px',marginLeft:-bigBoxWidth*0.2+'px',marginTop:-bigBoxHeight*0.25+'px'}" -->
    <div class="sign-box">
      <div class="sign-in-content">
        <div class="sign-in-title">
          <p>Teamanager</p>
        </div>
        <el-form
          :model="form"
          :rules="rules"
          ref="form"
          label-width="0px"
          class="login-form"
        >
          <!-- <el-form-item label="Username">
            <el-input
              v-model="form.username"
              placeholder="placeholder.username"
              prefix-icon="el-icon-user"
            ></el-input>
          </el-form-item>
          <el-form-item label="password">
            <el-input
              v-model="form.password"
              type="password"
              placeholder="placeholder.password"
              prefix-icon="el-icon-lock"
            ></el-input>
          </el-form-item> -->
          <sign-input-item :inputValue="form.username" :name="'Username'" :type="'text'">
          </sign-input-item>
          <sign-input-item :inputValue="form.password" :name="'Password'" :type="'password'">
          </sign-input-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">Create</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="sign-toggle-box-link">
        <p>Doesn't have an account of Teamanager</p>
        <router-link to="/signup">Sign up</router-link>
        <span>© Teamanager</span>
      </div>
    </div>
  </div>
</template>

<script>
import "";
import SignInputItem from '../components/sign/SignInputItem.vue';

export default {
  components: { SignInputItem },
  name: "Signin",
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
        username: "Enter your username",
        password: "Enter your password",
      },
    };
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
</style>