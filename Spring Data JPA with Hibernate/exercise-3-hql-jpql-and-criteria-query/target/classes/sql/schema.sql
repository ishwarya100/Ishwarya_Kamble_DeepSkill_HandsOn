-- Payroll schema (referenced by Hands on 2, 4 and 5): department, skill, employee
CREATE TABLE IF NOT EXISTS department (
    dp_id INT NOT NULL AUTO_INCREMENT,
    dp_name VARCHAR(50),
    PRIMARY KEY (dp_id)
);

CREATE TABLE IF NOT EXISTS skill (
    sk_id INT NOT NULL AUTO_INCREMENT,
    sk_name VARCHAR(50),
    PRIMARY KEY (sk_id)
);

CREATE TABLE IF NOT EXISTS employee (
    em_id INT NOT NULL AUTO_INCREMENT,
    em_name VARCHAR(50),
    em_salary NUMERIC(10, 2),
    em_permanent BOOLEAN,
    em_date_of_birth DATE,
    em_dp_id INT,
    PRIMARY KEY (em_id),
    FOREIGN KEY (em_dp_id) REFERENCES department (dp_id)
);

CREATE TABLE IF NOT EXISTS employee_skill (
    es_em_id INT NOT NULL,
    es_sk_id INT NOT NULL,
    PRIMARY KEY (es_em_id, es_sk_id),
    FOREIGN KEY (es_em_id) REFERENCES employee (em_id),
    FOREIGN KEY (es_sk_id) REFERENCES skill (sk_id)
);

-- Hands on 3: Quiz attempt schema - user, question, options, attempt, attempt_question, attempt_option
CREATE TABLE IF NOT EXISTS app_user (
    us_id INT NOT NULL AUTO_INCREMENT,
    us_name VARCHAR(50),
    PRIMARY KEY (us_id)
);

CREATE TABLE IF NOT EXISTS question (
    qn_id INT NOT NULL AUTO_INCREMENT,
    qn_text VARCHAR(255),
    PRIMARY KEY (qn_id)
);

CREATE TABLE IF NOT EXISTS options (
    op_id INT NOT NULL AUTO_INCREMENT,
    op_qn_id INT,
    op_text VARCHAR(255),
    op_score NUMERIC(3, 1),
    PRIMARY KEY (op_id),
    FOREIGN KEY (op_qn_id) REFERENCES question (qn_id)
);

CREATE TABLE IF NOT EXISTS attempt (
    at_id INT NOT NULL AUTO_INCREMENT,
    at_us_id INT,
    at_date DATE,
    PRIMARY KEY (at_id),
    FOREIGN KEY (at_us_id) REFERENCES app_user (us_id)
);

CREATE TABLE IF NOT EXISTS attempt_question (
    aq_id INT NOT NULL AUTO_INCREMENT,
    aq_at_id INT,
    aq_qn_id INT,
    PRIMARY KEY (aq_id),
    FOREIGN KEY (aq_at_id) REFERENCES attempt (at_id),
    FOREIGN KEY (aq_qn_id) REFERENCES question (qn_id)
);

CREATE TABLE IF NOT EXISTS attempt_option (
    ao_id INT NOT NULL AUTO_INCREMENT,
    ao_aq_id INT,
    ao_op_id INT,
    PRIMARY KEY (ao_id),
    FOREIGN KEY (ao_aq_id) REFERENCES attempt_question (aq_id),
    FOREIGN KEY (ao_op_id) REFERENCES options (op_id)
);
