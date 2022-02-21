<template>
  <div class="toolbar">
    <!-- TODO - toolbar 应该有两部分 -->
    <!-- 下首先应该有总览，上会出现各种排序方式加一个搜索框，右边是搜索结果
    下有加入，上面有搜素框，右边是搜索结果 -->
    <div class="toolbar-list">
      <sign-input-item
        :name="'Search'"
        v-model:inputValue="SearchCOntent"
        :fontSize="'1.2rem'"
        :backgroundColor="'#e6e1e1'"
        :inputHeight="2"
      ></sign-input-item>
      <el-cascader
        placeholder="Try searchingL Guide"
        :options="options"
        v-model="value"
        :props="props"
        filterable
      >
        <template #default="{ data }">
      <span>{{ data.teacherName }}</span>
      <span> ({{ data.teacherNumber }}) </span>
    </template>
      </el-cascader>
    </div>
    <div class="toolbar-list">
      <a class="btn-all-module">
        <i class="fa fa-bars"></i>
        ALL Modules
      </a>
      <a class="btn-add-module">
        <i class="fa fa-plus-square-o"></i>
        ADD Module
      </a>
    </div>
  </div>
</template>

<script>
import SignInputItem from "../../sign/SignInputItem.vue";
export default {
  components: { SignInputItem },
  name: "StudentToolbar",
  data() {
    return {
      SearchCOntent: "",
      props:{
        multiple:true,
        label:'teacherName'
      }
    };
  },
  props:{
    options:{
      type:Array,
    }
  }
};
</script>

<style>
.toolbar {
  height: 100%;
  width: 100%;
  /* margin-bottom: 5%;
  margin-top: 5%; */
  background-color: #e6e1e1;
}
.toolbar-list {
  height: 45%;
  padding: 5%;
  width: 90%;
}

.btn-all-module {
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  text-decoration: none;
  color: #df7599;
  background-color: #fac1d3;
  border: 2px solid #df7599;
  width: 80%;
  display: block;
  text-align: center;
  padding: 2%;
  border-radius: 10px;
  font-size: 1.2rem;
}

.btn-add-module {
  transition: background-color 0.35s ease-in-out, color 0.35s ease-in-out;
  text-decoration: none;
  color: #066e14;
  background-color: #65b54d56;
  border: 2px solid #65b54d;
  width: 80%;
  display: block;
  text-align: center;
  padding: 2%;
  margin-top: 10px;
  border-radius: 10px;
  font-size: 1.2rem;
}
</style>
