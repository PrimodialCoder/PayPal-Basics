package com.paypal.user_service.service;

import com.paypal.user_service.entity.User;

import java.util.Optional;
import java.util.List;
public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
}
