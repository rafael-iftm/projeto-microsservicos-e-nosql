package com.iftm.startexample.models.dto;

import java.io.Serializable;

import com.iftm.startexample.models.User;
import com.iftm.startexample.models.Address;

public class UserDTO implements Serializable {
    private String id;
    private String name;
    private int age;
    private Address address;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.age = user.getAge();
        this.address = user.getAddress();
    }

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    // Getters and Setters
    // equals and hashcode
    // toString
    
}
