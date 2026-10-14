package com.otsdemo.onlineToletSystemDemo;

import com.otsdemo.onlineToletSystemDemo.config.SecurityConfig;
import com.otsdemo.onlineToletSystemDemo.controller.*;
import com.otsdemo.onlineToletSystemDemo.model.*;
import com.otsdemo.onlineToletSystemDemo.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({HouseOwnerController.class,TenantController.class})
@Import(SecurityConfig.class)
class SecurityRepairTest {
    @Autowired MockMvc mvc;
    @MockBean HouseOwnerRepository owners;
    @MockBean FlatRepository flats;
    @MockBean TenantRepository tenants;
    @MockBean UserRepository users;
    @Test void anonymousCannotCreateFlats() throws Exception {
        mvc.perform(post("/houseowner/addFlat").with(csrf())).andExpect(status().is3xxRedirection());
        verify(flats,never()).save(any());
    }
    @Test @WithMockUser(username="owner@example.test",roles="OWNER") void csrfIsRequiredForWrites() throws Exception {
        mvc.perform(post("/houseowner/addFlat")).andExpect(status().isForbidden()); verify(flats,never()).save(any());
    }
    @Test @WithMockUser(roles="TENANT") void tenantCannotUseOwnerWrites() throws Exception {
        mvc.perform(post("/houseowner/addFlat").with(csrf())).andExpect(status().isForbidden()); verify(flats,never()).save(any());
    }
    @Test @WithMockUser(username="owner@example.test",roles="OWNER") void ownerCannotReadAnotherOwnersTenantList() throws Exception {
        HouseOwner mine=new HouseOwner();mine.setId(1L);HouseOwner other=new HouseOwner();other.setId(2L);
        Flat flat=new Flat();flat.setId(3L);flat.setHouseOwner(other);
        when(owners.findByEmail("owner@example.test")).thenReturn(Optional.of(mine));when(flats.findById(3L)).thenReturn(Optional.of(flat));
        mvc.perform(get("/houseowner/3/update")).andExpect(status().isForbidden());verify(tenants,never()).findByFlatId(anyLong());
    }
    @Test void passwordMustBelongToTheSubmittedAccount() throws Exception {
        HouseOwner owner=new HouseOwner();owner.setEmail("owner@example.test");owner.setPassword(new BCryptPasswordEncoder().encode("correct-password"));
        when(owners.findByEmail("owner@example.test")).thenReturn(Optional.of(owner));
        mvc.perform(formLogin("/login").user("owner@example.test").password("another-users-password")).andExpect(unauthenticated());
    }
}
