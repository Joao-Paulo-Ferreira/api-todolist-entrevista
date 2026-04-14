package com.entrevista.todolist.service;

import com.entrevista.todolist.model.SituacaoTarefa;
import com.entrevista.todolist.model.Tarefa;
import com.entrevista.todolist.repository.TarefaRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    @DisplayName("Deve concluir uma tarefa pendente com sucesso")
    void deveConcluirTarefaComSucesso() {
        Long idTarefa = 1L;
        Tarefa tarefaPendente = new Tarefa();
        tarefaPendente.setId(idTarefa);
        tarefaPendente.setSituacao(SituacaoTarefa.PENDENTE);

        Mockito.when(tarefaRepository.findById(idTarefa)).thenReturn(Optional.of(tarefaPendente));
        Mockito.when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaPendente);

        Tarefa tarefaConcluida = tarefaService.concluirTarefa(idTarefa);

        assertNotNull(tarefaConcluida);
        assertEquals(SituacaoTarefa.CONCLUIDA, tarefaConcluida.getSituacao());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar concluir uma tarefa já concluída")
    void deveLancarExcecaoAoConcluirTarefaJaConcluida() {
        Long idTarefa = 2L;
        Tarefa tarefaJaConcluida = new Tarefa();
        tarefaJaConcluida.setId(idTarefa);
        tarefaJaConcluida.setSituacao(SituacaoTarefa.CONCLUIDA);

        Mockito.when(tarefaRepository.findById(idTarefa)).thenReturn(Optional.of(tarefaJaConcluida));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            tarefaService.concluirTarefa(idTarefa);
        });

        assertEquals("Esta tarefa já se encontra concluída.", exception.getMessage());
    }
}