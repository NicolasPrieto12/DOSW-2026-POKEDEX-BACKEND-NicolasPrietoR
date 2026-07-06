-- V3__fix_admin_password.sql
-- Corrige el hash BCrypt del admin. Password = "admin123"
UPDATE users
SET password = '$2a$10$Pjdbr/lJ3FuAwwczt.hjy.zSpDlQksfxwN/kueUOcSgRGQY7H/AT6'
WHERE email = 'admin@nikodev.com';
