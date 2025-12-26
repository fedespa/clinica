ALTER TABLE nurse_profiles DROP CONSTRAINT IF EXISTS fk_nurse_profile_users;

ALTER TABLE nurse_profiles DROP COLUMN IF EXISTS user_id;

ALTER TABLE nurse_profiles ALTER COLUMN id DROP IDENTITY IF EXISTS;

ALTER TABLE nurse_profiles
    ADD CONSTRAINT fk_nurse_profiles_users
    FOREIGN KEY (id)
    REFERENCES users(id)
    ON DELETE CASCADE;