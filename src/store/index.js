import {
  createStore
} from 'vuex'

export default createStore({
  state() {
    return {
      windowSize: {
        windowSizeWidth: 1000,
        windowSizeHeight: 1000,
      },
      teams: [
        // {
        //   name: "SSM Team",
        //   // id是组的id
        //   id: 1,
        //   leaderId: 1,
        //   studentIds: [
        //     1, 2
        //   ],
        //   available: 2
        // }, {
        //   name: "VUE team",
        //   id: 2,
        //   leaderId: 3,
        //   studentIds: [
        //     3, 5
        //   ],
        //   available: 2
        // }, {
        //   name: "JS team",
        //   id: 3,
        //   leaderId: 6,
        //   studentIds: [
        //     4, 6, 7
        //   ],
        //   available: 2
        // }
      ],
      users: [{
          name: 'Tom',
          userIconSrc: require("@/assets/icon/Tom.jpg"),
          studentId: 1,
          Infor: 'astudentastudentastudentastudentastudentastudentastudentastudentastudentastudent'
        },
        {
          name: 'Jerry',
          userIconSrc: "",
          studentId: 2,
          Infor: 'astudentastudentastudentastudentastudentastudentastudentastudentastudentastudent'

        },
        {
          name: 'Mike',
          userIconSrc: "",
          studentId: 3,
          Infor: ""
        },
        {
          name: 'John',
          userIconSrc: require("@/assets/icon/John.jpg"),
          studentId: 4,
          Infor: 'astudentastudentastudentastudentastudentastudentastudentastudentastudentastudent'
        },
        {
          name: 'Lucy',
          userIconSrc: require("@/assets/icon/Lucy.jpg"),
          studentId: 5,
          Infor: ''

        },
        {
          name: 'Amy',
          userIconSrc: "",
          studentId: 6,
          Infor: 'astudentastudentastudentastudentastudentastudentastudentastudentastudentastudent'

        },
        {
          name: 'Penny',
          userIconSrc: require("@/assets/icon/Penny.jpg"),
          studentId: 7,
          Infor: 'astudentastudentastudentastudentastudentastudentastudentastudentastudentastudent'
        },
      ],
      teacher: [{
          teacherId: 1,
          teacherName: "Amy",
        },
        {
          teacherId: 2,
          teacherName: "Alice"
        }
      ],
      signInStudentModule: [
        // {
        //   moduleId: 1,
        //   moduleName: "Module 1",
        //   teacherId: 1,
        //   studentIdList: [1, 2, 3, 4, 5, 6, 7],
        //   teamIds: [1, 2, 3],
        // },
        // {
        //   moduleId: 2,
        //   moduleName: "Module 2",
        //   teacherId: 2,
        //   studentIdList: [1, 2, 3, 4, 5, 6, 7],
        //   teamIds: [1, 2, 3],
        // },
        // {
        //   moduleId: 3,
        //   moduleName: "Module 3",
        //   teacherId: 1,
        //   studentIdList: [1, 2, 3, 4, 5, 6, 7],
        //   teamIds: [1, 2, 3],
        // },
        // {
        //   moduleId: 5,
        //   moduleName: "Module 5",
        //   teacherId: 2,
        //   studentIdList: [1, 2, 3, 4, 5, 6, 7],
        //   teamIds: [1, 2, 3],
        // },
        // {
        //   moduleId: 6,
        //   moduleName: "Module 6",
        //   teacherId: 1,
        //   studentIdList: [1, 2, 3, 4, 5, 6, 7],
        //   teamIds: [1, 2, 3],
        // },
        // {
        //   moduleId: 7,
        //   moduleName: "Module 7",
        //   teacherId: 1,
        //   studentIdList: [1, 2, 3, 4, 5, 6, 7],
        //   teamIds: [1, 2, 3],
        // },
      ],
      signInTeacherModule: [{
          moduleId: 1,
          moduleName: "Module 1",
          teacherId: 1,
          studentIdList: [1, 2, 3, 4, 5, 6, 7],
          teamIds: [1, 2, 3],
        },
        {
          moduleId: 3,
          moduleName: "Module 3",
          teacherId: 1,
          studentIdList: [1, 2, 3, 4, 5, 6, 7],
          teamIds: [1, 2, 3],
        },
        {
          moduleId: 6,
          moduleName: "Module 6",
          teacherId: 1,
          studentIdList: [1, 2, 3, 4, 5, 6, 7],
          teamIds: [1, 2, 3],
        },
        {
          moduleId: 7,
          moduleName: "Module 7",
          teacherId: 1,
          studentIdList: [1, 2, 3, 4, 5, 6, 7],
          teamIds: [1, 2, 3],
        },
      ],
      signInTeacher: {
        teacherId: 1,
        teacherName: "Amy",
      },
      signInStudent:{
        name:"aaababy",
        info:"",
        userIconSrc:""
      },
      role:""
    }
  },
  getters: {},
  mutations: {
    updateSignInStudentName(state,name){
      state.signInStudent.name = name;
    },
    //用来更行签到的学生信息（info）
    updateSignInStudentInfo(state,name){
      state.signInStudent.info = name;
    },
    updateSignInStudentUserIcon(state,name){
      state.signInStudent.userIconSrc = name;
    },
    selectRole(state,role){
      state.role = role;
    }
  },
  actions: {},
  modules: {}
})