CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE veiculos (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    preco NUMERIC(10, 2) NOT NULL
);