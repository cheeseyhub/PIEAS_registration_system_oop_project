package com.pieas.student_registration.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pieas.student_registration.Entities.AdminEntity;

public interface AdminRepository extends MongoRepository<AdminEntity, String> {

    void deleteByAdminId(String adminId);
}
