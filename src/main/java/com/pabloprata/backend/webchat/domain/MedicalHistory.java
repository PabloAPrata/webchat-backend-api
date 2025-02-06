package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @Column(name = "fk_patient_id")
    private Integer patientId;

    @OneToOne
    @JoinColumn(name = "fk_patient_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "fk_patient_occupation_id")
    private Occupation patientOccupation;

    @ManyToOne
    @JoinColumn(name = "fk_patient_marital_id")
    private MaritalStatus patientMaritalStatus;

    @ManyToOne
    @JoinColumn(name = "fk_patient_religion_id")
    private Religion patientReligion;

    @ManyToOne
    @JoinColumn(name = "fk_patient_education_id")
    private EducationLevel patientEducation;

    private String fathersName;
    private Integer fathersAge;

    @ManyToOne
    @JoinColumn(name = "fk_fathers_education_id")
    private EducationLevel fathersEducation;

    @ManyToOne
    @JoinColumn(name = "fk_fathers_occupation_id")
    private Occupation fathersOccupation;

    private String mothersName;
    private Integer mothersAge;

    @ManyToOne
    @JoinColumn(name = "fk_mothers_education_id")
    private EducationLevel mothersEducation;

    @ManyToOne
    @JoinColumn(name = "fk_mothers_occupation_id")
    private Occupation mothersOccupation;

    private String fatherNotes;
    private String motherNotes;

}
