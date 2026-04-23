package com.pieas.student_registration.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pieas.student_registration.Entities.StudentEntity;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {

}
