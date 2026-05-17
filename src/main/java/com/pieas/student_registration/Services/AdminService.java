package com.pieas.student_registration.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pieas.student_registration.Entities.AdminEntity;
import com.pieas.student_registration.Repositories.AdminRepository;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public void addAdmin(AdminEntity admin) {
        adminRepository.insert(admin);

    }

    public void deleteAdmin(String username) {
        adminRepository.deleteByusername(username);
    }

    public List<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }

}
