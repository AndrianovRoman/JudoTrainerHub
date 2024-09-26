package com.example.studioJudo.repositories;

import com.example.studioJudo.dto.UserDto;
import com.example.studioJudo.models.Role;
import com.example.studioJudo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    List<User> findByRole(Role role);

//    @Query("select u from User u join fetch u.roles where u.id = :id")
//    Optional<User> findByIdWithRoles(@Param("id") Integer id);
}
