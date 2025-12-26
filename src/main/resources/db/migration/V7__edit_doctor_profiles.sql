ALTER TABLE doctor_profiles DROP CONSTRAINT IF EXISTS fk_doctor_profiles_users;

ALTER TABLE doctor_profiles DROP COLUMN IF EXISTS user_id;

ALTER TABLE doctor_profiles ALTER COLUMN id DROP IDENTITY IF EXISTS;

ALTER TABLE doctor_profiles
    ADD CONSTRAINT fk_doctor_profiles_users
    FOREIGN KEY (id)
    REFERENCES users(id)
    ON DELETE CASCADE;