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
      teams: [],
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
      teamSwiper:{}
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
    deleteTeacherModule(state,moduleId){
      var deleteModuleIndex = state.signInTeacherModule.findIndex((item)=>{
        return item.moduleId == moduleId;
      })
      state.signInTeacherModule.splice(deleteModuleIndex,1);
    },
    deleteTeacherTeam(state,payload){
      var deleteTeamIndex = state.teams.findIndex((item)=>{
        return item.teamId == payload.teamId;
      })
      state.teams.splice(deleteTeamIndex,1);
      var deleteModuleIndex = state.signInTeacherModule.findIndex((item)=>{
        return item.moduleId == payload.moduleId;
      })
      var deleteModuleTeamIndex = state.signInTeacherModule[deleteModuleIndex].teamIds.findIndex((item)=>{
        return item == payload.teamId;
      })
      state.signInTeacherModule[deleteModuleIndex].teamIds.splice(deleteModuleTeamIndex,1);
    },
    updateRememberCookie(state,rememberCookie){
      state.rememberCookie = rememberCookie;
    },
    updateTeamSwiper(state,teamSwiper){
      state.teamSwiper = teamSwiper;
    },
    changeModuleName(state,payload){
      var focusModuleIndex = state.signInTeacherModule.findIndex((item)=>{
        return item.moduleId == payload.moduleId;
      })
      state.signInTeacherModule[focusModuleIndex].moduleName = payload.moduleName;
    }
  },
  actions: {},
  modules: {}
})