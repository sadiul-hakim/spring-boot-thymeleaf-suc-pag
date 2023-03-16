package com.smartcontact.smartcontactmanager.repositories;

import com.smartcontact.smartcontactmanager.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.email=:email")
    public User findUserByEmail(@Param("email") String email);

    @Query("select u from User u")
    public Page<User> getAllUsers(Pageable pageable);
}
