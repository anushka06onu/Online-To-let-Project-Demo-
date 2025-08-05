package com.otsdemo.onlineToletSystemDemo.service;

import com.otsdemo.onlineToletSystemDemo.model.Flat;
import com.otsdemo.onlineToletSystemDemo.repository.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlatService {

    @Autowired
    FlatRepository flatRepository;


    public List<Flat> showFlatListByGender(String gender) {
        return flatRepository.findByTenantGender(gender);
    }

    public void updateRent(Flat flat) {
        flatRepository.save(flat);
    }

    public Object showFlatById(Long id) {
        return flatRepository.findById(id);
    }
}