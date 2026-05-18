package com.pieas.student_registration.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pieas.student_registration.Entities.CourseEntity;

public interface CourseRepository extends MongoRepository<CourseEntity, String> {
    List<CourseEntity> findBydegreeProgram(String degreeProgram);

}
