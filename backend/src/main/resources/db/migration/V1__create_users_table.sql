CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     email VARCHAR(255) NOT NULL UNIQUE,
                                     password_hash VARCHAR(255) NOT NULL,
                                     role VARCHAR(30) NOT NULL DEFAULT 'USER',
                                     created_at TIMESTAMP NOT NULL DEFAULT NOW()
);