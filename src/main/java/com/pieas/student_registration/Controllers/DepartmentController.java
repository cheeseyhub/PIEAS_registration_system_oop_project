package com.pieas.student_registration.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pieas.student_registration.Entities.DepartmentEntity;
import com.pieas.student_registration.Services.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public void addDepartment(@RequestBody DepartmentEntity entity) {

        departmentService.addDepartment(entity);
    }

}
