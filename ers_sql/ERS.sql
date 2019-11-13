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