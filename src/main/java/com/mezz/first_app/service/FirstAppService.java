package com.mezz.first_app.service;

import java.util.List;

import com.mezz.first_app.entity.User;

public interface FirstAppService {
    List<User> listUsers();
    void addUser(User user);
    void deleteUser(Integer id) throws UserNotFoundException;
    User get(Integer id) throws UserNotFoundException;
    List<User> findByLastName(String lastName);
  
    
}