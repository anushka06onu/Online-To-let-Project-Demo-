package com.otsdemo.onlineToletSystemDemo.repository;

import com.otsdemo.onlineToletSystemDemo.model.Tenant;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

   public List<Tenant> findByFlatId(Long id);


    boolean existsByEmail(@NotBlank(message = "Enter a valid email") String userEmail);
}
