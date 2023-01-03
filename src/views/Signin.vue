<template>
  <!-- 宽度小于500时不显示背景图 -->
  <div
    class="bg-box"
    :style="{ width: bigBoxWidth + 'px', height: bigBoxHeight + 'px' }"
    v-if="bigBoxWidth > 1200"
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

          <div class="cascader-box">
            <span style="margin-right: 1rem">choose your role</span>
            <el-cascader :options="roleOptions" v-model="role" @change="rememberCodeInject"></el-cascader>
          </div>
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
              class="btn-submit sign-in-btn-submit"
              type="button"
              @click="submitForm"
              value="Sign in"
            />
            <el-checkbox border v-model="rememberCode">
              <i class="fa fa-unlock-alt"></i>
              <span>Remember code</span>
            </el-checkbox>
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
          <div class="cascader-box">
            <span style="margin-right: 1rem">choose your role</span>
            <el-cascader :options="roleOptions" v-model="role" @change="rememberCodeInject"></el-cascader>
          </div>
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
              class="btn-submit sign-in-btn-submit"
              type="button"
              @click="submitForm"
              value="Sign in"
            />
            <el-checkbox border v-model="rememberCode">
              <i class="fa fa-unlock-alt"></i>
              <span>Remember code</span>
            </el-checkbox>
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
import {getCookie} from "../util/cookieUtil"
import qs from "qs";

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
      rememberCode: false,
      form: {
        username: "",
        password: "",
      },
      placeholder: {
        username: "Enter your username",
        password: "Enter your password",
      },
      roleOptions: [
        {
          value: "/student",
          label: "Student",
        },
        {
          value: "/teacher",
          label: "Teacher",
        },
      ],
      role: "",
    };
  },
  methods: {
    adaptUserData() {
      this.$store.commit("updateRememberCookie", this.rememberCode);
      if (this.role[0] == "/student") {
        return qs.stringify({
          studentId: this.form.username,
          password: this.form.password,
          rememberCode: this.rememberCode
        },{indices:false});
      } else {
        return qs.stringify({
          teacherId: this.form.username,
          password: this.form.password,
          rememberCode:this.rememberCode
        },{indices:false});
      }
    },
    rememberCodeInject(){
      //该函数根据cookie中的数据填充表单
      console.log("执行cookie探测");
      if(this.role[0]=="/student"){
        this.form.username = getCookie("studentId")
        this.form.password = getCookie("studentPassword")
      }else if(this.role[0]=="/teacher"){
        this.form.username = getCookie("teacherId")
        this.form.password = getCookie("teacherPassword")
      }
    },
    submitForm() {
      //TODO 加一个判断用户是否选择自己身份的警告
      //AJAX方式
      if(this.role == ""){
        this.$notify({
          title: "Please choose your role",
          message: "",
          type: "warning",
          duration: 2000,
          position:"top-left"
        })
        return;
      }
      this.axios({
        url: this.role[0],
        data: this.adaptUserData(),
        method: "post",
        baseURL: "http://localhost:8080/api",
        headers:{
          'Content-Type':'application/x-www-form-urlencoded'
        }
        // headers.post['Content-type'] = "application/json",
      }).then((response) => {
        console.log(response);
        //处理data不同的响应
        var responseData = response.data;
        if (responseData == "Match") {
          console.log("登录成功");
          if (this.role[0] == "/student") {
            this.$store.commit("updateSignInStudentName", this.form.username);
          } else {
            this.$store.commit("updateSignInTeacherName", this.form.username);
          }
          this.$router.push(this.role + "/" + this.form.username);
          this.$notify({
            title: "Sign in successfully",
            message: "",
            type: "success",
            duration: 2000,
            position:"top-left"
          })
        }else if(responseData == "NoMatch"){
          this.$notify({
            title: "The password is wrong",
            message: "",
            type: "error",
            duration: 2000,
            position:"top-left"
          })
          return;
        }else{
          this.$notify({
            title: "The User is not exist",
            message: "",
            type: "warning",
            duration: 2000,
            position:"top-left"
          })
          return;
        }
      });
    },
  },
};
</script>

<style scoped src="../assets/css/sign-css.css">
</style>
<style scoped>
.sign-in-btn-submit{
  width: 50% !important;
}
i{
  margin-right: 0.5rem;
}

</style>
