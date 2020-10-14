package me.henrique.syscredential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;

@SpringBootApplication
public class SyscredentialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyscredentialApplication.class, args);

	}

}
