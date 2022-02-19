<template>
  <div class="toolbar">
    <!-- TODO - toolbar 应该有两部分 -->
    <!-- 下首先应该有总览，上会出现各种排序方式加一个搜索框，右边是搜索结果
    下有加入，上面有搜素框，右边是搜索结果 -->
    <div class="toolbar-list">
      <sign-input-item :name="'Search'" v-model:inputValue="SearchCOntent" :fontSize="'1.2rem'" :backgroundColor="'#e6e1e1'" :inputHeight="2"></sign-input-item>
    </div>
    <div class="toolbar-list">

    </div>
  </div>
</template>

<script>
import SignInputItem from '../../sign/SignInputItem.vue'
export default {
  components: { SignInputItem },
  name: 'StudentToolbar',
  data(){
    return{
      SearchCOntent: '',
    }
  }
}
</script>

<style>
.toolbar {
  height: 100%;
  width: 100%;
  /* margin-bottom: 5%;
  margin-top: 5%; */
  background-color: #e6e1e1;
}
.toolbar-list{
  height:45%;
  padding: 5%;
  width: 90%;
}
</style>