package com.example.user_api.repository;

import com.example.user_api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByCpf(String cpf);
    List<User> findByNameContaining(String name);
}
