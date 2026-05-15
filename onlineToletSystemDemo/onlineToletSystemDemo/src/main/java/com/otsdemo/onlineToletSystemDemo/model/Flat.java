package com.otsdemo.onlineToletSystemDemo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    public Flat() {
    }

    public Flat(Long id, String flatNo, int rent, String rentType, int noOfRoom, String availableDate, boolean available, String contactNumber, String description, String tenantGender, String address, String ownername, HouseOwner houseOwner, List<Tenant> tenants) {
        this.id = id;
        this.flatNo = flatNo;
        this.rent = rent;
        this.rentType = rentType;
        this.noOfRoom = noOfRoom;
        this.availableDate = availableDate;
        this.available = available;
        this.contactNumber = contactNumber;
        this.description = description;
        this.tenantGender = tenantGender;
        this.address = address;
        this.ownername = ownername;
        this.houseOwner = houseOwner;
        this.tenants = tenants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public int getNoOfRoom() {
        return noOfRoom;
    }

    public void setNoOfRoom(int noOfRoom) {
        this.noOfRoom = noOfRoom;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenantGender() {
        return tenantGender;
    }

    public void setTenantGender(String tenantGender) {
        this.tenantGender = tenantGender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }
}
