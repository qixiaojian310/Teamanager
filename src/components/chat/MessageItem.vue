<template>
  <template>
    <div class="message-box sender" v-if="isSend = true">
      <div class="msg-content">
        <span>{{message}}</span>
      </div>
      <head-icon :disabled="true" :userIconHeight="30" :userIconWidth="30" :userIconSrc="userIconSrc"></head-icon>
    </div>
    <div class="message-box receiver" v-else>
      <head-icon :disabled="true" :userIconHeight="30" :userIconWidth="30" :userIconSrc="userIconSrc"></head-icon>
      <div class="msg-content">
        <span>{{message}}</span>
      </div>
    </div>
  </template>
</template>

<script>
import HeadIcon from '../HeadIcon.vue'
export default {
  components: { HeadIcon },
  name: "MessageItem",
  props: {
    message: {
      type: String,
      required: true
    },
    userIconSrc: {
      type: String,
      default: ""
    },
    isSend: {
      type: Boolean,
      default: true
    }
  },
}
</script>

<style scoped>
.message-box{
  width: 100%;
  height: 60px;
  display: flex;
}
.sender{
  justify-content: flex-end;
}
.receiver{
  justify-content: flex-start;
}
.msg-content{
  margin-left: 10px;
  margin-right: 10px;
  background: #1b1b1b;
}
</style>