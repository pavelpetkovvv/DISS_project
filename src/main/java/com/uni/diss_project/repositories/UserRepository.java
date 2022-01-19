package com.uni.diss_project.repositories;

import com.uni.diss_project.models.MessengerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<MessengerUser, UUID> {

    void deleteByUsername(String username);

    boolean existsByUsername(String username);

    MessengerUser findByUsername(String username);
}
