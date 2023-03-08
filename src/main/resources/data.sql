use solution_challenge;

insert into user(uid, password) values ('admin', '{bcrypt}$2a$10$4kWzwruKCB.0KtXOwYIlbe0rThqMI6e0pHFeZszpWnd2cPe1mgH3q');
insert into member_roles(member_id, roles) values (1, 'USER');

insert into user(uid, password) values ('user', '{bcrypt}$2a$10$4kWzwruKCB.0KtXOwYIlbe0rThqMI6e0pHFeZszpWnd2cPe1mgH3q');
insert into member_roles(member_id, roles) values (2, 'USER');

insert into challenge(topic,
                      how_proof,
                      correct_proof,
                      expected_results,
                      please_note,
                      period_start_date,
                      period_end_date,
                      expired_day) values ('재활용이 가능한 물건을 통해 나만의 물건을 제작하고 인증하기',
                                           '제출 일자 및 시간을 확인합니다. \n>제출 시간에 인증에 필요한 사진을 제출합니다.',
                                           '',
                                           '개인이 재활용 물건을 제작하면서 환경보전에 기여할 수 있습니다. 매일 7일간 재활용품을 제작하게 되면 어떠한 효과가 있습니다.',
                                           '챌린지는 비용을 지불해서 참여할 수 있습니다.\n올바른 인증 방법으로 참여해주셔야 합니다.\n올바르지 않은 인증은 미통과 사유가 될 수 있습니다.',
                                           '2023/03/09 18:00:00',
                                           '2023/03/16 18:00:00',
                                           '2023/03/08 23:59:59'
                                           );
insert into user_challenge(image, challenge_id, user_id) values ('zzzzz', 1, 1);
insert into user_challenge(image, challenge_id, user_id) values ('zzzzz', 1, 2);