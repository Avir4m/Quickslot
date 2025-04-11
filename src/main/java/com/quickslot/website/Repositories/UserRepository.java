package com.quickslot.website.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quickslot.website.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}