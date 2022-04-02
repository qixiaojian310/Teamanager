<template>
  <el-container>
    <el-header>
      {{contactName}}
    </el-header>
    <el-main>
      <el-row>
        <el-col :span="24">
          <el-scrollbar :height="300">
            <message-item></message-item>
          </el-scrollbar>
        </el-col>
      </el-row>
      </el-main>
      <el-main>
        <el-input v-model="message" placeholder="message"></el-input>
        <el-button type="success" @click="send">
          Send
        </el-button>
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
  components: { MessageItem },
  methods:{
    send(){
      this.$emit('send', this.message)
    }
  }
}
</script>

<style>

</style>