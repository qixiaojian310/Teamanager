package com.jian.service;


import com.jian.dao.ModuleDao;
import com.jian.dao.StudentDao;
import com.jian.pojo.Module;
import com.jian.pojo.Student;
import com.jian.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("studentModuleServiceImpl")
public class StudentModuleServiceImpl implements StudentModuleService {


    @Autowired
    @Qualifier("moduleDao")
    private ModuleDao moduleDao;

    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public ModuleDao getModuleDao() {
        return moduleDao;
    }

    public void setModuleDao(ModuleDao moduleDao) {
        this.moduleDao = moduleDao;
    }

    @Override
    //通过指定的studentId来获取所有的module,
    public List<Module> getAllModule(String studentId) {
        List<Module> moduleList =  moduleDao.getModuleStudent(studentId);
        for (Module module : moduleList) {
            Teacher teacher = moduleDao.getTeacherByModuleId(module.getModuleId());
            module.setTeacher(teacher);
            //获得所有的StudentId
            List<String> studentIds = moduleDao.getStudentsByModuleId(module.getModuleId());
            List<Student> studentList = new LinkedList<Student>();
            for (String sid : studentIds) {
                Student student = studentDao.getStduentNoPwd(sid);
                studentList.add(student);
            }
            module.setStudents(studentList);
            module.setTeamIds(moduleDao.getTeamByModule(module.getModuleId()));
        }
        return moduleList;
    }

    @Override
    public List<Module> getAllModuleSearch() {
        List<Module> moduleList = moduleDao.getAllModule();
        for (Module module : moduleList) {
            System.out.println(module);
            Teacher teacher = moduleDao.getTeacherByModuleId(module.getModuleId());
            module.setTeacher(teacher);
            //获得所有的StudentId
            List<String> studentIds = moduleDao.getStudentsByModuleId(module.getModuleId());
            List<Student> studentList = new LinkedList<Student>();
            for (String sid : studentIds) {
                Student student = studentDao.getStduentNoPwd(sid);
                studentList.add(student);
            }
            module.setStudents(studentList);
            module.setTeamIds(moduleDao.getTeamByModule(module.getModuleId()));
        }
        return moduleList;
    }

    @Override
    public boolean joinModule(String studentId, Integer moduleId) {
        return studentDao.addModule(studentId,moduleId);
    }

    @Override
    public boolean deleteStudentInModule(String[] studentIds, int moduleId) {
        for (String studentId : studentIds) {
            boolean status1 = studentDao.deleteStudentInTask(studentId,moduleId);
            boolean status2 = studentDao.deleteStudentInTeam(studentId,moduleId);
            boolean status3 = studentDao.deleteModuleByStudentId(studentId,moduleId);
            if(!status3){
                return false;
            }
        }
        return true;
    }


}
