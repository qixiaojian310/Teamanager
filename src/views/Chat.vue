<template>
  <el-container :style="{ height: containerHeight - 60 + 'px' }">
    <el-aside
      :width="'250px'"
      :style="{ height: containerHeight - 60 + 'px' }"
      v-if="!inTeam"
    >
      <el-scrollbar :height="containerHeight - 60">
        <div class="contacts">
          <p class="aside-title">Chatroom</p>
          <div>
            <el-button type="primary" @click="getChatRoom" style="width: 100%">
              <i class="fa fa-refresh fa-spin"></i>
              <span style="padding-left: 10px">refresh</span>
            </el-button>
          </div>
          <chat-room-item
            v-for="chatRoom in chatRooms"
            :key="chatRoom.chatRoomId"
            :chat-room-id="chatRoom.chatRoomId"
            :chat-room-name="chatRoom.chatRoomName"
            @choose-chat-room="chooseChatRoom"
          ></chat-room-item>
        </div>
      </el-scrollbar>
    </el-aside>
    <el-main>
      <chat-box
        @send="sendMessage"
        :all-messages="messages"
        :contact-name="focusChatRoom.chatRoomName"
        :chat-box-height="containerHeight - 292"
      ></chat-box>
    </el-main>
    <el-aside :width="'250px'" :style="{ height: containerHeight - 60 + 'px' }">
      <el-scrollbar
        :style="{ height: containerHeight - 100 + 'px' }"
        :height="containerHeight - 100"
      >
        <p class="aside-title">Contact</p>
        <div class="contacts">
          <contact-item
            v-for="contact in focusChatRoom.contacts"
            :key="contact.student.studentId"
            :contact-name="contact.student.studentId"
            :contact-date="contact.sendTime"
            :contact-info="contact.student.studentInfo"
            :message="contact.message"
          ></contact-item>
        </div>
      </el-scrollbar>
      <div style="height: 40px">
        <el-button
          type="primary"
          style="width: 100%; height: 40px"
          :disabled="inChatRoom"
          @click="joinChatRoom"
          >{{
            inChatRoom ? "Have Join Chat Room" : "Join Chat Room"
          }}</el-button
        >
      </div>
    </el-aside>
  </el-container>
</template>

<script>
import ContactItem from "@/components/chat/ContactItem";
import ChatRoomItem from "@/components/chat/ChatRoomItem";
import ChatBox from "@/components/chat/ChatBox";

import { ElNotification } from "element-plus";

import SockJS from "sockjs-client";
import Stomp from "stompjs";
import qs from "qs";

export default {
  components: { ContactItem, ChatBox, ChatRoomItem, ElNotification },
  data() {
    return {
      dataList: [],
      sockjsClient: null,
      stompClient: null,
      chartContent: "",
      sockjsAddr: "http://localhost:8080/api/build-chat-room-stomp-connect",
      websocketAddr: "ws://localhost:8080/gs-guide-websocket",
      connected: false,
      nickName: "",
      message: "",
      messages: [],
      role: this.$store.state.role,
      chatRooms: [],
      focusChatRoom: {
        contacts: [],
      },
    };
  },
  props: {
    inTeam: {
      type: Boolean,
      default: false,
    },
    chatRoomIdProps: {
      type: Number,
      default: 0,
    },
  },
  mounted() {
    if (!this.inTeam) {
      this.getChatRoom();
    } else {
      // this.getChatRoom();
      this.chatRooms.push({
        chatRoomId: this.chatRoomIdProps,
        chatRoomName: "Team Chatroom",
        contacts: [],
      });
      this.chooseChatRoom(this.chatRoomIdProps, "Team Chatroom");
    }
  },
  methods: {
    getChatRoom() {
      this.chatRooms = [];
      var chatRoomMap = [];
      // var chatRooms = []
      if (this.role == "student") {
        this.axios({
          method: "post",
          url: "/student" + "/getChatRoom",
          data: qs.stringify(
            {
              studentId: this.$store.state.signInStudent.name,
            },
            { indices: false }
          ),
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        })
          .then((res) => {
            console.log(res);
            chatRoomMap = res.data;
          })
          .then(() => {
            for (var chatRoomId in chatRoomMap) {
              var chatRoomName = chatRoomMap[chatRoomId];
              var chatRoom = {
                chatRoomId: chatRoomId,
                chatRoomName: chatRoomName,
              };
              this.chatRooms.push(chatRoom);
            }
          });
      } else if (this.role == "teacher") {
        this.axios({
          method: "post",
          url: "/teacher" + "/getChatRoom",
          data: qs.stringify(
            {
              teacherId: this.$store.state.signInTeacher.name,
            },
            { indices: false }
          ),
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        })
          .then((res) => {
            console.log(res);
            chatRoomMap = res.data;
          })
          .then(() => {
            for (var chatRoomId in chatRoomMap) {
              var chatRoomName = chatRoomMap[chatRoomId];
              var chatRoom = {
                chatRoomId: chatRoomId,
                chatRoomName: chatRoomName,
              };
              this.chatRooms.push(chatRoom);
            }
          });
      }
    },
    getTeacher(chatRoomId) {
      this.axios({
        method: "post",
        url: "/getTeacher",
        data: qs.stringify(
          {
            chatRoomId: chatRoomId,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        this.focusChatRoom.teacher = res.data;
      });
    },
    getContact(chatRoomId) {
      this.axios({
        method: "post",
        url: "/getContact",
        data: qs.stringify(
          {
            chatRoomId: chatRoomId,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        this.focusChatRoom.contacts = res.data;
      });
    },
    joinChatRoom() {
      this.axios({
        method: "post",
        url: "/joinChatRoom",
        data: qs.stringify(
          {
            chatRoomId: this.focusChatRoom.chatRoomId,
            studentId: this.$store.state.signInStudent.name,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }).then((res) => {
        if (res.data != 0) {
          this.$notify({
            title: "Success",
            message: "Join Chat Room Success",
            type: "success",
            position: "top-left",
            duration: 10000,
          });
          this.getContact(this.focusChatRoom.chatRoomId);
          this.getTeacher(this.focusChatRoom.chatRoomId);
          this.inChatRoom = true;
        }
      });
    },
    chooseChatRoom(chatRoomId, chatRoomName) {
      //先关闭webSocket链接
      this.clickClose();
      this.focusChatRoom.chatRoomId = chatRoomId;
      this.focusChatRoom.chatRoomName = chatRoomName;
      this.getContact(chatRoomId);
      this.getTeacher(chatRoomId);
      //先接受所有的信息再打开链接
      this.receiveAllExistMessage(chatRoomId);
    },
    // onOpen() {
    //   console.log("open");
    //   document.getElementById("status").value = "已连接";
    // },
    // onMessage() {
    //   if (this.chartContent.length > 0) {
    //     this.chartContent += "\n";
    //   }
    //   // this.chartContent = this.chartContent + e.data;
    //   document.getElementById("chartContent").value = this.chartContent;
    // },
    // onClose() {
    //   console.log("close");
    //   document.getElementById("status").value = "未连接...";
    // },
    clickConnect(chatRoomId) {
      this.sockjsClient = new SockJS(this.sockjsAddr);
      this.stompClient = Stomp.over(this.sockjsClient);
      this.stompClient.connect({ chatRoomId: chatRoomId }, (frame) => {
        this.setConnected(true);
        console.log("Connected: " + frame);
        this.stompClient.subscribe(
          "/user/queue/receiveMessage",
          (receiveMessage) => {
            var chatMessage = JSON.parse(receiveMessage.body).chatMessage;
            console.log(chatMessage);
            this.messages.push(chatMessage);
          }
        );
      });
    },
    receiveAllExistMessage(chatRoomId) {
      this.messages = [];
      this.axios({
        method: "post",
        url: "/getMessages",
        data: qs.stringify(
          {
            chatRoomId: chatRoomId,
          },
          { indices: false }
        ),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
        .then((res) => {
          this.messages = res.data;
        })
        .then(() => {
          this.clickConnect(chatRoomId);
        });
    },
    clickClose() {
      if (this.stompClient != null) {
        this.stompClient.disconnect();
      }
      this.setConnected(false);
      console.log("Disconnected");
    },
    sendMessage(message) {
      var senderId = "";
      var studentState = true;
      if (this.role == "student") {
        senderId = this.$store.state.signInStudent.name;
        studentState = true;
      } else {
        senderId = this.$store.state.signInTeacher.name;
        studentState = false;
      }
      var sendTime = Date.parse(new Date());
      this.stompClient.send(
        "/app/sendMessage",
        {},
        JSON.stringify({
          senderId: senderId,
          studentState: studentState,
          message: message,
          sendTime: sendTime,
        })
      );
    },
    showGreeting(message) {
      this.message = this.message + "\n" + message;
      console.log("greeting" + message);
    },
    setConnected(connected) {
      this.connected = connected;
      this.message = "";
    },
  },
  computed: {
    containerHeight() {
      if (!this.inTeam) {
        return this.$store.state.windowSize.windowSizeHeight;
      } else {
        return this.$store.state.windowSize.windowSizeHeight - 180;
      }
    },
    inChatRoom() {
      var flag = false;
      this.focusChatRoom.contacts.find((contact) => {
        if (contact.student.studentId == this.$store.state.signInStudent.name) {
          flag = true;
        }
      });
      return flag;
    },
  },
};
</script>

<style scoped>
#main-content {
  max-width: 940px;
  padding: 2em 3em;
  margin: 0 auto 20px;
  background-color: #fff;
  border: 1px solid #e5e5e5;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
}
.contacts {
  display: flex;
  flex-direction: column;
}
.el-aside {
  background: rgb(64, 64, 64);
}
.aside-title {
  color: #fff;
  font-size: 20px;
  font-weight: bold;
  padding: 10px 0;
  width: 100%;
  margin: 0;
  background: rgba(80, 80, 80);
}
</style>
