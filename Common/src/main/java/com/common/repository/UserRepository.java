package com.common.repository;

import com.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Override
    Optional<User> findById(Long id);

    @Query(value = "select * from user u join user_roles ur on ur.role_id = u.id join roles r on r.id = ur.role_id where r.name=:role",nativeQuery = true)
    List<User> getAllUsers(@Param("role") String role);

}
