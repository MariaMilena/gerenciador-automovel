CREATE TABLE motos (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cilindrada INT NOT NULL,
    veiculo_id UUID,
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id) ON DELETE CASCADE
);