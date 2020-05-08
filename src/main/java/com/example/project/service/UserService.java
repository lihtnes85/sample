package com.example.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers()
    {
        List<User> userList = userRepository.findAll();

        if(userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<>();
        }
    }

    public User getUserById(Integer id) throws Exception
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }

    public User createOrUpdateUser(User user) throws Exception
    {
        user = userRepository.save(user);
        return user;
    }

    public void deleteUserById(Integer id) throws Exception
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
        {
            userRepository.deleteById(id);
        }
    }

}
