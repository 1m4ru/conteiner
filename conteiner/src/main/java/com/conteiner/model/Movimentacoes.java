/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conteiner.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author 1m4ru
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Movimentacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "type_of_drive", length = 200, nullable = false)
    private String typeOfDrive;

    @Column(name = "date_time_time", length = 200, nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime dateTimeInitial;
    
    @Column(name = "date_time_final", length = 200, nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime dateTimeFinal;
    
    
}
