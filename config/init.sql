CREATE SCHEMA IF NOT EXISTS rinha;

CREATE TABLE IF NOT EXISTS rinha.pessoas (
                                       uuid VARCHAR(36) PRIMARY KEY,
                                       apelido VARCHAR(32) UNIQUE NOT NULL,
                                       nome VARCHAR(100) NOT NULL,
                                       nascimento VARCHAR(10) NOT NULL,
                                       stack TEXT[],
                                       busca TEXT
);

CREATE EXTENSION IF NOT EXISTS PG_TRGM;

CREATE INDEX CONCURRENTLY IF NOT EXISTS IDX_PESSOAS_BUSCA_TGRM ON rinha.pessoas USING GIST (busca GIST_TRGM_OPS);