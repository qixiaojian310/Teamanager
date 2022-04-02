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
      ],
      //TODO need fix
      signInTeacher: {
        name:""
      },
      signInStudent:{
        name:"",
        info:"",
        userIconSrc:""
      },
      role:"",
      rememberCookie:true,
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
    injectTeacherModules(state,tempModuleObjs){
      state.signInTeacherModule = tempModuleObjs;
    },
    injectStudentModules(state,tempModuleObjs){
      state.signInStudentModule = tempModuleObjs;
    },
    clearTeams(state){
      //将数据置空
      state.teams = [];
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
    // 提交两个东西的时候必须要用对象的方式提交
    deleteModuleStudents(state,payload){
      var focusModuleIndex = state.signInTeacherModule.findIndex((item)=>{
        return item.moduleId == payload.moduleId;
      })
      for (let index = 0; index < state.signInTeacherModule[focusModuleIndex].students.length; index++) {
        for (let i = 0; i < payload.studentIdList.length; i++) {
          if (state.signInTeacherModule[focusModuleIndex].students[index].studentId == payload.studentIdList[i]) {
            state.signInTeacherModule[focusModuleIndex].students.splice(index, 1);
          }
        }
      }
    },
    updateRememberCookie(state,rememberCookie){
      state.rememberCookie = rememberCookie;
    }
  },
  actions: {},
  modules: {}
})