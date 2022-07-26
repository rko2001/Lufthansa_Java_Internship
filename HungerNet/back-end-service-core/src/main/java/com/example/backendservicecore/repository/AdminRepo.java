package com.example.backendservicecore.repository;

import com.example.backendservicecore.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByUsername(String username);
}
