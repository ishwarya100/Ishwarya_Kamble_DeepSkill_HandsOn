-- ============================================================
-- Sample data for orm-learn - Exercise 3
-- Reuses the payroll (department, skill, employee) master data
-- for HQL/aggregate/native query demos, plus quiz attempt data
-- ============================================================

-- Hands on 3 (File 2): Department and Skill master data
-- ============================================================
insert into department (dp_id, dp_name) values (1, 'Engineering');
insert into department (dp_id, dp_name) values (2, 'Sales');
insert into department (dp_id, dp_name) values (3, 'HR');

insert into skill (sk_id, sk_name) values (1, 'Java');
insert into skill (sk_id, sk_name) values (2, 'SQL');
insert into skill (sk_id, sk_name) values (3, 'Communication');
insert into skill (sk_id, sk_name) values (4, 'Leadership');

-- Employee master data, mix of permanent and non permanent employees
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (1, 'John Smith', 65000.00, true, '1990-05-12', 1);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (2, 'Emma Davis', 72000.00, true, '1988-03-22', 3);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (3, 'Michael Brown', 48000.00, false, '1995-11-02', 2);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (4, 'Olivia Wilson', 61000.00, true, '1992-07-19', 1);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (5, 'William Taylor', 39000.00, false, '1998-01-30', 2);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (6, 'Sophia Anderson', 83000.00, true, '1985-09-14', 1);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (7, 'James Thomas', 45000.00, false, '1996-04-05', 3);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (8, 'Isabella Martinez', 58000.00, true, '1991-12-25', 2);

-- Many to many employee skill mapping
insert into employee_skill (es_em_id, es_sk_id) values (1, 1);
insert into employee_skill (es_em_id, es_sk_id) values (1, 2);
insert into employee_skill (es_em_id, es_sk_id) values (2, 2);
insert into employee_skill (es_em_id, es_sk_id) values (2, 3);
insert into employee_skill (es_em_id, es_sk_id) values (4, 1);
insert into employee_skill (es_em_id, es_sk_id) values (6, 1);

-- Hands on 3 (File 3): Quiz attempt sample data
-- Matches the exact quiz questions, options, scores and the
-- selected options shown in the expected output of the document
-- ============================================================
insert into app_user (us_id, us_name) values (1, 'testuser');

insert into question (qn_id, qn_text) values (1, 'What is the extension of the hyper text markup language file?');
insert into question (qn_id, qn_text) values (2, 'What is the maximum level of heading tag can be used in a HTML page?');
insert into question (qn_id, qn_text) values (3, 'The HTML document itself begins with <html> and ends </html>. State True or False');
insert into question (qn_id, qn_text) values (4, 'Choose the right option to store text value in a variable');

-- Options for question 1 (op_id 1-4), correct answer is '.html'
insert into options (op_id, op_qn_id, op_text, op_score) values (1, 1, '.xhtm', 0.0);
insert into options (op_id, op_qn_id, op_text, op_score) values (2, 1, '.ht', 0.0);
insert into options (op_id, op_qn_id, op_text, op_score) values (3, 1, '.html', 1.0);
insert into options (op_id, op_qn_id, op_text, op_score) values (4, 1, '.htmx', 0.0);

-- Options for question 2 (op_id 5-8), correct answer is '6'
insert into options (op_id, op_qn_id, op_text, op_score) values (5, 2, '5', 0.0);
insert into options (op_id, op_qn_id, op_text, op_score) values (6, 2, '3', 0.0);
insert into options (op_id, op_qn_id, op_text, op_score) values (7, 2, '4', 0.0);
insert into options (op_id, op_qn_id, op_text, op_score) values (8, 2, '6', 1.0);

-- Options for question 3 (op_id 9-10), correct answer is 'true'
insert into options (op_id, op_qn_id, op_text, op_score) values (9, 3, 'false', 0.0);
insert into options (op_id, op_qn_id, op_text, op_score) values (10, 3, 'true', 1.0);

-- Options for question 4 (op_id 11-14), two partially correct answers
insert into options (op_id, op_qn_id, op_text, op_score) values (11, 4, '''John''', 0.5);
insert into options (op_id, op_qn_id, op_text, op_score) values (12, 4, 'John', 0.0);
insert into options (op_id, op_qn_id, op_text, op_score) values (13, 4, '"John"', 0.5);
insert into options (op_id, op_qn_id, op_text, op_score) values (14, 4, '/John/', 0.0);

-- One attempt by testuser covering all four questions
insert into attempt (at_id, at_us_id, at_date) values (1, 1, '2026-07-01');

insert into attempt_question (aq_id, aq_at_id, aq_qn_id) values (1, 1, 1);
insert into attempt_question (aq_id, aq_at_id, aq_qn_id) values (2, 1, 2);
insert into attempt_question (aq_id, aq_at_id, aq_qn_id) values (3, 1, 3);
insert into attempt_question (aq_id, aq_at_id, aq_qn_id) values (4, 1, 4);

-- Options selected by testuser for each question of the attempt
insert into attempt_option (ao_id, ao_aq_id, ao_op_id) values (1, 1, 3);
insert into attempt_option (ao_id, ao_aq_id, ao_op_id) values (2, 2, 6);
insert into attempt_option (ao_id, ao_aq_id, ao_op_id) values (3, 3, 10);
insert into attempt_option (ao_id, ao_aq_id, ao_op_id) values (4, 4, 11);
