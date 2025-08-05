package com.otsdemo.onlineToletSystemDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flatNo;
    private int rent;
    private String rentType;
    private int noOfRoom;
    private String availableDate;
    private boolean available;
    private String contactNumber;
    private String description;
    private String tenantGender;
    private String address;
    private String ownername;

    @ManyToOne
    @JoinColumn(name = "houseOwner_id", nullable = false)
    private HouseOwner houseOwner;

    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL)
    private List<Tenant> tenants;
}
