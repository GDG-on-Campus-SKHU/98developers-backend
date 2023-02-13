use solution_challenge;

insert into user(user_name, password) values ('admin', '{bcrypt}$2a$10$4kWzwruKCB.0KtXOwYIlbe0rThqMI6e0pHFeZszpWnd2cPe1mgH3q');
insert into member_roles(member_id, roles) values (1, 'USER');

insert into user(user_name, password) values ('user', '{bcrypt}$2a$10$4kWzwruKCB.0KtXOwYIlbe0rThqMI6e0pHFeZszpWnd2cPe1mgH3q');
insert into member_roles(member_id, roles) values (2, 'USER');