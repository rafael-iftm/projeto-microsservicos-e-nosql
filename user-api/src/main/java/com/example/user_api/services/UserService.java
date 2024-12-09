package com.example.user_api.services;

import com.example.user_api.models.User;
import com.example.user_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameContaining(name);
    }
    
    public User editUser(String id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getName() != null && 
            !existingUser.getEmail().equals(user.getEmail())) {
            existingUser.setName(user.getName());
        }
        return userRepository.save(existingUser);
    }

    public User updateUser(String id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setCpf(newUser.getCpf());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
