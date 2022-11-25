/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conteiner.conteinerController;

import com.conteiner.model.Conteiner;
import com.conteiner.model.Movimentacoes;
import com.conteiner.repository.MovimentacoesRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1m4ru
 */
@RestController
@RequestMapping("api/mapping")
@AllArgsConstructor
public class MovimentaçõesController {

    private final MovimentacoesRepository movimentacoesRepository;

    @GetMapping
    public @ResponseBody
    List<Movimentacoes> list() throws Exception {
        if (movimentacoesRepository.findAll().isEmpty()) {
            throw new Exception("Não foi possivel carregar a lista");
        }
        return movimentacoesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacoes> findBy(@PathVariable Long id) throws Exception {
        if (id == null) {
            throw new Exception("Não foi possivel pesquisar por  id");
        }
        return movimentacoesRepository.findById(id).map(recordFound -> ResponseEntity.ok()
                .body(recordFound)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Movimentacoes create(@RequestBody Movimentacoes movimentacoes) throws Exception {
        if (ObjectUtils.isEmpty(movimentacoes) || ObjectUtils.isEmpty(movimentacoes.getTypeOfDrive())) {
            throw new Exception("Não foi possivel salvar");
        }
        return movimentacoesRepository.save(movimentacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimentacoes> update(@PathVariable Long id,
            @RequestBody Movimentacoes movimentacoes) throws Exception {
        if (id == null) {
            throw new Exception("Não foi possivel pesquisar por  id");
        }
        if (ObjectUtils.isEmpty(movimentacoes) || ObjectUtils.isEmpty(movimentacoes.getTypeOfDrive())) {
            throw new Exception("Não foi possivel salvar");
        }

        return movimentacoesRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setTypeOfDrive(movimentacoes.getTypeOfDrive());
                    recordFound.setDateTimeInitial(movimentacoes.getDateTimeInitial());
                    recordFound.setDateTimeFinal(movimentacoes.getDateTimeFinal());
                    Movimentacoes update = movimentacoesRepository.save(recordFound);
                    return ResponseEntity.ok().body(update);

                }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (id == null) {
            throw new Exception("Erro ao deletar");
        }
        return movimentacoesRepository.findById(id)
                .map(recordFound -> {
                    movimentacoesRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
