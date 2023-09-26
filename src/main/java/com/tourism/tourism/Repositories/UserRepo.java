package com.tourism.tourism.Repositories;

import com.tourism.tourism.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.email =:e")
    public User getUserByUsername(@Param("e")  String email);
}
