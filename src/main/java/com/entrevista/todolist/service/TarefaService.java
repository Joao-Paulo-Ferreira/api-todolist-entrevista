package com.entrevista.todolist.service;

import com.entrevista.todolist.model.SituacaoTarefa;
import com.entrevista.todolist.model.Tarefa;
import com.entrevista.todolist.repository.TarefaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Transactional
    public Tarefa criar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Page<Tarefa> listarTodas(Pageable pageable) {
        return tarefaRepository.findAll(pageable);
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));
    }

    @Transactional
    public Tarefa atualizar(Long id, Tarefa dadosAtualizados) {
        Tarefa tarefaExistente = buscarPorId(id);
        
        tarefaExistente.setTitulo(dadosAtualizados.getTitulo());
        tarefaExistente.setDescricao(dadosAtualizados.getDescricao());
        tarefaExistente.setDataConclusaoPrevista(dadosAtualizados.getDataConclusaoPrevista());
        
        return tarefaRepository.save(tarefaExistente);
    }

    @Transactional
    public Tarefa concluirTarefa(Long id) {
        Tarefa tarefa = buscarPorId(id);

        if (tarefa.getSituacao() == SituacaoTarefa.CONCLUIDO) {
            throw new IllegalStateException("Esta tarefa já se encontra concluída.");
        }

        tarefa.setSituacao(SituacaoTarefa.CONCLUIDO);
        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public void excluir(Long id) {
        Tarefa tarefa = buscarPorId(id);
        tarefaRepository.delete(tarefa);
    }
}