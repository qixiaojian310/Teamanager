<template>
  <el-scrollbar>
      <head-icon
    :key="signInStudentObj.studentId"
    :userIconHeight="50"
    :userIconWidth="50"
    :userIconSrc="signInStudentObj.userIconSrc"
    :userName="signInStudentObj.name"
    :userInfor="signInStudentObj.Infor"
    :disabled="true"
  >
  </head-icon>
          <el-form
          id="signup-form-nobg"
          :model="form"
          :rules="rules"
          ref="form"
          label-width="0px"
          class="login-form"
        >
          <sign-input-item
            v-model:inputValue="form.username"
            :name="signInStudentObj.name"
            :type="'text'"
          >
          </sign-input-item>
          <textarea :value="signInStudentObj.info" @input="setUserInfo" style="background: var(--input-bg-color)">

          </textarea>
          <div class="submit-box">
            <input
              class="btn-submit"
              type="button"
              @click="submitForm"
              value="Ensure your change"
            />
          </div>
        </el-form>
  </el-scrollbar>
</template>

<script>
import HeadIcon from "../../HeadIcon.vue";
import SignInputItem from "../../sign/SignInputItem.vue";

export default {
  components: { HeadIcon,SignInputItem },
  name: "StudentInfo",
  props: {
    signInStudentId: {
      type: Number,
      required: true,
    },
  },
  data(){
    return{
      form: {
        username: this.$store.state.signInStudent.name,
        info: "",
      },
    }
  },
  methods:{
    setUserInfo:function(e){
      this.form.info = e.target.value;
    },
    submitForm:function () {
      this.axios({
        url: "/setStudentNoPwd",
        data: {
          studentId: this.form.username,
          studentInfo:this.form.info,
        },
        method: "post",
      }).then(res => {
        
      });
    }
  },
  computed: {
    signInStudentObj() {
      return this.$store.state.signInStudent
    },
  },
};
</script>

<style scoped>
.submit-box{
  display: flex;
  justify-content: center;
  margin-top: 2rem;
}
.btn-submit{
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  text-decoration: none;
  color: var(--el-text-color-primary);
  background-color: #65b54d56;
  border: 2px solid #65b54d;
  width: 80%;
  display: block;
  text-align: center;
  padding: 2%;
  border-radius: 10px;
  font-size: 1.2rem;
}
.btn-submit:hover{
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  background-color: #65b54d;
  color: #fff;
}
textarea{
  margin-left: 10px;
  margin-right: 10px;
  border-radius: 5px;
  resize: none;
  height: 15rem;
  width: calc(100% - 30px);
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  color: #70665e;
font-size: 1.5rem;

}
textarea:focus{
  outline: none;
  color: #65b54d;
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
}
</style>