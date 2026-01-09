CREATE TABLE test (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO test (username) VALUES ('john_doe');
INSERT INTO test (username) VALUES ('jane_smith');