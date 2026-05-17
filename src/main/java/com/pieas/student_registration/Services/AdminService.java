package com.pieas.student_registration.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieas.student_registration.Entities.AdminEntity;
import com.pieas.student_registration.Repositories.AdminRepository;

public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public void addAdmin(AdminEntity admin) {
        adminRepository.insert(admin);

    }

    public void deleteAdmin(String username) {
        adminRepository.deleteByadminUsername(username);
    }

    public List<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }

}
