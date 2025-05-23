package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "fk_patient_occupation_id", referencedColumnName = "id", nullable = false)
    private Occupation patientOccupation;

    @ManyToOne
    @JoinColumn(name = "fk_patient_marital_id", referencedColumnName = "id", nullable = false)
    private MaritalStatus patientMaritalStatus;

    @ManyToOne
    @JoinColumn(name = "fk_patient_religion_id", referencedColumnName = "id", nullable = false)
    private Religion patientReligion;

    @ManyToOne
    @JoinColumn(name = "fk_patient_education_id", referencedColumnName = "id", nullable = false)
    private EducationLevel patientEducation;

    @ManyToOne
    @JoinColumn(name = "fk_fathers_education_id", referencedColumnName = "id")
    private EducationLevel fathersEducation;

    @ManyToOne
    @JoinColumn(name = "fk_fathers_occupation_id", referencedColumnName = "id")
    private Occupation fathersOccupation;

    @ManyToOne
    @JoinColumn(name = "fk_mothers_education_id", referencedColumnName = "id")
    private EducationLevel mothersEducation;

    @ManyToOne
    @JoinColumn(name = "fk_mothers_occupation_id", referencedColumnName = "id")
    private Occupation mothersOccupation;

    @Column(name = "fathers_name")
    private String fathersName;

    @Column(name = "fathers_age")
    private Integer fathersAge;

    @Column(name = "mothers_name")
    private String mothersName;

    @Column(name = "mothers_age")
    private Integer mothersAge;

    @Column(name = "parents_notes")
    private String parentsNotes;
}
