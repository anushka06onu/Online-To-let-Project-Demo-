package com.otsdemo.onlineToletSystemDemo.service;

import com.otsdemo.onlineToletSystemDemo.model.Flat;
import com.otsdemo.onlineToletSystemDemo.model.HouseOwner;
import com.otsdemo.onlineToletSystemDemo.repository.FlatRepository;
import com.otsdemo.onlineToletSystemDemo.repository.HouseOwnerRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseOwnerService {

    @Autowired
    private HouseOwnerRepository houseOwnerRepository;

    @Autowired
    FlatRepository flatRepository;

    public List<Flat> showFlatList() {
        return flatRepository.findAll();
    }

    public Flat getdetails(Long id) {
        return flatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No id Found"));
    }

    public void addFlat(Flat flat) {
        flatRepository.save(flat);
    }

    public List<HouseOwner> showOwners() {
        return houseOwnerRepository.findAll();
    }

    public Optional<HouseOwner> findById(Long houseOwnerId) {
        return houseOwnerRepository.findById(houseOwnerId);
    }

    public boolean checkEmail(String email){
        return houseOwnerRepository.existsByEmail(email);
    }

    public boolean checkPassword(String password){
        return houseOwnerRepository.existsByPassword(password);
    }

    public List<Flat> shortFlat(String time) {
        return flatRepository.findByRentType(time);
    }
}
