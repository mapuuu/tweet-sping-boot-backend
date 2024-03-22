package com.mapu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mapu.backend.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String username);

    @Query("select distinct u from User u where u.fullName like %:query% or u.email like %:query% ")
    public List<User> searchUser(@Param("query") String query);

}
