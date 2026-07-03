-- V2__add_password_and_admin.sql

ALTER TABLE users ADD COLUMN password VARCHAR(255);

-- Admin de prueba: password = "admin123" (BCrypt)
INSERT INTO users (email, trainer_name, role, active, password)
VALUES ('admin@nikodev.com', 'Admin', 'ADMIN', true,
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');
