package com.Cont;

import com.Cont.Model.Conteiner;
import com.Cont.Model.Movimentacoes;
import com.Cont.Repository.ConteinerRepository;
import com.Cont.Repository.MovimentacoesRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.Cont.Repository")
public class ContApplication {

    @Bean
    CommandLineRunner initDatabase(@Autowired ConteinerRepository conteinerRepository, @Autowired MovimentacoesRepository movimentacoesRepository) {
        return args -> {
            // conteinerRepository.deleteAll();

            Conteiner c = new Conteiner();
            c.setClient("JJ importações");
            c.setNumberOfConteiner("Test1234567");
            c.setType("40");
            c.setStatus("Vazio");
            c.setCategory("Importação");

            conteinerRepository.save(c);
            
            Movimentacoes m = new Movimentacoes();
            m.setTypeOfDrive("Embarque");
            m.setDataTimeInitial(LocalDateTime.now());
            m.setDataTimeFinal(LocalDateTime.now());
           
            movimentacoesRepository.save(m);
           
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ContApplication.class, args);
    }

}
