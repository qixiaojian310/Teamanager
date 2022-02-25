<template>
  <!-- 宽度小于500时不显示背景图 -->
  <div
    class="bg-box"
    :style="{ width: bigBoxWidth + 'px', height: bigBoxHeight + 'px' }"
    v-if="bigBoxWidth > 1200"
  >
    <!-- :style="{width:bigBoxWidth*0.4+'px',height:bigBoxHeight*0.5+'px',marginLeft:-bigBoxWidth*0.2+'px',marginTop:-bigBoxHeight*0.25+'px'}" -->
    <div class="sign-box">
      <div class="sign-up-box">
        <div class="sign-in-title" style="text-align: center">
          <p
            style="font-size: 30px; font-weight: 800; padding: 5px; margin: 5px"
          >
            Teamanager
          </p>
        </div>
        <el-scrollbar class="sign-in-box-content" :height="300">
          <el-form
            :model="form"
            :rules="rules"
            ref="form"
            label-width="0px"
            class="login-form"
          >
            <el-form-item>
              <p>input your username</p>
              <el-input
                v-model="form.username"
                :placeholder="placeholder.username"
                :prefix-icon="User"
              >
                <template #suffix>
                  <el-icon class="el-input__icon"><user /></el-icon> </template
              ></el-input>
              <p v-show="registered">This user name have been registered</p>
            </el-form-item>
            <el-form-item>
              <p>input your password</p>
              <el-input
                v-model="form.password"
                type="password"
                :placeholder="placeholder.password"
              >
                <template #suffix>
                  <el-icon class="el-input__icon"><lock /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item>
              <p>check your password</p>
              <el-input
                v-model="checkPassword"
                :type="checkInputInvisible"
                placeholder="check your password"
                @input="checkPasswordInput"
              >
              </el-input>
              <p v-show="isWrong" :style="{ color: '#F56C6C' }">
                Your password is no same
              </p>
            </el-form-item>
            <div class="cascader-box">
              <span style="margin-right: 1rem">choose your role</span>
              <el-cascader :options="roleOptions" v-model="role"></el-cascader>
            </div>
            <div class="submit-box">
              <input
                class="btn-submit"
                type="button"
                @click="submitForm"
                value="Sign up your Team"
                :style="{ cursor: cursorStatus }"
              />
            </div>
          </el-form>
        </el-scrollbar>
      </div>
      <div class="sign-toggle-box-link">
        <router-link to="/signin">Sign in</router-link>
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
        <div class="sign-in-box-content" :height="200">
          <el-form
            :model="form"
            :rules="rules"
            ref="form"
            label-width="0px"
            class="login-form"
          >
            <el-form-item>
              <p>input your username</p>
              <el-input
                v-model="form.username"
                :placeholder="placeholder.username"
                :prefix-icon="User"
              >
                <template #suffix>
                  <el-icon class="el-input__icon"><user /></el-icon> </template
              ></el-input>
              <p v-show="registered">This user name have been registered</p>
            </el-form-item>
            <el-form-item>
              <p>input your password</p>
              <el-input
                v-model="form.password"
                type="password"
                :placeholder="placeholder.password"
              >
                <template #suffix>
                  <el-icon class="el-input__icon"><lock /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item>
              <p>check your password</p>
              <el-input
                v-model="checkPassword"
                :type="checkInputInvisible"
                placeholder="check your password"
                @input="checkPasswordInput"
              >
              </el-input>
              <p v-show="isWrong" :style="{ color: '#F56C6C' }">
                Your password is no same
              </p>
            </el-form-item>
            <div class="submit-box">
              <input
                class="btn-submit"
                type="button"
                @click="submitForm"
                value="Sign up your Team"
              />
            </div>
            <div class="cascader-box">
              <span style="margin-right: 1rem">choose your role</span>
              <el-cascader :options="roleOptions" v-model="role"></el-cascader>
            </div>
          </el-form>
          <div class="sign-toggle-box-link">
            <p>Already have an account of Teamanager</p>
            <router-link to="/signin">Sign in</router-link>
            <span>© Teamanager</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Lock, User } from "@element-plus/icons-vue";
import SignInputItem from "../components/sign/SignInputItem.vue";

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
      url: "/registerStudent",
      placeholder: {
        username: "Enter your user name",
        password: "Enter your password",
      },
      checkPassword: "",
      checkInputInvisible: "password",
      isWrong: false,
      registered: false,
      cursorStatus: "not-allowed",
      roleOptions: [
        {
          value: "student",
          label: "Student",
        },
        {
          value: "teacher",
          label: "Teacher",
        },
      ],
      role: "",
    };
  },
  methods: {
    checkPasswordInput: function () {
      if (this.checkPassword != this.form.password) {
        this.isWrong = true;
        this.cursorStatus = "not-allowed";
        document.getElementsByClassName("btn-submit")[0].disabled = true;
      } else {
        this.isWrong = false;
        this.cursorStatus = "pointer";
        document.getElementsByClassName("btn-submit")[0].disabled = false;
      }
    },
    submitForm: function () {
      //AJAX方式
      this.axios({
        url: this.url,
        data: {
          studentId: this.form.username,
          password: this.form.password,
        },
        method: "post",
        baseURL: "http://localhost:8080/api/",
        // headers.post['Content-type'] = "application/json",
      }).then((response) => {
        console.log(response);
        //处理data不同的响应
        var responseData = response.data;
        if (responseData == "already have user") {
          console.log("already have user");
          //用户已经存在
          this.registered = true;
        } else if (responseData == "success") {
          //注册成功
          this.registered = false;
          this.$router.push("/student/" + this.form.username);
        } else {
          //注册失败
          console.error("注册失败");
        }
      });
    },
  },
  components: {
    SignInputItem,
    Lock,
    User,
  },
  mounted() {
    //禁用注册按钮
    document.getElementsByClassName("btn-submit")[0].disabled = true;
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
<style scoped>
p {
  margin: 0;
}
</style>