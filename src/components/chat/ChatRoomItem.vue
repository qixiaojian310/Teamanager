<template>
  <button class="chat-room-item" @click="chooseChatRoom">
    <i class="fa fa-commenting fa-2x"></i>
    <span>{{chatRoomName}}</span>
  </button>
</template>

<script>

export default {
  name: "ChatRoomItem",
  props:{
    chatRoomId:{
      type:Number
    },
    chatRoomName:{
      type:String
    },
  },
  data(){
    return{
      isActive:false
    }
  },
  methods:{
    chooseChatRoom(){
      this.$emit('chooseChatRoom',this.chatRoomId,this.chatRoomName);
    }
  }
};
</script>

<style scoped>
.chat-room-item {
  display: flex;
  align-items: center;
  padding: 14px;
  background: #282828;
  height: 60px;
  box-sizing: border-box;
  color:#fff;
  border: none;
}
.chat-room-item:hover{
  background: #1a1a1a;
}
.chat-room-item:focus{
  background:#000 !important;
}
span{
  margin-left: 10px;
  font-size: 1.2rem;
  word-wrap: break-word;
}
</style>