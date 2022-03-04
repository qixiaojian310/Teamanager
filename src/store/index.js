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
        //NOTE signInStudentModule接口 Interface moduleName moduleId teacher students teamIds teamNum moduleSize
      ],
      signInTeacherModule: [
        // {
        //   moduleId: 1,
        //   moduleName: "Module 1",
        //   teacherId: 1,
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
      //TODO need fix
      signInTeacher: {
        name:"5678"
      },
      signInStudent:{
        name:"",
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
    updateSignInTeacherName(state,name){
      state.signInTeacher.name = name;
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
    },
    pushTeacherModule(state,tempModuleObj){
      state.signInTeacherModule.push(tempModuleObj);
    },
    pushTeacherTeams(state,tempTeamsObj){
      state.teams.push(tempTeamsObj);
    },
    pushStudentTeams(state,tempTeamsObj){
      state.teams.push(tempTeamsObj);
    },
    pushStudentModule(state,tempModuleObj){
      state.signInStudentModule.push(tempModuleObj);
    },
    // 传入课程的id和要删除的studentIds
    deleteModuleStudents(state,moduleId,studentIds){
      var focusModuleIndex = state.signInStudentModule.findIndex((item)=>{
        return item.moduleId == moduleId;
      })
      for (let index = 0; index < state.signInTeacherModule[focusModuleIndex].students.length; index++) {
        for (let i = 0; i < studentIds.length; i++) {
          if (state.signInTeacherModule[focusModuleIndex].students[index].studentId == studentIds[i]) {
            state.signInTeacherModule[focusModuleIndex].students.splice(index, 1);
          }
        }
      }
    }
  },
  actions: {},
  modules: {}
})