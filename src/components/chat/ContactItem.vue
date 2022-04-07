<template>
  <div class="contact-item" >
    <head-icon
      :user-name="contactName"
      :user-icon-width="40"
      :user-infor="contactInfo"
      :user-icon-height="40"
    ></head-icon>
    <div class="right">
      <el-row class="right-top">
        <div class="who-name">
          <span class="who-msg">{{contactName}}</span>
        </div>
        <div class="time">{{contactDate}}</div>
      </el-row>
      <el-row class="right-bottom">
        <p class="last-msg">{{message}}</p>
      </el-row>
    </div>
  </div>
</template>

<script>
import HeadIcon from "@/components/HeadIcon";
export default {
  components: { HeadIcon },
  name: "ContactItem",
  // 从服务器传过来的
  props: {
    contactName: {
      type: String,
      required: true
    },
    contactInfo: {
      type: String,
      required: true
    },
    contactDate: {
      type: String,
      required: true
    },
    message: {
      type: String,
      required: true
    },
    userIconSrc: {
      type: String,
      default: ""
    }
  },
};
</script>

<style scoped>
.contact-item {
  display: flex;
  align-items: center;
  padding: 10px;
  background: #1b1b1b;  
  height: 100px; 
  box-sizing: border-box;
  color: #fff;
}
.active{
  background: #0f0f0f;
}
.right {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-left: 10px;
  width: calc(100% - 70px);
}
</style>