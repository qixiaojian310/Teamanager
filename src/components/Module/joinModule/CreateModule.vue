<template>
  <el-container class="overflow:hidden">
    <el-row style="width: 100%">
      <el-col :span="20" :offset="2" style="height: 100%">
        <div class="input-item-box">
          <div class="input-item">
            <span>Your Module Name</span>
            <el-input
              v-model="moduleName"
              placeholder="Your Module's Name"
            ></el-input>
          </div>
          <div class="input-item">
            <span>Your Module Size</span>
            <el-input-number v-model="moduleSize" placeholder=""></el-input-number>
          </div>
        </div>
        <div class="submit-box">
          <input
            class="btn-submit"
            type="button"
            @click="submitForm"
            value="Sign up your Team"
          />
        </div>
      </el-col>
    </el-row>
  </el-container>
</template>
<script>
import qs from 'qs'

//用来创建一个课程
export default {
  name: "CreateModule",
  data() {
    return {
      moduleName:"",
      moduleSize:10,
      teacherId:this.$store.state.signInTeacher.name
    };
  },
  methods:{
    submitForm(){
      this.axios({
        url:"createModuleByTeacher",
        data:qs.stringify(this.$data,{ indices: false }),
        method:"post",
        headers:{
          "Content-Type": "application/x-www-form-urlencoded",
        }
      }).then((res)=>{
        
      })
    }
  }
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
  color: #1b1b1b;
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
.input-item{
  margin: 20px;
}
</style>