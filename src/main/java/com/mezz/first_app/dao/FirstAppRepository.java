package com.mezz.first_app.dao;


import java.util.List;

import com.mezz.first_app.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface FirstAppRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
    List<User> findByLastName(String lastName);
}
