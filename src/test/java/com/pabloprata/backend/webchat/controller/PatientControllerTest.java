package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.domain.*;
import com.pabloprata.backend.webchat.dto.*;
import com.pabloprata.backend.webchat.repository.*;
import com.pabloprata.backend.webchat.service.MedicalHistoryService;
import com.pabloprata.backend.webchat.service.PatientService;
import com.pabloprata.backend.webchat.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RequiredArgsConstructor
class PatientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PsychologistService psychologistService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private TypeNotificationRepository typeNotificationRepository;

    @Autowired
    private TypeMeetingRepository typeMeetingRepository;

    @Autowired
    private StatusMeetingRepository statusMeetingRepository;

    @Autowired
    private TypeMessageRepository typeMessageRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EducationLevelRepository educationLevelRepository;

    @Autowired
    private ReligionRepository religionRepository;

    @Autowired
    private OccupationRepository occupationRepository;

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private PsychologistRepository psychologistRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @BeforeAll
    void populateDatabase() {

        List<Gender> genders = List.of(new Gender("M"), new Gender("F"), new Gender("O"));
        genderRepository.saveAll(genders);

        List<TypeNotification> typeNotifications = List.of(new TypeNotification("Message"), new TypeNotification("Call"), new TypeNotification("VideoCall"), new TypeNotification("Session"), new TypeNotification("System"), new TypeNotification("Others"));
        typeNotificationRepository.saveAll(typeNotifications);

        List<TypeMeeting> typeMeeting = List.of(new TypeMeeting("Individual"), new TypeMeeting("Grupal"), new TypeMeeting("Emergencial"));
        typeMeetingRepository.saveAll(typeMeeting);

        List<StatusMeeting> statusMeetings = List.of(new StatusMeeting("agendada"), new StatusMeeting("concluída"), new StatusMeeting("cancelada"));
        statusMeetingRepository.saveAll(statusMeetings);

        List<TypeMessage> typeMessages = List.of(new TypeMessage("Text"), new TypeMessage("Image"), new TypeMessage("Audio"), new TypeMessage("Video"));
        typeMessageRepository.saveAll(typeMessages);

        List<Country> countries = List.of(new Country("Brasil"), new Country("Estados Unidos"), new Country("Canadá"), new Country("Portugal"), new Country("Argentina"));
        countryRepository.saveAll(countries);

        List<State> states = List.of(new State("São Paulo", "SP", countries.getFirst()), new State("Rio de Janeiro", "RJ", countries.getFirst()), new State("Minas Gerais", "MG", countries.getFirst()), new State("Bahia", "BA", countries.getFirst()), new State("Paraná", "PR", countries.getFirst()));
        stateRepository.saveAll(states);

        List<City> cities = List.of(new City("São Paulo", states.get(0)), new City("Rio de Janeiro", states.get(1)), new City("Belo Horizonte", states.get(2)), new City("Salvador", states.get(3)), new City("Curitiba", states.get(4)));
        cityRepository.saveAll(cities);

        List<EducationLevel> educationLevels = List.of(new EducationLevel("Ensino Fundamental"), new EducationLevel("Ensino Médio"), new EducationLevel("Graduação"), new EducationLevel("Pós-Graduação"), new EducationLevel("Doutorado"));
        educationLevelRepository.saveAll(educationLevels);

        List<Religion> religions = List.of(new Religion("Catolicismo"), new Religion("Protestantismo"), new Religion("Espiritismo"), new Religion("Ateísmo"), new Religion("Outras"));
        religionRepository.saveAll(religions);

        List<Occupation> occupations = List.of(new Occupation("Professor"), new Occupation("Engenheiro"), new Occupation("Médico"), new Occupation("Advogado"), new Occupation("Psicólogo"));
        occupationRepository.saveAll(occupations);

        List<MaritalStatus> maritalStatuses = List.of(new MaritalStatus("Solteiro(a)"), new MaritalStatus("Casado(a)"), new MaritalStatus("Divorciado(a)"), new MaritalStatus("Viúvo(a)"), new MaritalStatus("União Estável"));
        maritalStatusRepository.saveAll(maritalStatuses);

        PsychologistSignUpDTO psychologistSignUpDTO = new PsychologistSignUpDTO("Valdeci", "Maria", "Santos", "933.632.086-69", "ES/9876", "+5511992480811", "valdeci.alves.1939@gmail.com", "1939-01-07", 2, "password@123");

        PsychologistCreatedDTO psychologistCreated = psychologistService.signup(psychologistSignUpDTO);

        PatientSignUpDTO patientSignUpDTO = new PatientSignUpDTO(psychologistCreated.id(), "Pedro", "Augusto", "Brito", "101.737.648-43", "+552126155590", "pedro_augusto_brito@procivil.com.br", "1961-01-07", 1);

        PatientCreatedDTO patientCreated = patientService.signup(patientSignUpDTO);

        Patient patient = patientRepository.findByUser_Id(patientCreated.id()).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado!"));

        MedicalHistoryDTO medicalHistoryDTO = getMedicalHistoryDTO();

        medicalHistoryService.createMedicalHistory(patient.getUser().getId(), medicalHistoryDTO);
    }

    private static MedicalHistoryDTO getMedicalHistoryDTO() {
        ParentInfoDTO fatherInfoDTO = new ParentInfoDTO("João Mendes", 60, 2, 1, "Muito legal");

        ParentInfoDTO motherInfoDTO = new ParentInfoDTO("Maria Lima", 58, 1, 2, "Muito Chata");

        return new MedicalHistoryDTO(3, 1, 1, 3, fatherInfoDTO, motherInfoDTO);
    }

    @Test
    @DisplayName("Paciente: Deve devolver código http 200 e trazer os detalhes de um usuário cadastrado.")
    @WithMockUser
    @Transactional
    void getPatientDetails_case_01() throws Exception {

        //Primeiro criamos um psicólogo,
        // para poder gerar um paciente,
        // para depois buscar os detalhes
        var psyDTO = new PsychologistSignUpDTO("Allana", "Antonella", "Gomes", "170.999.987-06", "SP/12775", "+5581993941371", "allana-gomes76@jp.ind.br", "1997-01-14", 2, "Senha@123");
        PsychologistCreatedDTO psychologistCreatedDTO = psychologistService.signup(psyDTO);

        var patientDTO = new PatientSignUpDTO(psychologistCreatedDTO.id(), "Liz", "Sara", "Pinto", "310.333.036-74", "+5596997585689", "liz_sara_pinto@ipek.net.br", "1999-07-03", 2);
        PatientCreatedDTO patientCreatedDTO = patientService.signup(patientDTO);

        var response = mvc.perform(get("/patient/" + patientCreatedDTO.id())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

//    @Test
//    void updatePatientStatus_case_01 throws Exception() {
//    }
//
//    @Test
//    void getMedicalHistory_case_01 throws Exception() {
//    }
//
//    @Test
//    void createMedicalHistory_case_01 throws Exception() {
//    }
}