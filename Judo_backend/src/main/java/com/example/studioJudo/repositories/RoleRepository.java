package com.example.studioJudo.repositories;

import com.example.studioJudo.models.Qualification;
import com.example.studioJudo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
