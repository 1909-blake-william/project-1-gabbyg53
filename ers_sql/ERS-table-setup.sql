/*********************************
Tables and sequences
**********************************/
--CREATE SEQUENCE ers_user_role_seq;
CREATE TABLE ERS_USER_ROLES (
    ers_user_role_id NUMBER PRIMARY KEY NOT NULL,
    user_role VARCHAR2(10) NOT NULL
);

--CREATE SEQUENCE ers_reimbursement_type_seq;
CREATE TABLE ERS_REIMBURSEMENT_TYPE (
    reimb_type_id NUMBER PRIMARY KEY NOT NULL,
    reimb_type VARCHAR2(10) NOT NULL
);

--CREATE SEQUENCE ers_reimbursement_status_seq;
CREATE TABLE ERS_REIMBURSEMENT_STATUS (
    reimb_status_id NUMBER PRIMARY KEY NOT NULL,
    reimb_status VARCHAR2(10) NOT NULL
);

CREATE SEQUENCE ers_users_seq;
CREATE TABLE ERS_USERS (
    ers_user_id NUMBER PRIMARY KEY NOT NULL,
    ers_username VARCHAR2(50) UNIQUE NOT NULL,
    ers_password VARCHAR2(50) NOT NULL,
    user_first_name VARCHAR2(100) NOT NULL,
    user_last_name VARCHAR2(100) NOT NULL,
    user_email VARCHAR2(50) UNIQUE NOT NULL,
    user_role_id NUMBER NOT NULL REFERENCES ERS_USER_ROLES(ers_user_role_id)
);

CREATE SEQUENCE ers_reimbursement_seq;
CREATE TABLE ERS_REIMBURSEMENT (
    reimb_id NUMBER PRIMARY KEY NOT NULL,
    reimb_amount NUMBER NOT NULL,
    reimb_submitted TIMESTAMP NOT NULL,
    reimb_resolved TIMESTAMP, --DEFAULT NULL
    reimb_description VARCHAR2(250),
    reimb_author NUMBER NOT NULL REFERENCES ERS_USERS(ers_user_id),
    reimb_resolver NUMBER REFERENCES ERS_USERS(ers_user_id), --DEFAULT 0
    reimb_status_id NUMBER NOT NULL REFERENCES ERS_REIMBURSEMENT_STATUS(reimb_status_id),
    reimb_type_id NUMBER NOT NULL REFERENCES ERS_REIMBURSEMENT_TYPE(reimb_type_id)
);

commit;

