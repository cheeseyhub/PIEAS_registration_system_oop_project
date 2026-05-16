package com.pieas.student_registration.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pieas.student_registration.Entities.DepartmentEntity;

public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {
    void deleteByDepartmentName(String departmentName);

}
