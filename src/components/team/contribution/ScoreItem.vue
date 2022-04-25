<template>
  <el-row class="item-box">
    <el-col :span="3">
      <div class="head-box">
        <head-icon
        :user-name="student.studentId"
        :userIconSrc="student.studentId"
        :user-infor="student.studentInfo"
        :user-icon-height="64"
        :user-icon-width="64"
      ></head-icon>
      </div>
    </el-col>
    <el-col :span="17" :offset="1">
      <div>
        <el-rate
          v-model="value"
          disabled
          show-score
          text-color="#ff9900"
          :texts="['oops', 'disappointed', 'normal', 'good', 'great']"
          score-template="{value} stars"
          :colors="color"
        />
      </div>
      <div>
        <el-slider v-model="inputValue" :step="1" show-stops @change="changeScore" show-input/>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import HeadIcon from "@/components/HeadIcon";
import { ElNotification } from "element-plus";
import qs from 'qs'

export default {
  data() {
    return {
      inputValue: this.score,
      color:{
        3: '#F56C6C',
        4: '#E6A23C',
        5: '#67C23A',
      }
    };
  },
  props: {
    student: {
      type: Object,
      default() {
        return {
          studentId: "",
          studentInfo: "",
        };
      },
    },
    score: {
      type: Number,
      default: 0,
    },
    teamId: {
      type: Number,
      default: -1,
    },
  },
  computed: {
    value() {
      return (this.inputValue / 20).toFixed(1);
    },
  },
  components: {
    HeadIcon,
    ElNotification
  },
  methods: {
    changeScore() {
      this.axios({
        method:"post",
        url:"changeScore",
        data:qs.stringify({
          teamId:this.teamId,
          studentId:this.student.studentId,
          score:this.inputValue,
        }),
        headers:{
          "Content-Type":"application/x-www-form-urlencoded"
        }
      }).then(res=>{
        if(res.data!=0){
          this.$notify({
            title: "Success",
            message: "Change score successfully",
            type: "success",
            position:"top-left",
            duration: 2000
          });
        }
      })
    },
  },
};
</script>

<style scoped>
.item-box{
  background-color: #fff;
  margin-bottom: 10px;
  border-radius: 5px;
  padding: 10px;
  box-sizing: border-box;
}
.head-box{
  display: flex;
  justify-content: right;
  align-items: center;
}
</style>
