import {ref,computed, watch, toRefs,onMounted} from 'vue'


export default function setupInit(country) {
  // console.log(props.country);

  const name = ref("小四");
  // name就是一个对象，这个对象有一个属性叫value，我们可以用name.value来访问
  //ref 可以将data属性合并到setup函数中，它可以让一个值转换为具有响应式效果的对象

  const age = ref(18);

  function plusOne() {
    age.value++; //想改变值或获取值 必须.value
  }

  //setup中可以合并生命周期钩子来合并API
  onMounted(plusOne)

  //将监听器合并到setup函数中
  watch(age, (newValue) => {
    console.log("the new age is " + age.value);
  });

  //toRefs 将props中的属性转换为setup函数内可访问的具有响应式效果的对象

  watch(country, (newValue) => {
    console.log("the new country is " + country.value);
  });

  //computed函数可以在setup函数中使用计算属性
  const major = ref("computer");
  const doubleMajor = computed(() => {
    return major.value + major.value;
  });

  return {
    //必须返回 模板中才能使用
    name,
    age,
    plusOne,
    major,
    doubleMajor
  };
}