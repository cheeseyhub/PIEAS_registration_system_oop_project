package com.pieas.student_registration.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pieas.student_registration.Entities.StudentEntity;
import java.util.Optional;
import java.util.List;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {

    Optional<StudentEntity> findByRegistrationNumber(String registrationNumber);

    List<StudentEntity> findByDepartment(String department);
}
