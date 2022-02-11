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
          ID:'12345',
        },
        {
          name:'Jerry',
          userIconSrc:"",
          ID:'34567',
        },
        {
          name:'Mike',
          userIconSrc:"",
          ID:'34785',
        },
        {
          name:'John',
          userIconSrc:require("@/assets/icon/John.jpg"),
          ID:'33245',
        },
        {
          name:'Lucy',
          userIconSrc:require("@/assets/icon/Lucy.jpg"),
          ID:'12645',
        },
        {
          name:'Amy',
          userIconSrc:"",
          ID:'34895',
        },
        {
          name:'Penny',
          userIconSrc:require("@/assets/icon/Penny.jpg"),
          ID:'34580',
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
