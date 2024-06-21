package com.planosaude.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.planosaude.entity.BeneficiarioEntity;
import com.planosaude.impl.CrudRegisterImpl;

@WebMvcTest(CrudRegistersController.class)
public class CrudRegistersControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrudRegisterImpl service;

    @InjectMocks
    private CrudRegistersController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllBeneficiariosSuccess() throws Exception {
        BeneficiarioEntity beneficiario1 = new BeneficiarioEntity();
        beneficiario1.setId(1);
        beneficiario1.setNome("Miguel");

        BeneficiarioEntity beneficiario2 = new BeneficiarioEntity();
        beneficiario2.setId(2);
        beneficiario2.setNome("Ro");

        List<BeneficiarioEntity> allBeneficiarios = Arrays.asList(beneficiario1, beneficiario2);

        when(service.getAll()).thenReturn(allBeneficiarios);

        mockMvc.perform(get("/manage/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Miguel"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Ro"));
    }

    @Test
    public void testGetAllBeneficiariosInternalServerError() throws Exception {
        when(service.getAll()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/manage/all"))
                .andExpect(status().isInternalServerError());
    }
}
