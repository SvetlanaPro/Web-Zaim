-- db/changelog/change-001.sql
-- liquibase formatted sql

-- changeset sryabukhina:1
CREATE TABLE settings (
                          id SERIAL PRIMARY KEY,
                          key VARCHAR(255) UNIQUE NOT NULL,
                          value VARCHAR(255) NOT NULL
);

COMMENT ON TABLE settings IS 'таблица настроек приложения';
COMMENT ON COLUMN settings.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN settings.key IS 'Ключ настройки';
COMMENT ON COLUMN settings.value IS 'Значение настройки';

INSERT INTO settings (key, value) VALUES ('distanceRatioThreshold', '0.9');

-- changeset sryabukhina:2
CREATE TABLE request_content (
                                 id SERIAL PRIMARY KEY,
                                 loan_request_id UUID UNIQUE NOT NULL,
                                 content JSON NOT NULL
);
COMMENT ON TABLE request_content IS 'таблица с запросом';
COMMENT ON COLUMN request_content.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN request_content.loan_request_id IS 'Уникальный идентификатор запроса';
COMMENT ON COLUMN request_content.content IS 'Содержимое JSON';

-- changeset sryabukhina:3
CREATE TABLE reg_person (
                            id SERIAL PRIMARY KEY,
                            loan_request_id UUID NOT NULL,
                            first_name VARCHAR(255) NOT NULL,
                            middle_name VARCHAR(255),
                            last_name VARCHAR(255) NOT NULL,
                            CONSTRAINT fk_reg_person_request_content FOREIGN KEY (loan_request_id) REFERENCES request_content (loan_request_id)
);

-- changeset sryabukhina:4
CREATE TABLE credit_bureau (
                               id SERIAL PRIMARY KEY,
                               loan_request_id UUID NOT NULL,
                               CONSTRAINT fk_credit_bureau_request_content FOREIGN KEY (loan_request_id) REFERENCES request_content (loan_request_id)
);

-- changeset sryabukhina:5
CREATE TABLE account_info (
                              id SERIAL PRIMARY KEY,
                              credit_bureau_id INTEGER NOT NULL,
                              account_number VARCHAR(255) NOT NULL,
                              account_status VARCHAR(255) NOT NULL,
                              current_balance DECIMAL NOT NULL,
                              date_opened DATE NOT NULL,
                              days_in_arrears INTEGER NOT NULL,
                              delinquency_code VARCHAR(255) NOT NULL,
                              highest_days_in_arrears INTEGER NOT NULL,
                              is_your_account BOOLEAN NOT NULL,
                              last_payment_amount DECIMAL NOT NULL,
                              last_payment_date DATE,
                              loaded_at DATE NOT NULL,
                              original_amount DECIMAL NOT NULL,
                              overdue_balance DECIMAL NOT NULL,
                              overdue_date DATE,
                              product_type_id INTEGER NOT NULL,
                              CONSTRAINT fk_account_info_credit_bureau FOREIGN KEY (credit_bureau_id) REFERENCES credit_bureau (id)
);

-- changeset sryabukhina:6
CREATE TABLE verified_name (
                               id SERIAL PRIMARY KEY,
                               credit_bureau_id INTEGER NOT NULL,
                               first_name VARCHAR(255) NOT NULL,
                               other_name VARCHAR(255) NOT NULL,
                               surname VARCHAR(255) NOT NULL,
                               CONSTRAINT fk_verified_name_credit_bureau FOREIGN KEY (credit_bureau_id) REFERENCES credit_bureau (id)
);