CREATE TABLE tarefas (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    situacao VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_conclusao_prev TIMESTAMP NOT NULL,
    
    CONSTRAINT check_situacao CHECK (situacao IN ('PENDENTE', 'EM ANDAMENTO', 'CONCLUIDO'))
);

CREATE INDEX idx_tarefas_situacao ON tarefas(situacao);
CREATE INDEX idx_tarefas_prazo ON tarefas(data_conclusao_prev);