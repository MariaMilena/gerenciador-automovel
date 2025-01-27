DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'tipo_combustivel_enum') THEN
        CREATE TYPE tipo_combustivel_enum AS ENUM ('gasolina', 'etanol', 'diesel', 'flex');
    END IF;
END $$;

CREATE TABLE carros (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    quantidade_portas INT NOT NULL,
    tipo_combustivel tipo_combustivel_enum NOT NULL,
    veiculo_id UUID,
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id) ON DELETE CASCADE
);