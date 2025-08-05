package com.otsdemo.onlineToletSystemDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    //private Long flatid;
    private LocalDate moveInDate;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;


}
