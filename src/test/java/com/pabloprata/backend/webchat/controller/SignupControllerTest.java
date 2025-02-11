package com.pabloprata.backend.webchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pabloprata.backend.webchat.dto.PatientCreatedDTO;
import com.pabloprata.backend.webchat.dto.PatientSignUpDTO;
import com.pabloprata.backend.webchat.dto.PsychologistCreatedDTO;
import com.pabloprata.backend.webchat.dto.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.repository.UserRepository;
import com.pabloprata.backend.webchat.service.PatientService;
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
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PsychologistService psychologistService;

    @Autowired
    private PatientService patientService;

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

        var psyDTO = new PsychologistSignUpDTO("Mirella", "Heloise", "Peixoto", "132.350.148-70", "SP/12345", "+5527984427820", "mirella.heloise.peixoto@origembr.com", "1999-09-24", 2, "Senha@123");

        PsychologistCreatedDTO psychologist = psychologistService.signup(psyDTO);

        var patientDTO = new PatientSignUpDTO(psychologist.id(), "Giovanna", "Jéssica", "Campos", "493.284.993-13", "+5592999884836", "giovanna-campos83@tonyveiculos.com.br", "1985-07-03", 2);

        var response = mvc.perform(post("/signup/patient").contentType(MediaType.APPLICATION_JSON).content(patientSignUpDTOJson.write(patientDTO).getJson())).andReturn().getResponse();

        PatientCreatedDTO actual = new ObjectMapper().readValue(response.getContentAsString(), PatientCreatedDTO.class);

        assertThat(actual.id()).isNotNull();
        assertThat(actual.firstName()).isEqualTo("Giovanna");
        assertThat(actual.lastName()).isEqualTo("Campos");
        assertThat(actual.phoneNumber()).isEqualTo("+5592999884836");
        assertThat(actual.email()).isEqualTo("giovanna-campos83@tonyveiculos.com.br");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }


}