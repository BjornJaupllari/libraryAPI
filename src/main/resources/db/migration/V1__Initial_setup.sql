-- V1__Initial_setup.sql

-- Insert a default user
INSERT INTO users (id, name, email, phone_number, password, age, gender, role, created_at)
VALUES (1, 'Guest', 'guest@example.com', '1234567890',
        '$2a$10$glDO8G2wNO/Y1K.Kp4AjberJ2Ii4x6MneSmRmY5TaCWmmi8qvGwpC', 30,
        'Male', 'ADMIN', CURRENT_TIMESTAMP);
