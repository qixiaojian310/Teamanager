<template>
  <el-popover
    :width="250"
    :placement="popLayout"
    trigger="hover"
    :hide-after="100"
    :disabled="disabled"
  >
    <template #reference>
      <!-- 用户没有头绪就显示图标 -->
      <div class="avatar-box">
        <el-avatar :size="userIconHeight">
          <p class="avatar-content">{{ userName.slice(0, 2) }}</p>
        </el-avatar>
      </div>
    </template>
    <template #default>
      <el-row style="margin-bottom: 0">
        <el-col :span="9" style="margin-bottom: 0">
          <div class="avatar-box">
            <el-avatar :size="userIconHeight">
              <p class="avatar-content">{{ userName.slice(0, 2) }}</p>
            </el-avatar>
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
      default: "",
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
    widthMore768Less1200: {
      type: Boolean,
    },
    disabled: {
      type: Boolean,
      default: false,
    },
    forceDirection: {
      type: String,
      default: "",
    },
  },
  computed: {
    popLayout() {
      if (this.forceDirection == "") {
        if (this.widthLess768) {
          return "bottom";
        } else {
          if (this.widthMore768Less1200) {
            return "left-end";
          } else {
            return "left-end";
          }
        }
      } else {
        return this.forceDirection;
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
  margin-bottom: 15px;
  margin-top: 15px;
}
.avatar-content {
  font-size: 20px;
  font-weight: 800;
}
.avatar-box {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin: 10px;
}
</style>
