package com.entrevista.todolist.controller;

import com.entrevista.todolist.model.Tarefa;
import com.entrevista.todolist.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.criar(tarefa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaTarefa.getId())
                .toUri();
        return ResponseEntity.created(uri).body(novaTarefa);
    }

    @GetMapping
    public ResponseEntity<Page<Tarefa>> listarTodas(Pageable pageable) {
        return ResponseEntity.ok(tarefaService.listarTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(tarefaService.atualizar(id, tarefa));
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluirTarefa(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.concluirTarefa(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}