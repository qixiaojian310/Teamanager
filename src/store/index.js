import { createStore } from 'vuex'

export default createStore({
  state(){
    return{
      windowSize:{
        windowSizeWidth:1000,
        windowSizeHeight:1000
      },
      users:[
        {
          name:'Tom',
          userIconSrc:require("@/assets/icon/Tom.jpg"),
        },
        {
          name:'Jerry',
          userIconSrc:"",
        },
        {
          name:'Mike',
          userIconSrc:"",
        },
        {
          name:'John',
          userIconSrc:require("@/assets/icon/John.jpg"),
        },
        {
          name:'Lucy',
          userIconSrc:require("@/assets/icon/Lucy.jpg"),
        },
        {
          name:'Amy',
          userIconSrc:"",
        },
        {
          name:'Penny',
          userIconSrc:require("@/assets/icon/Penny.jpg"),
        }
      ]
    }
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
