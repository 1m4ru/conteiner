/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Cont.Repository;

import com.Cont.Model.Conteiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 1m4ru
 */
@Repository
public interface ConteinerRepository extends JpaRepository<Conteiner, Long>{
    
}
