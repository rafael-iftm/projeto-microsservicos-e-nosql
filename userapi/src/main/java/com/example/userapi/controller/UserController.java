package com.example.userapi.controller;

import com.example.userapi.dto.UserDTO;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/user")
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<>();

    @PostConstruct
    public void initiateList() {
        UserDTO user1 = new UserDTO("Eduardo", "123", "Rua a", "eduardo@email.com", "1234-3454", LocalDateTime.now());
        UserDTO user2 = new UserDTO("Luiz", "456", "Rua b", "luiz@email.com", "1234-3454", LocalDateTime.now());
        UserDTO user3 = new UserDTO("Bruna", "678", "Rua c", "bruna@email.com", "1234-3454", LocalDateTime.now());
        
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return usuarios;
    }

    @GetMapping("/{cpf}")
    public UserDTO getUserByCpf(@PathVariable String cpf) {
        return usuarios
                .stream()
                .filter(userDTO -> userDTO.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody @Valid UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        return userDTO;
    }

    @DeleteMapping("/{cpf}")
    public boolean deleteUser(@PathVariable String cpf) {
        return usuarios
            .removeIf(userDTO -> userDTO.getCpf().equals(cpf));
    }
}
