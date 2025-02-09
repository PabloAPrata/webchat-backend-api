package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medical_history")
public class MedicalHistory {


    public MedicalHistory(Patient patient, Occupation patientOccupation, MaritalStatus patientMaritalStatus, Religion patientReligion, EducationLevel patientEducation, String fathersName, Integer fathersAge, EducationLevel fathersEducation, Occupation fathersOccupation, String mothersName, Integer mothersAge, EducationLevel mothersEducation, Occupation mothersOccupation, String fatherNotes, String motherNotes) {
        this.patient = patient;
        this.patientOccupation = patientOccupation;
        this.patientMaritalStatus = patientMaritalStatus;
        this.patientReligion = patientReligion;
        this.patientEducation = patientEducation;
        this.fathersName = fathersName;
        this.fathersAge = fathersAge;
        this.fathersEducation = fathersEducation;
        this.fathersOccupation = fathersOccupation;
        this.mothersName = mothersName;
        this.mothersAge = mothersAge;
        this.mothersEducation = mothersEducation;
        this.mothersOccupation = mothersOccupation;
        this.fatherNotes = fatherNotes;
        this.motherNotes = motherNotes;
    }
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

    @Column(name = "fathers_name")
    private String fathersName;

    @Column(name = "fathers_age")
    private Integer fathersAge;

    @ManyToOne
    @JoinColumn(name = "fk_fathers_education_id")
    private EducationLevel fathersEducation;

    @ManyToOne
    @JoinColumn(name = "fk_fathers_occupation_id")
    private Occupation fathersOccupation;

    @Column(name = "mothers_name")
    private String mothersName;

    @Column(name = "mothers_age")
    private Integer mothersAge;

    @ManyToOne
    @JoinColumn(name = "fk_mothers_education_id")
    private EducationLevel mothersEducation;

    @ManyToOne
    @JoinColumn(name = "fk_mothers_occupation_id")
    private Occupation mothersOccupation;

    @Column(name = "father_notes")
    private String fatherNotes;

    @Column(name = "mother_notes")
    private String motherNotes;


}
