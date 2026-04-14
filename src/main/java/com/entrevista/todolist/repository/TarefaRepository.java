package com.entrevista.todolist.repository;

import com.entrevista.todolist.model.SituacaoTarefa;
import com.entrevista.todolist.model.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Page<Tarefa> findBySituacao(SituacaoTarefa situacao, Pageable pageable);

    @Query("SELECT t FROM Tarefa t WHERE t.dataConclusaoPrevista < CURRENT_TIMESTAMP AND t.situacao <> 'CONCLUIDA'")
    List<Tarefa> encontrarTarefasAtrasadas();
}