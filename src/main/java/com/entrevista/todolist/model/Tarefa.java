package com.entrevista.todolist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres.")
    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoTarefa situacao = SituacaoTarefa.PENDENTE;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @NotNull(message = "A data de conclusão prevista é obrigatória.")
    @FutureOrPresent(message = "A data de previsão não pode estar no passado.")
    @Column(name = "data_conclusao_prev")
    private LocalDateTime dataConclusaoPrevista;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public SituacaoTarefa getSituacao() { return situacao; }
    public void setSituacao(SituacaoTarefa situacao) { this.situacao = situacao; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }

    public LocalDateTime getDataConclusaoPrevista() { return dataConclusaoPrevista; }
    public void setDataConclusaoPrevista(LocalDateTime dataConclusaoPrevista) 
            { this.dataConclusaoPrevista = dataConclusaoPrevista; }
}