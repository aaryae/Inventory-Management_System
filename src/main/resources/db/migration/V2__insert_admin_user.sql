INSERT INTO users (
    id, email, password, password_last_updated, role, username, verification_code
) VALUES (
           6,
             'admin@hotmail.com',
             '$2a$10$vetP.kJhtZkDQE/se1k5FOpujbxKJEgPS.lTL3wBW24j32iXnQkLe',
             NOW(),
             'ADMIN',
             'admin',
             NULL
         );
