package com.example.userapi.controller;

import com.example.userapi.dto.UserDTO;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    // Lista estática de usuários para fins de demonstração
    public static List<UserDTO> usuarios = new ArrayList<>();

    // Inicializa a lista de usuários após a criação do bean
    @PostConstruct
    public void initiateList() {
        UserDTO user1 = new UserDTO("Eduardo", "123", "Rua a", "eduardo@email.com", "1234-3454", LocalDateTime.now());
        UserDTO user2 = new UserDTO("Luiz", "456", "Rua b", "luiz@email.com", "1234-3454", LocalDateTime.now());
        UserDTO user3 = new UserDTO("Bruna", "678", "Rua c", "bruna@email.com", "1234-3454", LocalDateTime.now());
        
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
    }

    // Retorna a lista de usuários cadastrados
    @GetMapping
    public List<UserDTO> getUsers() {
        return usuarios;
    }
}
