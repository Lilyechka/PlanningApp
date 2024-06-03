package com.liliia.service;

import com.liliia.model.Task;
import com.liliia.model.User;
import com.liliia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

   /* public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
*/
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

