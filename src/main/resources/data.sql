use solution_challenge;

insert into user(user_name, password) values ('admin', '1234');
insert into member_roles(member_id, roles) values (1, 'USER');

insert into user(user_name, password) values ('user', '1234');
insert into member_roles(member_id, roles) values (2, 'USER');