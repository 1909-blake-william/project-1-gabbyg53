/*****************************
populate tables
*****************************/
--INSERT INTO ERS_USER_ROLES ( ers_user_role_id, user_role)
--VALUES (0, 'manager');
--
--INSERT INTO ERS_USER_ROLES ( ers_user_role_id, user_role)
--VALUES (1, 'employee');
--
--INSERT INTO ERS_REIMBURSEMENT_TYPE ( reimb_type_id, reimb_type)
--VALUES (1, 'lodging');
--
--INSERT INTO ERS_REIMBURSEMENT_TYPE ( reimb_type_id, reimb_type)
--VALUES (2, 'travel');
--
--INSERT INTO ERS_REIMBURSEMENT_TYPE ( reimb_type_id, reimb_type)
--VALUES (3, 'food');
--
--INSERT INTO ERS_REIMBURSEMENT_TYPE ( reimb_type_id, reimb_type)
--VALUES (4, 'other');
--
--INSERT INTO ERS_REIMBURSEMENT_STATUS ( reimb_status_id, reimb_status)
--VALUES (2, 'pending');
--
--INSERT INTO ERS_REIMBURSEMENT_STATUS ( reimb_status_id, reimb_status)
--VALUES (1, 'approved');
--
--INSERT INTO ERS_REIMBURSEMENT_STATUS ( reimb_status_id, reimb_status)
--VALUES (0, 'denied');
--
--INSERT INTO ERS_USERS ( ers_user_id, ers_username, ers_password,user_first_name,
--user_last_name, user_email, user_role_id)
--VALUES (ERS_USERS_SEQ.nextval, 'gabbyg53', 'password', 'Gabrielle', 'Griggs', 'gabrielle.griggs@revature.net',1);
--
--INSERT INTO ERS_USERS ( ers_user_id, ers_username, ers_password,user_first_name,
--user_last_name, user_email, user_role_id)
--VALUES (ERS_USERS_SEQ.nextval, 'ggriggs', 'p4ssw0rd', 'Gabrielle', 'Griggs', 'ggriggs@g.clemson.edu',0);
--
--INSERT INTO ERS_USERS ( ers_user_id, ers_username, ers_password,user_first_name,
--user_last_name, user_email, user_role_id)
--VALUES (ERS_USERS_SEQ.nextval, 'sg7son', 'password', 'Stefon', 'de la Cruz', 'sg7son@hotmail.com',1);

--INSERT INTO ERS_REIMBURSEMENT (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, 
--        reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
--VALUES (ERS_REIMBURSEMENT_SEQ.nextval, 420.00, TIMESTAMP '2019-11-06 00:00:00', null, 
--'lodging for business trip', 1, null, 2, 1);

--INSERT INTO ERS_REIMBURSEMENT (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, 
--        reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
--VALUES (ERS_REIMBURSEMENT_SEQ.nextval, 100.00, TIMESTAMP '2019-11-06 16:20:00', null, 
--'meals for employee lunch', 3, null, 2, 3);

--INSERT INTO ERS_REIMBURSEMENT (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, 
--        reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
--VALUES (ERS_REIMBURSEMENT_SEQ.nextval, 250.00, TIMESTAMP '2019-11-11 15:30:00', null, 
--'because i wanted to chill during class', 3, null, 2, 3);
