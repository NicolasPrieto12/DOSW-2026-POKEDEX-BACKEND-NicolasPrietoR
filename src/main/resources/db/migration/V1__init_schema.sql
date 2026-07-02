-- V1__init_schema.sql
-- Migración inicial — NikoDex API

CREATE TABLE regions (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE types (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE pokemon (
    id              BIGSERIAL PRIMARY KEY,
    national_number INTEGER      NOT NULL UNIQUE,
    name            VARCHAR(100) NOT NULL UNIQUE,
    image_url       TEXT,
    description     VARCHAR(500),
    generation      INTEGER      NOT NULL,
    region_id       BIGINT REFERENCES regions (id),
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_pokemon_number ON pokemon (national_number);

CREATE TABLE pokemon_stats (
    id              BIGSERIAL PRIMARY KEY,
    pokemon_id      BIGINT  NOT NULL UNIQUE REFERENCES pokemon (id) ON DELETE CASCADE,
    hp              INTEGER NOT NULL,
    attack          INTEGER NOT NULL,
    defense         INTEGER NOT NULL,
    special_attack  INTEGER NOT NULL,
    special_defense INTEGER NOT NULL,
    speed           INTEGER NOT NULL
);

CREATE TABLE pokemon_type (
    pokemon_id BIGINT NOT NULL REFERENCES pokemon (id) ON DELETE CASCADE,
    type_id    BIGINT NOT NULL REFERENCES types (id),
    PRIMARY KEY (pokemon_id, type_id)
);

CREATE TABLE users (
    id              BIGSERIAL PRIMARY KEY,
    email           VARCHAR(255) NOT NULL UNIQUE,
    trainer_name    VARCHAR(30) UNIQUE,
    profile_picture TEXT,
    role            VARCHAR(20)  NOT NULL DEFAULT 'TRAINER',
    active          BOOLEAN               DEFAULT TRUE,
    created_at      TIMESTAMP             DEFAULT NOW()
);

CREATE TABLE teams (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(30) NOT NULL,
    user_id    BIGINT      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    deleted    BOOLEAN   DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE team_members (
    team_id    BIGINT NOT NULL REFERENCES teams (id) ON DELETE CASCADE,
    pokemon_id BIGINT NOT NULL REFERENCES pokemon (id),
    PRIMARY KEY (team_id, pokemon_id)
);

CREATE TABLE favorites (
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT    NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    pokemon_id BIGINT    NOT NULL REFERENCES pokemon (id) ON DELETE CASCADE,
    added_at   TIMESTAMP DEFAULT NOW(),
    UNIQUE (user_id, pokemon_id)
);
