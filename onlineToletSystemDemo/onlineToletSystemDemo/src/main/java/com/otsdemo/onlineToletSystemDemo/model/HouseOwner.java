package com.otsdemo.onlineToletSystemDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class HouseOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Enter your name")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "Enter your email")
    private String email;
    @Column(unique = true)
    @NotBlank(message = "Enter your phone number")
    private String phoneNumber;
    @NotBlank(message = "Enter a strong password")
    private String password;

    @OneToMany(mappedBy = "houseOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flat> flat;

    public HouseOwner() {
    }

    public HouseOwner(Long id, String name, String email, String phoneNumber, String password, List<Flat> flat) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.flat = flat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Flat> getFlat() {
        return flat;
    }

    public void setFlat(List<Flat> flat) {
        this.flat = flat;
    }
}
