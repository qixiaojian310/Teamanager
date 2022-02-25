package com.jian.service;

import com.jian.pojo.Module;

import java.util.List;

public interface StudentModuleService {
    public List<Module> getAllModule(String studentId);
}
