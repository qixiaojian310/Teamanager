<template>
  <el-container>
    <el-header>
      <p class="header">{{contactName}}</p>
    </el-header>
    <el-main>
      <el-row>
        <el-col :span="24">
          <el-scrollbar :height="chatBoxHeight">
            <message-item v-for="allMessage in allMessages" :key="allMessage.index" :message="allMessage.message" :sender-id="allMessage.senderId" :send-time-num="allMessage.sendTime"></message-item>
          </el-scrollbar>
        </el-col>
      </el-row>
      </el-main>
      <el-main>
        <div class="send-box">
          <el-input v-model="message" placeholder="message"></el-input>
        <el-button type="success" @click="send">
          Send
        </el-button>
        </div>
      </el-main>
  </el-container>
</template>

<script>
import MessageItem from './MessageItem.vue'
export default {
  data(){
    return {
      message: '',
    }
  },
  props:{
    allMessages: {
      type: Array,
    },
    contactName: {
      type: String,
    },
    chatBoxHeight:{
      type: Number,
    }
  },
  components: { MessageItem },
  methods:{
    send(){
      this.$emit('send', this.message)
    }
  }
}
</script>

<style scoped>
.header{
  font-size: 20px;
  font-weight: bold;
  margin: 0px;
  color: #000;
}
.send-box{
  display: flex;
  align-items: center;
  padding: 10px;
}
</style>