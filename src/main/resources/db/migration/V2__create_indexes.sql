ALTER TABLE users RENAME COLUMN name TO first_name;
ALTER TABLE users
    ADD COLUMN last_name VARCHAR(255) NOT NULL,
    DROP COLUMN full_name;

ALTER TABLE user_roles ADD CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role_id);


CREATE INDEX idx_admissions_patient_id ON admissions(patient_id);
CREATE INDEX idx_admissions_doctor_id ON admissions(doctor_id);
CREATE INDEX idx_admissions_status_triage ON admissions(status, triage_level);
CREATE INDEX idx_admissions_active ON admissions(id) WHERE deleted_at IS NULL;

CREATE INDEX idx_patients_last_name ON patients(last_name);
CREATE INDEX idx_patients_active ON patients(id) WHERE deleted_at IS NULL;

CREATE INDEX idx_users_name ON users(last_name);
CREATE INDEX idx_users_active ON users(id) WHERE deleted_at IS NULL;