<template>
  <el-container style="height:100%;position:relative" class="chat-box">
    <el-header :height="'30px'">
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
        <div class="send-box">
          <!-- <el-input v-model="message" placeholder="message"></el-input> -->
        <message-editor @send="send"></message-editor>

        </div>
  </el-container>
</template>

<script>
import MessageEditor from '@/components/editor/MessageEditor.vue'
import MessageItem from './MessageItem.vue'
export default {
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
  components: { MessageItem, MessageEditor },
  methods:{
    send(message){
      this.$emit('send', message)
    }
  }
}
</script>

<style scoped>
.header{
  font-size: 20px;
  font-weight: bold;
  margin: 10px;
  color: #000;
}
.send-box{
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding: 10px;
  position: absolute;
  bottom: 0px;
  right: 0px;
  width: 100%;
  box-sizing: border-box;
  border: 1px solid #ccc;
  background: var(--chat-box-input-bgcolor);
}
.chat-box{
  background: var(--chat-box-bgcolor);
  box-sizing: border-box;
}
</style>