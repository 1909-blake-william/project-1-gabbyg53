select * from ers_reimbursement;
select * from ers_users;
select * from ers_user_roles;
SELECT * FROM ers_reimbursement LEFT JOIN 
ers_users ON (ers_reimbursement.reimb_author = ers_users.ers_user_id)
left join ERS_REIMBURSEMENT_STATUS on (ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id)
WHERE ers_reimbursement_status.reimb_status = 'pending';

select * from ers_reimbursement
where reimb_status_id = 2;

update ers_users set ers_username = 'gabbyg53'
where ers_user_id = 1;

select * from ers_users left join 
ers_user_roles on (ers_users.user_role_id = ers_user_roles.ers_user_role_id)
where ers_user_roles.user_role = 'manager';

select * from ers_reimbursement left join
ers_reimbursement_status on (ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id)
where ers_reimbursement_status.reimb_status = 'pending';

TRUNCATE TABLE ers_reimbursement;

INSERT INTO ERS_USERS ( ers_user_id, ers_username, ers_password,user_first_name,
user_last_name, user_email, user_role_id)
VALUES (ERS_USERS_SEQ.nextval, 'lh_griggs', 'password', 'Leah', 'Griggs', 'lhgriggs@gmail.com',1);

INSERT INTO ERS_USERS ( ers_user_id, ers_username, ers_password,user_first_name,
user_last_name, user_email, user_role_id)
VALUES (ERS_USERS_SEQ.nextval, 'sarahg', 'password', 'Sarah', 'Griggs', 'sarah4@aol.com',1);




INSERT INTO ERS_REIMBURSEMENT (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, 
        reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
VALUES (ERS_REIMBURSEMENT_SEQ.nextval, 420.00, TIMESTAMP '2019-11-06 00:00:00', null, 
'lodging for business trip', 1, null, 2, 1);

INSERT INTO ERS_REIMBURSEMENT (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, 
        reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
VALUES (ERS_REIMBURSEMENT_SEQ.nextval, 100.00, TIMESTAMP '2019-11-06 16:20:00', null, 
'meals for employee lunch', 3, null, 2, 3);

INSERT INTO ERS_REIMBURSEMENT (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, 
        reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
VALUES (ERS_REIMBURSEMENT_SEQ.nextval, 250.00, TIMESTAMP '2019-11-11 15:30:00', null, 
'because i wanted to chill during class', 3, null, 2, 3);



