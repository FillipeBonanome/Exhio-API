package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String username);
    User findByEmail(String email);
}
