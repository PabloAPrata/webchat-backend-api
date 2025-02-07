package com.pabloprata.backend.webchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pabloprata.backend.webchat.dto.PatientCreatedDTO;
import com.pabloprata.backend.webchat.dto.PatientSignUpDTO;
import com.pabloprata.backend.webchat.dto.PsychologistCreatedDTO;
import com.pabloprata.backend.webchat.dto.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.service.PsychologistService;
import org.junit.jupiter.api.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignupControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<PsychologistSignUpDTO> psychologistSignUpDTOJson;

    @Autowired
    private JacksonTester<PsychologistCreatedDTO> psychologistCreatedDTOJson;

    @Autowired
    private JacksonTester<PatientSignUpDTO> patientSignUpDTOJson;

    @Autowired
    private JacksonTester<PatientCreatedDTO> patientCreatedDTOJson;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    void populateDatabase() {
        jdbcTemplate.execute("INSERT INTO gender (name) VALUES ('M'), ('F'), ('O');" + "INSERT INTO type_notification (name) VALUES ('Message'), ('Call'), ('VideoCall'), ('Session'), ('System'), ('Others');" + "INSERT INTO type_meeting (name) VALUES ('Individual'), ('Grupal'), ('Emergencial');" + "INSERT INTO status_meeting (name) VALUES ('agendada'), ('concluída'), ('cancelada');" + "INSERT INTO type_message (name) VALUES ('Text'), ('Image'), ('Document'), ('Audio'), ('Video');");
    }

    @Autowired
    private PsychologistService psychologistService;

    @Test
    @DisplayName("Psicólogo: Deve devolver código http 400 quando não possui body.")
    @WithMockUser
    void createPsychologist_case_01() throws Exception {
        var response = mvc.perform(post("/signup/psychologist")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Psicólogo: Deve devolver código http 201 quando as informações estão válidas e cadastro é bem sucedido")
    @WithMockUser
    @Transactional
    void createPsychologist_case_02() throws Exception {
        var response = mvc.perform(post("/signup/psychologist").contentType(MediaType.APPLICATION_JSON).content(psychologistSignUpDTOJson.write(new PsychologistSignUpDTO("Mirella", "Heloise", "Peixoto", "124.006.043-20", "SP/12345", "+5527984427820", "mirella.heloise.peixoto@origembr.com", "1999-09-24", 2, "Senha@123")).getJson())).andReturn().getResponse();

        PsychologistCreatedDTO actual = new ObjectMapper().readValue(response.getContentAsString(), PsychologistCreatedDTO.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actual.firstName()).isEqualTo("Mirella");
        assertThat(actual.lastName()).isEqualTo("Peixoto");
        assertThat(actual.crp()).isEqualTo("SP/12345");
        assertThat(actual.phoneNumber()).isEqualTo("+5527984427820");
        assertThat(actual.email()).isEqualTo("mirella.heloise.peixoto@origembr.com");
        assertThat(actual.id()).isNotNull();


    }

    @Test
    @DisplayName("Paciente: Deve devolver código http 400 quando não possui body.")
    @WithMockUser
    void createPatient_case_01() throws Exception {
        var response = mvc.perform(post("/signup/patient")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Paciente: Deve devolver código http 201 quando as informações estão válidas e cadastro é bem sucedido")
    @WithMockUser
    @Transactional
    void createPatient_case_02() throws Exception {

        var psyDTO = new PsychologistSignUpDTO("Mirella", "Heloise", "Peixoto", "124.006.043-20", "SP/12345", "+5527984427820", "mirella.heloise.peixoto@origembr.com", "1999-09-24", 2, "Senha@123");

        PsychologistCreatedDTO psychologist = psychologistService.signup(psyDTO);

        var response = mvc.perform(post("/signup/patient").contentType(MediaType.APPLICATION_JSON).content(patientSignUpDTOJson.write(new PatientSignUpDTO(psychologist.id(), "Giovana", "Rosa", "Barbosa", "101.737.648-43", "+5568994328069", "giovana.rosa.barbosa@infouai.com", "1999-09-24", 2)).getJson())).andReturn().getResponse();

        PatientCreatedDTO actual = new ObjectMapper().readValue(response.getContentAsString(), PatientCreatedDTO.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actual.id()).isNotNull();
        assertThat(actual.firstName()).isEqualTo("Giovana");
        assertThat(actual.lastName()).isEqualTo("Barbosa");
        assertThat(actual.phoneNumber()).isEqualTo("+5568994328069");
        assertThat(actual.email()).isEqualTo("giovana.rosa.barbosa@infouai.com");
    }


}