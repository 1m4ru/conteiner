/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Cont.Controller;

import com.Cont.Model.Conteiner;
import com.Cont.Repository.ConteinerRepository;
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
@RequestMapping("/api/conteiner")
@AllArgsConstructor
public class ConteinerController {
    
     private final ConteinerRepository conteinerRepository;

    @GetMapping
   public List<Conteiner> list() throws Exception{
        List<Conteiner> conteiners = conteinerRepository.findAll();
       return conteiners;
//      if(conteinerRepository.findAll().isEmpty()){
//          throw new Exception("Não foi possível retornar a lista.");
//      }
//               return conteinerRepository.findAll();
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<Conteiner> findBy(@PathVariable Long id) throws Exception{
       if(id == null){
           throw new Exception("Erro ao pesquisar por Id");
       }
       return conteinerRepository.findById(id).map(recordFound -> ResponseEntity.ok().body(recordFound)).orElse(ResponseEntity.notFound().build());
   }
   
   @PostMapping
   @ResponseStatus(code = HttpStatus.CREATED)
   public Conteiner create(@RequestBody Conteiner conteiner) throws Exception{
          if(ObjectUtils.isEmpty(conteiner) || ObjectUtils.isEmpty(conteiner.getClient())){
              throw new Exception("Erro ao salvar cliente");
          }
       
       return conteinerRepository.save(conteiner);
   }

    @PutMapping("/{id}")
    public ResponseEntity<Conteiner> update(@PathVariable Long id,
            @RequestBody Conteiner conteiner) throws Exception {
         if(id == null){
           throw new Exception("Erro ao pesquisar por Id");
       }
           if(ObjectUtils.isEmpty(conteiner) || ObjectUtils.isEmpty(conteiner.getClient())){
              throw new Exception("Erro ao alterar cliente");
          }
        
        return conteinerRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setClient(conteiner.getClient());
                    recordFound.setNumberOfConteiner(conteiner.getNumberOfConteiner());
                    recordFound.setType(conteiner.getType());
                    recordFound.setStatus(conteiner.getStatus());
                    recordFound.setCategory(conteiner.getCategory());
                    Conteiner updated = conteinerRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
      @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
         if(id == null){
           throw new Exception("Erro ao deletar");
       }
        return conteinerRepository.findById(id)
                .map(recordFound -> {
                    conteinerRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
}
