package me.henrique.syscredential.config;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import me.henrique.syscredential.api.dto.AtividadeInput;
import me.henrique.syscredential.api.dto.EventoInput;
import me.henrique.syscredential.api.dto.ParticipanteInput;
import me.henrique.syscredential.api.dto.RegionalInput;
import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.AtividadeRepository;
import me.henrique.syscredential.domain.repository.EventoRepository;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import me.henrique.syscredential.domain.repository.RegionalRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

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

		Regional reg1 = new Regional(new RegionalInput(1, "Umuarama", "UMU"));
		Regional reg2 = new Regional(new RegionalInput(2, "Maringá", "MAR"));

		regionalRepository.saveAll(Arrays.asList(reg1, reg2));

		ParticipanteInput pdto1 = new ParticipanteInput("0444658981", "Henrique", "henrique@email.com", "44984414582",
				TamanhoCamiseta.G, reg1);
		ParticipanteInput pdto2 = new ParticipanteInput("9998887772", "Dani Guerra", "dani@email.com", "99988877",
				TamanhoCamiseta.P, reg2);

		Participante p1 = new Participante(pdto1);
		Participante p2 = new Participante(pdto2);

		participanteRepository.saveAll(Arrays.asList(p1, p2));

		AtividadeInput atdto1 = new AtividadeInput("Palestra de Vendas", "Como aumentar suas vendas",
				LocalDateTime.of(2020, 12, 2, 8, 0), LocalDateTime.of(2020, 12, 2, 9, 0));
		AtividadeInput atdto2 = new AtividadeInput("Degusta Zaeli", "Degustação com os principais produtos",
				LocalDateTime.of(2020, 12, 2, 10, 0), LocalDateTime.of(2020, 12, 2, 12, 0));

		Atividade at1 = new Atividade(atdto1);
		Atividade at2 = new Atividade(atdto2);

		EventoInput evdto1 = new EventoInput("Convenção de Natal", "Convenção de Vendas", "Hotel Mabu - Curitiba",
			LocalDateTime.of(2020,12,2,8,0), LocalDateTime.of(2020,12,2,18,0));
		EventoInput evdto2 = new EventoInput("Convenção Junina", "Convenção de Vendas", "Chácara Mafalda",
			LocalDateTime.of(2020,12,2,8,0), LocalDateTime.of(2020,12,2,18,0));

		Evento ev1 = new Evento(evdto1);
		Evento ev2 = new Evento(evdto2);

		at1.setEvento(ev1);
		at2.setEvento(ev1);

		eventoRepository.saveAll(Arrays.asList(ev1, ev2));
		atividadeRepository.saveAll(Arrays.asList(at1, at2));
	}

}
