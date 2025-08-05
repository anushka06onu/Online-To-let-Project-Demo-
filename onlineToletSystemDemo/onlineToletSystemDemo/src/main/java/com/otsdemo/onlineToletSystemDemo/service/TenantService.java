package com.otsdemo.onlineToletSystemDemo.service;

import com.otsdemo.onlineToletSystemDemo.controller.TenantController;
import com.otsdemo.onlineToletSystemDemo.model.Tenant;
import com.otsdemo.onlineToletSystemDemo.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public void addtenant(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    public List<Tenant> showFlatTenat(Long id) {
        return tenantRepository.findByFlatId(id);
    }

    public void deleteTenant(Long tenantId) {
        Tenant tenant =  tenantRepository.findById(tenantId).orElseThrow(()
                -> new RuntimeException("Tenant Not Found"));
        tenantRepository.deleteById(tenant.getId());
    }
}
