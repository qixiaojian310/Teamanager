<template>
  <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </div> -->
  <div>
    <router-view />
  </div>
</template>

<script>
import all from "./util/all";

export default {
  name: "App",
  mounted() {
    this.reloadChange();
  },
  beforeMount() {
    all();
  },
  methods: {
    //监听窗口大小变化
    reloadChange() {
      this.$store.state.windowSize.windowSizeWidth = window.innerWidth;
      this.$store.state.windowSize.windowSizeHeight = window.innerHeight;
      window.onresize = () => {
        this.$store.state.windowSize.windowSizeWidth = window.innerWidth;
        this.$store.state.windowSize.windowSizeHeight = window.innerHeight;
      };
    },
  },
};
</script>

<style>
.el-scrollbar__thumb {
  background-color: #0988ff !important;
  border-radius: 3px;
}
.el-scrollbar__bar {
  width: 4px !important;
}
</style>
<style>
:root {
  --header-color: var(--el-fill-color-blank);
  --header-link-bgcolor: var(--el-fill-color);
  --header-link-color: var(--el-text-color-primary);
  --header-link-hover-color: var(--el-text-color-primary);
  --main-left-card-bgcolor: var(--el-fill-color-blank);
  --main-left-input-bgcolor: var(--panel-bg-color);
  --main-center-bgcolor: var(--el-fill-color-blank);
  --main-center-title-color: var(--el-text-color-primary);
  /* 右侧工具栏背景色 */
  --right-bar-bgcolor: var(--el-fill-color-dark);
  --right-link-color: var(--el-text-color-primary);
  --team-toolbar-bgcolor: var(--right-bar-bgcolor);
  /* module和team的背景色 */
  --page-background: var(--el-bg-color-overlay);
  --left-slide-border-color: var(--el-text-color-primary);
  --chat-box-input-bgcolor: var(--el-fill-color-blank);
  /* --el-fill-color-blank: #333 !important;
  --el-fill-color-light: #444 !important;
  --el-text-color-primary: #f5f5f5 !important;
  --el-text-color-secondary: #12334e !important;
  --el-text-color-regular: #ffffff !important;

  --el-bg-color-overlay: #222 !important;*/
  --panel-bg-color: var(--el-fill-color-dark);

  /* 输入框的颜色 */
  --input-bg-color: var(--el-fill-color);
}
:root {
  /* 所有页面头部栏背景色 */
  --header-bg-color: #289c28;
  --header-link-hover-bgcolor: #1b681b;
  /* 左侧工具栏背景色 */
  --main-left-stripe-toolbar-bgcolor: var(--header-bg-color);
  /* 主页左侧工具窗口背景色 */
  --right-card-bgcolor: #cee9c6;
  /* cacasder的背景颜色 */
  /* --left-slide-content-bgcolor: #1b4e18; */
    --left-slide-content-bgcolor: #41763e;
  --chat-box-bgcolor: var(--left-slide-content-bgcolor);

  /* 重新确定element plus的颜色 */
}
/* 暗色主题 */
:root,
:host {
  --w-e-textarea-bg-color: var(--el-bg-color-page);
  --w-e-textarea-color: var(--el-text-color-primary);
  --w-e-toolbar-color: var(--el-text-color-regular);
  --w-e-toolbar-bg-color: var(--el-color-info);
  --w-e-toolbar-active-color: var(--el-bg-color-overlay);
  --w-e-toolbar-active-bg-color: var(--el-text-color-primary);
  --w-e-toolbar-disabled-color: var(--el-color-danger);
  --w-e-toolbar-border-color: #e8e8e8;
}
</style>
