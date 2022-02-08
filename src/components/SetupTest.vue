<template>
  <div>
    {{ name }}
    <p>{{ age }}</p>
    <p>{{ country }}</p>
    <p>{{ major }}</p>
    <p>{{ doubleMajor }}</p>
    <input type="text" @input="major = $event.target.value">
    <button @click="plusOne()">+</button>
  </div>
</template>

<script>
import {toRefs} from "vue";
import setupInit from "../composables/setupTest";
export default {
  name: "SetupTest",
  data() {
    return {
      name: "xiaosan",
    };
  },
  props: {
    country: {
      type: String,
      default: "china",
      required: true,
    },
  },

  // 对于其他的逻辑关注点我们也可以这样做，但是你可能已经在问这个问题了——这不就是把代码移到 setup 选项并使它变得非常大吗？
  // 嗯，确实是这样的。这就是为什么我们要在继续其他任务之前，我们首先要将上述代码提取到一个独立的组合式函数中。
  // 让我们从创建 useUserRepositories 函数开始：

  setup(props) {

  const {
    country
  } = toRefs(props);

    const {
          name,
    age,
    plusOne,
    major,
    doubleMajor
    } = setupInit(country);

    return {
      name,
      age,
      plusOne,
      major,
      doubleMajor
    };
    // console.log(props.country);

    // const name = ref("小四");
    // // name就是一个对象，这个对象有一个属性叫value，我们可以用name.value来访问
    // //ref 可以将data属性合并到setup函数中，它可以让一个值转换为具有响应式效果的对象

    // const age = ref(18);
    // function plusOne() {
    //   age.value++; //想改变值或获取值 必须.value
    // }

    // //setup中可以合并生命周期钩子来合并API
    // onMounted(plusOne)

    // //将监听器合并到setup函数中
    // watch(age, (newValue) => {
    //   console.log("the new age is " + age.value);
    // });

    // //toRefs 将props中的属性转换为setup函数内可访问的具有响应式效果的对象
    // const {country} = toRefs(props);
    // watch(country, (newValue) => {
    //   console.log("the new country is " + country.value);
    // });

    // //computed函数可以在setup函数中使用计算属性
    // const major = ref("computer");
    // const doubleMajor = computed(() => {
    //   return major.value + major.value;
    // });


    // return {
    //   //必须返回 模板中才能使用
    //   name,
    //   age,
    //   plusOne,
    //   major,
    //   doubleMajor
    // };
  },
};
</script>