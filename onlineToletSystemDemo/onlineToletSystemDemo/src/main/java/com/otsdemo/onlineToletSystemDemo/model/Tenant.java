package com.otsdemo.onlineToletSystemDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Enter Full Name")
    private String name;
    @NotBlank(message = "Enter Your Phone Number")
    @Size(min = 11, max = 11, message = "Enter correct phone number")
    private String contactNumber;
    @ValidDiuEmail(message = "Enter Valid DIU email")
    private String email;//@diu.edu.bd
    private LocalDate moveInDate;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    public Tenant() {
    }

    public Tenant(Long id, String name, String contactNumber, String email, LocalDate moveInDate, Flat flat) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.moveInDate = moveInDate;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(LocalDate moveInDate) {
        this.moveInDate = moveInDate;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }
}
