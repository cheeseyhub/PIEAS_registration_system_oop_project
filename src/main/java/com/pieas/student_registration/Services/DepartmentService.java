package com.pieas.student_registration.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Repositories.DepartmentRepository;

public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
