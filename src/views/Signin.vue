<template>
  <!-- 宽度小于500时不显示背景图 -->
  <div
    class="bg-box"
    :style="{ width: bigBoxWidth + 'px', height: bigBoxHeight + 'px' }"
    v-if="bigBoxWidth > 1500"
  >
    <!-- :style="{width:bigBoxWidth*0.4+'px',height:bigBoxHeight*0.5+'px',marginLeft:-bigBoxWidth*0.2+'px',marginTop:-bigBoxHeight*0.25+'px'}" -->
    <div class="sign-box">
      <div class="sign-in-content">
        <div class="sign-in-title" style="text-align: center">
          <p style="font-size: 30px; font-weight: 800; padding: 20px">
            Teamanager
          </p>
        </div>
        <el-form
          id="signup-form"
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
          <!-- v-model可以加在需要动态绑定的属性上 -->

          <sign-input-item
            v-model:inputValue="form.username"
            :name="'Username'"
            :type="'text'"
          >
          </sign-input-item>
          <sign-input-item
            v-model:inputValue="form.password"
            :name="'Password'"
            :type="'password'"
          >
          </sign-input-item>
          <div class="submit-box">
            <input
              class="btn-submit"
              type="button"
              @click="submitForm"
              value="Sign up your Team"
            />
          </div>
        </el-form>
      </div>
      <div class="sign-toggle-box-link">
        <p>Doesn't have an account of Teamanager</p>
        <router-link to="/signup">Sign up</router-link>
        <span>© Teamanager</span>
      </div>
    </div>
  </div>
  <div
    class="no-bg-box"
    :style="{ width: bigBoxWidth + 'px', height: bigBoxHeight + 'px' }"
    v-else
  >
    <!-- :style="{width:bigBoxWidth*0.4+'px',height:bigBoxHeight*0.5+'px',marginLeft:-bigBoxWidth*0.2+'px',marginTop:-bigBoxHeight*0.25+'px'}" -->
    <div class="sign-box">
      <div class="sign-in-content">
        <div class="sign-in-title" style="text-align: center">
          <p style="font-size: 30px; font-weight: 800">Teamanager</p>
        </div>
        <el-form
          id="signup-form-nobg"
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
          <sign-input-item
            v-model:inputValue="form.username"
            :name="'Username'"
            :type="'text'"
          >
          </sign-input-item>
          <sign-input-item
            v-model:inputValue="form.password"
            :name="'Password'"
            :type="'password'"
          >
          </sign-input-item>
          <div class="submit-box">
            <input
              class="btn-submit"
              type="button"
              @click="submitForm"
              value="Sign up your Team"
            />
          </div>
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
import SignInputItem from "../components/sign/SignInputItem.vue";

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
      //AJAX方式
      this.axios({
        url: this.url,
        data: {
            username: this.form.username,
            password: this.form.password,
        },
        method: "post",
        baseURL: "http://localhost:8088/api",
        // headers.post['Content-type'] = "application/json",
      }).then((response) => {
        console.log(response);
      });
    },
  },
};
</script>

<style scoped src="../assets/css/sign-css.css">
</style>
