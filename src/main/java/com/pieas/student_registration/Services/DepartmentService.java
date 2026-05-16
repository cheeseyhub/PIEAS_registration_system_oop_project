package com.pieas.student_registration.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Repositories.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public void addDepartment(DepartmentEntity department) {
        departmentRepository.insert(department);
    }

    public void deleteDepartment(String departmentName) {
        departmentRepository.deledeleteByDepartmentName(departmentName);
    }
}
