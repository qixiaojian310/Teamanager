<template>
  <div style="width: 100%">
    <Toolbar
      style="border-bottom: 1px solid #ccc"
      :editor="editorRef"
      :defaultConfig="toolbarConfig"
      :mode="mode"
    />
    <Editor
      style="height: 70px; overflow-y: hidden; width: 100%"
      v-model="valueHtml"
      :defaultConfig="editorConfig"
      :mode="mode"
      @onCreated="handleCreated"
    ></Editor>
    <el-button type="success" @click="send" style="float: right;"> Send </el-button>
  </div>
</template>

<script>
import "@wangeditor/editor/dist/css/style.css"; // 引入 css

import { onBeforeUnmount, ref, shallowRef, onMounted,emit } from "vue";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { i18nChangeLanguage } from "@wangeditor/editor";

export default {
  components: { Editor, Toolbar },
  setup(props, context) {
    i18nChangeLanguage("en");
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef();

    // 内容 HTML
    const valueHtml = ref("<p>hello</p>");

    // 模拟 ajax 异步获取内容
    onMounted(() => {
      setTimeout(() => {
        valueHtml.value = "<p>模拟 Ajax 异步设置内容</p>";
      }, 1500);
    });

    const send = ()=>{
      context.emit('send',valueHtml.value)
    }

    const toolbarConfig = {
      toolbarKeys: [
        // 菜单 key
        "headerSelect",

        // 分割线
        "|",

        // 菜单 key
        "bold",
        "italic",

        // 菜单组，包含多个菜单
        {
          key: "group-more-style", // 必填，要以 group 开头
          title: "更多样式", // 必填
          iconSvg: "<svg>....</svg>", // 可选
          menuKeys: ["through", "code", "clearStyle"], // 下级菜单 key ，必填
        },
        // 继续配置其他菜单...
      ],
    };
    const editorConfig = { placeholder: "请输入内容..." };

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value;
      if (editor == null) return;
      editor.destroy();
    });

    const handleCreated = (editor) => {
      editorRef.value = editor; // 记录 editor 实例，重要！
    };

    return {
      editorRef,
      valueHtml,
      mode: "default", // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated,
      send
    };
  },
};
</script>

<style ></style>
