package com.mezz.first_app.service;

import java.util.List;
import java.util.Optional;

import com.mezz.first_app.dao.FirstAppRepository;
import com.mezz.first_app.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstAppServiceImplement implements FirstAppService{

@Autowired private FirstAppRepository repo;
    @Override
    public List<User> listUsers() {
        
        return (List<User>) repo.findAll();
    }

    @Override
    public void addUser(User user) {
        repo.save(user);
    }

    @Override
    public void deleteUser(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if(count == null ||count == 0){
            throw new UserNotFoundException("Could not find any users with ID " + id);  
        }
        repo.deleteById(id);
    }

    @Override
    public User get (Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> users = repo.findByLastName(lastName);
        return users;
    }

    
    
}
