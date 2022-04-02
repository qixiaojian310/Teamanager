<template>
  <!-- <el-container>
    <el-aside :width="'250px'">
      <contact-item></contact-item>
    </el-aside>
    <el-main>
      <chat-box @send="sendMessage"></chat-box>
    </el-main>
  </el-container> -->
  <div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" type="button" @click="clickConnect" :disabled="connected">Connect</button>
                    <button id="disconnect" class="btn btn-default" type="button" @click="clickClose" :disabled="!connected">Disconnect
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="name">What is your name?</label>
                    <input type="text" v-model="nickName" id="name" class="form-control" placeholder="Your name here...">
                </div>
                <button id="send" class="btn btn-default" type="button" @click="clickSend">Send</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>Greetings</th>
                </tr>
                </thead>
                <tbody id="greetings">
                  <tr>
                    <td>
                      <div v-html="message">
                      </div>
                    </td>
                  </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</template>

<script>
import ContactItem from "@/components/chat/ContactItem";
import ChatBox from "@/components/chat/ChatBox";

import SockJS from "sockjs-client";
import Stomp from "stompjs";
export default {
  components: { ContactItem, ChatBox },
  data() {
    return {
      dataList: [],
      sockjsClient: null,
      stompClient: null,
      chartContent: "",
      sockjsAddr: "http://localhost:8080/api/gs-guide-websocket",
      websocketAddr: "ws://localhost:8080/gs-guide-websocket",
      connected: false,
      nickName:"",
      message:""
    };
  },
  mounted: function () {},
  methods: {
    onOpen() {
      console.log("open");
      document.getElementById("status").value = "已连接";
    },
    onMessage() {
      if (this.chartContent.length > 0) {
        this.chartContent += "\n";
      }
      // this.chartContent = this.chartContent + e.data;
      document.getElementById("chartContent").value = this.chartContent;
    },
    onClose() {
      console.log("close");
      document.getElementById("status").value = "未连接...";
    },
    clickConnect() {
      // if (nickName.length <= 0) {
      //   alert("请先填写昵称");
      //   return;
      // }
      // document.getElementById("status").value = "正在连接中...";
      // if ("WebSocket" in window) {
      //   this.sockjsClient = new WebSocket(this.websocketAddr);
      // } else {
      //   this.sockjsClient = new SockJS(this.sockjsAddr);
      // }
      this.sockjsClient = new SockJS(this.sockjsAddr);
      this.stompClient = Stomp.over(this.sockjsClient);
      this.stompClient.connect({}, (frame) => {
        this.setConnected(true);
        console.log("Connected: " + frame);
        this.stompClient.subscribe("/topic/greetings", (greeting)=> {
          this.showGreeting(JSON.parse(greeting.body).content);
        });
      });
      // this.sockjsClient = new SockJS();
      // this.sockjsClient.onopen = this.onOpen;
      // this.sockjsClient.onmessage = this.onMessage;
      // this.sockjsClient.onclose = this.onClose;
    },
    clickClose() {
      // if (this.sockjsClient != null) {
      //   this.sockjsClient.close();
      //   this.sockjsClient = null;
      //   document.getElementById('status').value = "已断开";
      // }
      if (this.stompClient != null) {
        this.stompClient.disconnect();
      }
      this.setConnected(false);
      console.log("Disconnected");
    },
    clickSend() {
      // if (null == this.sockjsClient) {
      //   alert("请先连接");
      //   return;
      // }
      // var message = document.getElementById("message").value.trim();
      // if (message.length <= 0) {
      //   return;
      // }
      // try {
      //   var isSendSuccess = this.sockjsClient.send(message);
      //   if (!isSendSuccess) {
      //     alert("发送失败");
      //   } else {
      //     document.getElementById("message").value = "";
      //   }
      // } catch (e) {
      //   alert("发送失败");
      // }
      this.stompClient.send("/app/hello", {}, JSON.stringify({'name': this.nickName}));
    },
    showGreeting(message) {
      // $("#greetings").append("<tr><td>" + message + "</td></tr>");
      this.message = this.message + "\n" + message;
      console.log('greeting'+message);
    },
    setConnected(connected){
      this.connected = connected;
      this.message = ''
    }
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
</style>