

INSERT INTO user (user_id,email,username, password, enabled) values(1,'admin@gmail.com','admin','$2a$10$.ObJYBCq7/Ts4V9HBarnmOLGp1SR8BKETzkOsBalDiPZR2gEChVAe',1);

INSERT INTO user (user_id,email,username, password, enabled) values(2,'user@gmail.com','user','$2a$10$zk1H50FcurUnyyxlIacnOu0OC66lxRB/W1PCOGcclVcwASnrJQPFa',1);



INSERT INTO role (role_id,role) values (1,'ADMIN');
INSERT INTO role (role_id,role) values (2,'USER');

INSERT INTO user_role (user_id,role_id) values (1,1);
INSERT INTO user_role (user_id,role_id) values (2,2);
