package me.henrique.syscredential.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import me.henrique.syscredential.api.dto.EventoFormDto;
import me.henrique.syscredential.api.dto.ParticipanteFormDto;
import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.EventoRepository;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import me.henrique.syscredential.domain.repository.RegionalRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	RegionalRepository rr;
	
	@Autowired
	ParticipanteRepository pr;
	
	@Autowired
	EventoRepository er;
	
	@Override
	public void run(String... args) throws Exception {

		Regional reg1 = new Regional(1, "Umuarama", "UMU");
		Regional reg2 = new Regional(2, "Maringá", "MAR");
		
		rr.saveAll(Arrays.asList(reg1, reg2));
		
		ParticipanteFormDto pdto1 = new ParticipanteFormDto("0444658981", "Henrique", "henrique@email.com", "44984414582", TamanhoCamiseta.G, reg1);
		ParticipanteFormDto pdto2 = new ParticipanteFormDto("9998887772", "Dani Guerra", "dani@email.com", "99988877", TamanhoCamiseta.P, reg2);
		
		Participante p1 = new Participante(pdto1);
		Participante p2 = new Participante(pdto2);
		
		pr.saveAll(Arrays.asList(p1, p2));
		
		EventoFormDto evdto1 = new EventoFormDto("Convenção de Natal", "Convenção de Vendas", "Hotel Mabu - Curitiba", Instant.parse("2020-12-02T08:00:00Z"), Instant.parse("2020-12-02T17:00:00Z"));
		EventoFormDto evdto2 = new EventoFormDto("Convenção Junina", "Convenção de Vendas", "Chácara Mafalda", Instant.parse("2020-06-02T08:00:00Z"), Instant.parse("2020-06-02T12:00:00Z"));
		
		Evento ev1 = new Evento(evdto1);
		Evento ev2 = new Evento(evdto2);
		
		er.saveAll(Arrays.asList(ev1, ev2));
	}

}
