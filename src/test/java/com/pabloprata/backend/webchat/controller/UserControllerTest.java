package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.dto.PsychologistCreatedDTO;
import com.pabloprata.backend.webchat.dto.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.dto.UpdateUserDTO;
import com.pabloprata.backend.webchat.service.PsychologistService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UpdateUserDTO> updateUserDTOJson;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PsychologistService psychologistService;

    @Test
    @DisplayName("Usuário: Deve devolver 204 após atualizar o usuário")
    @WithMockUser
    @Transactional
    void updateUser() throws Exception {

        var psyDTO = new PsychologistSignUpDTO("Mirella Heloise Peixoto", "124.006.043-20", "SP/12345", "+5527984427820", "mirella.heloise.peixoto@origembr.com", "1999-09-24", 2, "Senha@123");

        PsychologistCreatedDTO psychologist = psychologistService.signup(psyDTO);

        var response = mvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON).content(updateUserDTOJson.write(new UpdateUserDTO(
               psychologist.id(),
               "Pablo Almeida Prata",
                null,
                "1999-09-28",
                "+5511992480831",
                "133.158.337-37",
                "pabloalmeidaprata@hotmail.com"

        )).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }


}