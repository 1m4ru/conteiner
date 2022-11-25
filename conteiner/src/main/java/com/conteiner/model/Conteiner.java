/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conteiner.model;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author 1m4ru
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Conteiner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "client", length = 140, nullable = false)
    private String client;
    
    @Column(name = "number_of_conteiner", length = 140, nullable = false)
    private String numberOfConteiner;
    
    @Column(name = "type", length = 20, nullable = false)
    private String type;
    
    @Column(name = "status", length = 20, nullable = false)
    private String status;
    
    @Column(name = "category", length = 20, nullable = false)
    private String category;

    @OneToOne
    @JoinColumn(name= "id_movimentações")
    private Movimentacoes movimentacoes;
   
    
}
