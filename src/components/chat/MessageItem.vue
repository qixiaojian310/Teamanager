<template>
  <div class="message-box sender" v-if="isSend">
    <div class="msg-content">
      <div class="info-box">
        <p>{{ senderId }}</p>
        <p>{{ sendTime }}</p>
      </div>
      <p class="message" v-html="message"></p>
    </div>
    <head-icon
      :disabled="true"
      :userIconHeight="40"
      :userIconWidth="40"
      :user-name="senderId"
    ></head-icon>
  </div>
  <div class="message-box receiver" v-else>
    <head-icon
      :disabled="true"
      :userIconHeight="40"
      :userIconWidth="40"
      :user-name="senderId"
    ></head-icon>
    <div class="msg-content">
      <div class="info-box">
        <p>{{ senderId }}</p>
        <p>{{ sendTime }}</p>
      </div>
      <p class="message" v-html="message"></p>
    </div>
  </div>
</template>

<script>
import HeadIcon from "../HeadIcon.vue";
export default {
  components: { HeadIcon },
  name: "MessageItem",
  props: {
    message: {
      type: String,
      required: true,
    },
    userIconSrc: {
      type: String,
      default: "",
    },
    senderId: {
      type: String,
      default: "",
    },
    sendTimeNum: {
      type: Number,
    },
  },
  computed: {
    sendTime() {
      return this.changeTimeFormat(this.sendTimeNum);
    },
    isSend() {
      if (this.$store.state.role == "student") {
        if (this.senderId == this.$store.state.signInStudent.name) {
          return true;
        } else {
          return false;
        }
      } else {
        if (this.senderId == this.$store.state.signInTeacher.name) {
          return true;
        } else {
          return false;
        }
      }
    },
  },
  methods: {
    changeTimeFormat(timestamp) {
      let _date = new Date(parseInt(timestamp));
      let y = _date.getFullYear();
      let m = _date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      let d = _date.getDate();
      d = d < 10 ? "0" + d : d;
      let h = _date.getHours();
      h = h < 10 ? "0" + h : h;
      let minute = _date.getMinutes();
      minute = minute < 10 ? "0" + minute : minute;
      let second = _date.getSeconds();
      second = second < 10 ? "0" + second : second;
      // console.log( y + '-' + m + '-' + d + ' ' + '　' + h + ':' + minute + ':' + second)
      let dates =
        y + "-" + m + "-" + d + " " + " " + h + ":" + minute + ":" + second;

      return dates;
    },
  },
};
</script>

<style scoped>
.message-box {
  width: 100%;
  margin: 15px 0px;
  display: flex;
  border-radius: 5px;
}
.sender {
  justify-content: flex-end;
}
.receiver {
  justify-content: flex-start;
}

.msg-content {
  margin-left: 10px;
  margin-right: 10px;
  padding: 10px;
  padding-top: 0px;
  border-radius: 5px;
  color: #222;
}
.sender > .msg-content {
  border-right-width:7px;
  border-right-color: #444791;
  border-right-style: solid;
  background: #a4e079;
}

.receiver > .msg-content{
  border-left-width: 7px;
  border-left-style: solid;
  border-left-color: #444791;
  background: rgb(241, 241, 241) 
}
.info-box {
  display: flex;
  align-content: center;
}
.info-box p {
  margin-left: 15px;
  font-weight: 600;
  /* color: #fff; */
}
.message {
  /* color: #fff; */
  margin: 10px;
  word-break: break-all;
}
</style>