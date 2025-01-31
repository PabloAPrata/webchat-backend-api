-- Habilitar extensão para geração de UUIDs
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE gender (
    gender_id SERIAL PRIMARY KEY,
    name CHAR(1) UNIQUE NOT NULL
);

CREATE TABLE type_notification (
    type_notification_id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE type_meeting (
    type_meeting_id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE status_meeting (
    status_meeting_id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE type_message (
    type_message_id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE countries (
    country_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE states (
    state_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    uf CHAR(2) NOT NULL,
    fk_country_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_states_country FOREIGN KEY (fk_country_id) REFERENCES countries(country_id) ON DELETE CASCADE
);

CREATE INDEX idx_states_country_id ON states(fk_country_id);

CREATE TABLE cities (
    city_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    fk_state_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cities_state FOREIGN KEY (fk_state_id) REFERENCES states(state_id) ON DELETE CASCADE
);

CREATE INDEX idx_cities_state_id ON cities(fk_state_id);

CREATE TABLE users (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL CHECK (cpf ~ '^\d{3}\.\d{3}\.\d{3}-\d{2}$'),
    telephone VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    profile_img VARCHAR(255),
    date_birth DATE,
    fk_gender_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_users_gender FOREIGN KEY (fk_gender_id) REFERENCES gender(gender_id) ON DELETE RESTRICT
);

CREATE INDEX idx_users_name ON users(name);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_cpf ON users(cpf);

CREATE TABLE addresses (
    address_id SERIAL PRIMARY KEY,
    fk_user_id UUID NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement VARCHAR(100),
    district VARCHAR(100) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    fk_city_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_addresses_user FOREIGN KEY (fk_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_addresses_city FOREIGN KEY (fk_city_id) REFERENCES cities(city_id) ON DELETE CASCADE
);

CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE permissions (
    permission_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_role (
    fk_user_id UUID,
    fk_role_id INT,
    PRIMARY KEY (fk_user_id, fk_role_id),
    FOREIGN KEY (fk_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);

CREATE TABLE role_permission (
    fk_role_id INT,
    fk_permission_id INT,
    PRIMARY KEY (fk_role_id, fk_permission_id),
    FOREIGN KEY (fk_role_id) REFERENCES roles(role_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_permission_id) REFERENCES permissions(permission_id) ON DELETE CASCADE
);

CREATE TABLE psychologists (
    psychologist_id SERIAL PRIMARY KEY,
    fk_user_id UUID UNIQUE NOT NULL,
    crp VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_psychologists_user FOREIGN KEY (fk_user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE patients (
    patient_id SERIAL PRIMARY KEY,
    fk_user_id UUID UNIQUE NOT NULL,
    fk_psychologist_id INT NOT NULL,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_patients_user FOREIGN KEY (fk_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_patients_psychologist FOREIGN KEY (fk_psychologist_id) REFERENCES psychologists(psychologist_id) ON DELETE CASCADE
);

CREATE TABLE education_levels (
    education_id SERIAL PRIMARY KEY,
    description VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE religions (
    religion_id SERIAL PRIMARY KEY,
    description	VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE occupations (
    occupation_id SERIAL PRIMARY KEY,
    description	VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE marital_statuses (
    marital_id SERIAL PRIMARY KEY,
    description	VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE medical_history (
    medical_history_id SERIAL PRIMARY KEY,
    fk_patient_id INT,
    fk_patient_occupation_id INT,
    fk_patient_marital_id INT,
    fk_patient_religion_id INT,
    fk_patient_education_id INT,
    fathers_name VARCHAR(100),
    fathers_age INT,
    fk_fathers_education_id INT,
    fk_fathers_occupation_id INT,
    mothers_name VARCHAR(100),
    mothers_age INT,
    fk_mothers_education_id INT,
    fk_mothers_occupation_id INT,
    parents_notes VARCHAR(255),
    FOREIGN KEY (fk_patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_patient_occupation_id) REFERENCES occupations(occupation_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_patient_marital_id) REFERENCES marital_statuses(marital_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_patient_religion_id) REFERENCES religions(religion_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_patient_education_id) REFERENCES education_levels(education_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_fathers_education_id) REFERENCES education_levels(education_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_fathers_occupation_id) REFERENCES occupations(occupation_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_mothers_occupation_id) REFERENCES occupations(occupation_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_mothers_education_id) REFERENCES education_levels(education_id) ON DELETE CASCADE
);

CREATE TABLE medical_history_topics (
    history_topic_id SERIAL PRIMARY KEY,
    fk_medical_history_id	INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    date_register TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_medical_history_id) REFERENCES medical_history(medical_history_id) ON DELETE CASCADE
);

-- Criação de uma tabela unificada para os elementos do diagrama de conceito

CREATE TABLE concept_diagram (
    concept_diagram_id SERIAL PRIMARY KEY,
    core_beliefs VARCHAR(255),
    intermediate_beliefs VARCHAR(255),
    compensatory_strategies VARCHAR(255),
    date_register TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE concept_elements (
    concept_element_id SERIAL PRIMARY KEY,
    fk_concept_diagram_id INT NOT NULL,
    element_type VARCHAR(50) NOT NULL CHECK (element_type IN ('situation', 'thought', 'meaning_pa', 'emotion', 'behavior')),
    description TEXT NOT NULL,
    FOREIGN KEY (fk_concept_diagram_id) REFERENCES concept_diagram(concept_diagram_id) ON DELETE CASCADE
);

CREATE TABLE meetings (
    meeting_id SERIAL PRIMARY KEY,
    title VARCHAR(60) NOT NULL,
    fk_type_meeting_id INT NOT NULL,
    date_time TIMESTAMP NOT NULL,
    date_creation TIMESTAMP NOT NULL,
    fk_psychologist_id INT NOT NULL,
    uri_access VARCHAR(255) UNIQUE NOT NULL,
    fk_status_meeting_id INT DEFAULT 1,
    summary TEXT,
    recording_consent BOOLEAN NOT NULL DEFAULT false,
    uri_record_file VARCHAR(255),
    uri_image VARCHAR(255),
    CONSTRAINT fk_meetings_type_meeting FOREIGN KEY (fk_type_meeting_id) REFERENCES type_meeting(type_meeting_id) ON DELETE RESTRICT,
    CONSTRAINT fk_meetings_psychologist FOREIGN KEY (fk_psychologist_id) REFERENCES psychologists(psychologist_id) ON DELETE CASCADE,
    CONSTRAINT fk_meetings_status FOREIGN KEY (fk_status_meeting_id) REFERENCES status_meeting(status_meeting_id) ON DELETE RESTRICT
);

CREATE INDEX idx_meetings_meetings ON meetings(date_time);

CREATE TABLE meeting_participants (
    fk_meeting_id INT NOT NULL,
    fk_user_id UUID NOT NULL,
    FOREIGN KEY (fk_meeting_id) REFERENCES meetings(meeting_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE chat_messages (
    chat_message_id SERIAL PRIMARY KEY,
    fk_meeting_id INT NOT NULL,
    fk_user_id UUID NOT NULL,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fk_type_message_id INT NOT NULL DEFAULT 1,
    file_uri VARCHAR(255),
    CONSTRAINT fk_chat_meeting FOREIGN KEY (fk_meeting_id) REFERENCES meetings(meeting_id) ON DELETE CASCADE,
    CONSTRAINT fk_chat_user FOREIGN KEY (fk_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_chat_type_message FOREIGN KEY (fk_type_message_id) REFERENCES type_message(type_message_id) ON DELETE RESTRICT
);

CREATE INDEX idx_chat_messages_meeting_created_at ON chat_messages (fk_meeting_id, created_at);

CREATE TABLE message_read (
    fk_message_id INT NOT NULL,
    fk_user_id UUID NOT NULL,
    read_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (fk_message_id, fk_user_id),
    CONSTRAINT fk_message_read_message FOREIGN KEY (fk_message_id) REFERENCES chat_messages(chat_message_id) ON DELETE CASCADE,
    CONSTRAINT fk_message_read_user FOREIGN KEY (fk_user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE notifications (
    notification_id SERIAL PRIMARY KEY,
    fk_user_id UUID NOT NULL,
    fk_chat_message_id INT,
    fk_type_notification_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(100),
    CONSTRAINT fk_notifications_user FOREIGN KEY (fk_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_notifications_chat FOREIGN KEY (fk_chat_message_id) REFERENCES chat_messages(chat_message_id) ON DELETE CASCADE,
    CONSTRAINT fk_notifications_type_notification FOREIGN KEY (fk_type_notification_id) REFERENCES type_notification(type_notification_id) ON DELETE RESTRICT
);

CREATE INDEX idx_notifications_user_id ON notifications(fk_user_id);

-- Inserção dos valores
INSERT INTO gender (name) VALUES
    ('M'),
    ('F'),
    ('O');

INSERT INTO type_notification (name) VALUES
    ('Message'),
    ('Call'),
    ('VideoCall'),
    ('Session'),
    ('System'),
    ('Others');

INSERT INTO type_meeting (name) VALUES
    ('Individual'),
    ('Grupal'),
    ('Emergencial');

INSERT INTO status_meeting (name) VALUES
    ('agendada'),
    ('concluída'),
    ('cancelada');

INSERT INTO type_message (name) VALUES
    ('Text'),
    ('Image'),
    ('Document'),
    ('Audio'),
    ('Video');