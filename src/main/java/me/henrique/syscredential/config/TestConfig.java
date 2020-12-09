package me.henrique.syscredential.config;

import me.henrique.syscredential.controller.request.*;
import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.*;
import me.henrique.syscredential.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RegionalRepository regionalRepository;

	@Autowired
	ParticipanteRepository participanteRepository;

	@Autowired
	EventoRepository eventoRepository;

	@Autowired
	AtividadeRepository atividadeRepository;

	@Override
	public void run(String... args) throws Exception {

		Usuario user1 = new Usuario(new UsuarioRequest("Carlos Henrique", "henriquechsf", "1234"));

		usuarioRepository.saveAll(Arrays.asList(user1));

		Regional reg1 = new Regional(new RegionalRequest(159, "Umuarama", "UMU"));
		Regional reg2 = new Regional(new RegionalRequest(117, "Maringá", "MAR"));

		regionalRepository.saveAll(Arrays.asList(reg1, reg2));

		ParticipanteRequest pdto1 = new ParticipanteRequest("0444658981", "Henrique", "henrique@email.com", "44984414582",
				TamanhoCamiseta.G, true, reg1);
		ParticipanteRequest pdto2 = new ParticipanteRequest("9998887772", "Dani Guerra", "dani@email.com", "99988877",
				TamanhoCamiseta.P, false, reg2);

		Participante p1 = new Participante(pdto1);
		Participante p2 = new Participante(pdto2);

		participanteRepository.saveAll(Arrays.asList(p1, p2));

		EventoRequest evdto1 = new EventoRequest("Convenção Junina 2021", "Reunião com equipe de vendas para apresentação de projetos de campanha", "Hotel Mabu", "Curitiba",
				LocalDateTime.of(2021,4,10,8,0), LocalDateTime.of(2021,4,10,17,0), true);
		EventoRequest evdto2 = new EventoRequest("Convenção Natalina 2020", "Apresentação dos Materiais de Marketing e Campanha Natal 2020", "Chácara Zaeli", "Umuarama",
				LocalDateTime.of(2020,12,2,9,0), LocalDateTime.of(2020,12,2,12,0), false);

		Evento ev1 = new Evento(evdto1);
		Evento ev2 = new Evento(evdto2);

		AtividadeRequest atdto1 = new AtividadeRequest("Palestra de Vendas", "Como aumentar suas vendas",
				LocalTime.of(8, 0), LocalTime.of(9, 0), ev1);
		AtividadeRequest atdto2 = new AtividadeRequest("Degusta Zaeli", "Degustação com os principais produtos",
				LocalTime.of(10, 0), LocalTime.of(12, 0), ev1);

		Atividade at1 = new Atividade(atdto1);
		Atividade at2 = new Atividade(atdto2);

		eventoRepository.saveAll(Arrays.asList(ev1, ev2));
		atividadeRepository.saveAll(Arrays.asList(at1, at2));
	}

}
