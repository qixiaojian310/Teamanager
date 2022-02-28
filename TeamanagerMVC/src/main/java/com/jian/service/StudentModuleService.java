package com.jian.service;

import com.jian.pojo.Module;

import java.util.List;

public interface StudentModuleService {
    public List<Module> getAllModule(String studentId);

    public List<Module> getAllModuleSearch();

    public boolean joinModule(String studentId, Integer moduleId);

    public boolean deleteStudentInModule(String[] studentIds,int moduleId);
}
