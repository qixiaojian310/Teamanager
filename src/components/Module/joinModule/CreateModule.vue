<template>
  <el-container class="overflow:hidden">
    <el-row style="width: 100%">
      <el-col :span="20" :offset="2" style="height: 100%">
        <div class="input-item-box">
          <div style="padding: 20px">
            <span>Your Module Name</span>
            <el-input
              v-model="moduleName"
              placeholder="Your Module's Name"
            ></el-input>
          </div>
          <el-row>
            <el-col :span="10">
              <div class="input-item-content">
                <span>Your Module Size</span>
                <el-input-number
                  v-model="moduleSize"
                  placeholder=""
                ></el-input-number>
              </div>
              <div class="input-item-content">
                <span>Team number in your module</span>
                <el-input-number
                  v-model="teamNum"
                  placeholder=""
                ></el-input-number>
              </div>
            </el-col>
            <el-col :span="4">
              <div class="left-icon-box">
                <el-icon><arrow-right-bold /></el-icon>
              </div>
            </el-col>
            <el-col :span="10">
              <div class="computed-team-size">
                <div>Your Team size is {{ teamSize }}</div>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="submit-box">
          <input
            class="btn-submit"
            type="button"
            @click="submitForm"
            value="Create a Module"
          />
        </div>
      </el-col>
    </el-row>
  </el-container>
</template>
<script>
import qs from "qs";
import { ArrowRightBold } from "@element-plus/icons-vue";

//用来创建一个课程
export default {
  name: "CreateModule",
  data() {
    return {
      moduleName: "",
      teamNum: 5,
      moduleSize: 10,
      teacherId: this.$store.state.signInTeacher.name,
    };
  },
  methods: {
    submitForm() {
      this.axios({
        url: "createModuleByTeacher",
        data: qs.stringify(
          Object.assign(this.$data, {
            teamNum: this.teamNum,
          }),
          { indices: false }
        ),
        method: "post",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        if(res.data){
          this.$notify({
            title: "Success",
            message: "Create a module successfully",
            type: "success",
            position:"top-left"
          });
        }
      });
    },
  },
  computed: {
    //计算出teamSize
    teamSize() {
      return Math.ceil(this.moduleSize / this.teamNum);
    },
  },
  components: {
    ArrowRightBold,
  },
};
</script>

<style scoped>
.submit-box {
  display: flex;
  justify-content: center;
  margin-top: 2rem;
}
.btn-submit {
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  text-decoration: none;
  color: #1b1b1b;
  background-color: #65b54d56;
  border: 2px solid #65b54d;
  width: 200px;
  display: block;
  text-align: center;
  padding: 2%;
  border-radius: 10px;
  font-size: 1.2rem;
}
.btn-submit:hover {
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  background-color: #65b54d;
  color: #fff;
}
.input-item-content {
  padding-top: 10px;
  display: flex;
  justify-content: space-between;
}
.computed-team-size {
  height: 100%;
  display: flex;
  align-items: center;
}
.computed-team-size > div {
  font-size: 20px;
}
.left-icon-box {
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>