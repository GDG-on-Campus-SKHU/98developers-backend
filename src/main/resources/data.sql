use solution_challenge;

insert into user(uid, password) values ('admin', '{bcrypt}$2a$10$4kWzwruKCB.0KtXOwYIlbe0rThqMI6e0pHFeZszpWnd2cPe1mgH3q');
insert into member_roles(member_id, roles) values (1, 'USER');

insert into user(uid, password) values ('user', '{bcrypt}$2a$10$4kWzwruKCB.0KtXOwYIlbe0rThqMI6e0pHFeZszpWnd2cPe1mgH3q');
insert into member_roles(member_id, roles) values (2, 'USER');

insert into challenge(topic,collect_proof) values ('eee', 'ttt');
insert into user_challenge(image, challenge_id, user_id) values ('zzzzz', 1, 1);
insert into user_challenge(image, challenge_id, user_id) values ('zzzzz', 1, 2);