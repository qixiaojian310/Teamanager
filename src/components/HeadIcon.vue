<template>
  <div>
    <el-popover
      :width="250"
      :placement="popLayout"
      trigger="hover"
      :hide-after="100"
    >
      <template #reference>
        <!-- 用户没有头绪就显示图标 -->
        <div class="icon-box head-box" v-if="userIconSrc == ''">
          <svg
            t="1644432695605"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="3022"
            :width="userIconWidth"
            :height="userIconHeight"
          >
            <path
              d="M1024 544c0 265.12-214.944 480-480 480h-64C214.912 1024 0 809.12 0 544v-64C0 214.912 214.912 0 480 0h64c265.056 0 480 214.912 480 480v64z"
              fill="#376fbd"
              p-id="3023"
            ></path>
            <path
              d="M512.032 160a216.896 216.896 0 0 0-216.64 216.64 216.896 216.896 0 0 0 216.64 216.608c119.424 0 216.672-97.248 216.672-216.608A216.96 216.96 0 0 0 512.032 160z"
              fill="#FFFFFF"
              p-id="3024"
            ></path>
            <path
              d="M678.976 588.256c-46.112 36.512-103.584 59.104-166.944 59.104s-120.768-22.656-167.04-59.104c-87.584 55.776-148.544 153.696-156.928 267.104 30.656 4.16 85.248 8.64 163.104 8.64h321.568c77.92 0 132.512-4.48 163.232-8.672-8.32-113.44-69.312-211.296-156.992-267.072z"
              fill="#FFFFFF"
              p-id="3025"
            ></path>
          </svg>
        </div>
        <div class="icon-box head-box" v-else>
          <div
            :style="{
              width: userIconWidth + 'px',
              height: userIconHeight + 'px',
              background: 'no-repeat 0% 0%',
              backgroundImage: `url(${userIconSrc})`,
              backgroundSize: '100% 100%',
              borderRadius: '50%',
            }"
            alt=""
          />
        </div>
      </template>
      <template #default>
        <el-row style="margin-bottom: 0">
          <el-col :span="9" style="margin-bottom: 0">
            <div
              class="icon-box"
              style="margin-bottom: 0"
              v-if="userIconSrc == ''"
            >
              <svg
                t="1644432695605"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="3022"
                :width="userIconWidth + 10"
                :height="userIconHeight + 10"
              >
                <path
                  d="M1024 544c0 265.12-214.944 480-480 480h-64C214.912 1024 0 809.12 0 544v-64C0 214.912 214.912 0 480 0h64c265.056 0 480 214.912 480 480v64z"
                  fill="#376fbd"
                  p-id="3023"
                ></path>
                <path
                  d="M512.032 160a216.896 216.896 0 0 0-216.64 216.64 216.896 216.896 0 0 0 216.64 216.608c119.424 0 216.672-97.248 216.672-216.608A216.96 216.96 0 0 0 512.032 160z"
                  fill="#FFFFFF"
                  p-id="3024"
                ></path>
                <path
                  d="M678.976 588.256c-46.112 36.512-103.584 59.104-166.944 59.104s-120.768-22.656-167.04-59.104c-87.584 55.776-148.544 153.696-156.928 267.104 30.656 4.16 85.248 8.64 163.104 8.64h321.568c77.92 0 132.512-4.48 163.232-8.672-8.32-113.44-69.312-211.296-156.992-267.072z"
                  fill="#FFFFFF"
                  p-id="3025"
                ></path>
              </svg>
            </div>
            <div style="margin-bottom: 0" class="icon-box" v-else>
              <div
                :style="{
                  width: userIconWidth + 10 + 'px',
                  height: userIconHeight + 10 + 'px',
                  background: 'no-repeat 0% 0%',
                  backgroundImage: `url(${userIconSrc})`,
                  backgroundSize: '100% 100%',
                  borderRadius: '50%',
                }"
                alt=""
              />
            </div>
          </el-col>
          <el-col :span="15" style="margin-bottom: 0">
            <p style="font-size: 18px; margin-bottom: 0; font-weight: 800">
              {{ userName }}
            </p>
          </el-col>
        </el-row>
        <el-divider></el-divider>
        <p style="margin: 5%" v-if="userInfor == ''">Nothing to say...</p>
        <p style="margin: 5%" v-else>{{ userInfor }}</p>
      </template>
    </el-popover>
  </div>
</template>

<script>
export default {
  name: "HeadIcon",
  props: {
    widthLess768: {
      type: Boolean,
    },
    userName: {
      type: String,
    },
    userIconSrc: {
      type: String,
    },
    userIconWidth: {
      type: Number,
    },
    userIconHeight: {
      type: Number,
    },
    userInfor: {
      type: String,
    },
    widthMore768Less1200:{
      type: Boolean,
    }
  },
  computed: {
    popLayout() {
      if (this.widthLess768) {
        return "bottom";
      } else {
        if(this.widthMore768Less1200){
          return "left-end";
        }else{
          return "right-end";
        }
      }
    },
  },
};
</script>

<style scoped>
.icon-box {
  display: flex;
  justify-content: center;
  align-items: center;
}
.head-box {
  margin-bottom: 30px;
  margin-right: 30px;
  margin-left: 30px;
}
</style>
