package com.mapu.backend.service;

import java.util.List;

import com.mapu.backend.exception.UserException;
import com.mapu.backend.model.User;

public interface IUserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

    public User updateUser(Long userId, User user) throws UserException;

    public User followUser(Long userId, User user) throws UserException;

    public List<User> searchUser(String query);
}
