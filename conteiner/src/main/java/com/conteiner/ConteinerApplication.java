package com.conteiner;

import com.conteiner.model.Conteiner;
import com.conteiner.model.Movimentacoes;
import com.conteiner.repository.ConteinerRepository;
import com.conteiner.repository.MovimentacoesRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConteinerApplication {

    
    @Bean
    CommandLineRunner initDatabase(@Autowired ConteinerRepository conteinerRepository, @Autowired MovimentacoesRepository movimentacoesRepository){
        return args -> {
            Conteiner c = new Conteiner();
            c.setClient("Martins Importações");
            c.setNumberOfConteiner("Test1234567");
            c.setType("20");
            c.setStatus("Cheio");
            c.setCategory("Exportação");
            
            conteinerRepository.save(c);
            
            Movimentacoes m = new Movimentacoes();
            m.setTypeOfDrive("Embarque");
            m.setDateTimeInitial(LocalDateTime.now());
            m.setDateTimeFinal(LocalDateTime.now());
            
            movimentacoesRepository.save(m);
        };
    }
    
	public static void main(String[] args) {
		SpringApplication.run(ConteinerApplication.class, args);
	}

}
