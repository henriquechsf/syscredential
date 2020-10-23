package me.henrique.syscredential.config;

import java.time.Instant;
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
	RegionalRepository rr;

	@Autowired
	ParticipanteRepository pr;

	@Autowired
	EventoRepository er;

	@Autowired
	AtividadeRepository ar;

	@Override
	public void run(String... args) throws Exception {

		RegionalInput regdto1 = new RegionalInput(1, "Umuarama", "UMU");
		RegionalInput regdto2 = new RegionalInput(2, "Maringá", "MAR");

		Regional reg1 = new Regional(regdto1);
		Regional reg2 = new Regional(regdto2);

		rr.saveAll(Arrays.asList(reg1, reg2));

		ParticipanteInput pdto1 = new ParticipanteInput("0444658981", "Henrique", "henrique@email.com", "44984414582",
				TamanhoCamiseta.G, reg1);
		ParticipanteInput pdto2 = new ParticipanteInput("9998887772", "Dani Guerra", "dani@email.com", "99988877",
				TamanhoCamiseta.P, reg2);

		Participante p1 = new Participante(pdto1);
		Participante p2 = new Participante(pdto2);

		pr.saveAll(Arrays.asList(p1, p2));

		AtividadeInput atdto1 = new AtividadeInput("Palestra de Vendas", "Como aumentar suas vendas",
				Instant.parse("2020-12-02T08:00:00Z"), Instant.parse("2020-12-02T10:00:00Z"));
		AtividadeInput atdto2 = new AtividadeInput("Degusta Zaeli", "Degustação com os principais produtos",
				Instant.parse("2020-12-02T10:00:00Z"), Instant.parse("2020-12-02T12:00:00Z"));

		Atividade at1 = new Atividade(atdto1);
		Atividade at2 = new Atividade(atdto2);

		ar.saveAll(Arrays.asList(at1, at2));

		EventoInput evdto1 = new EventoInput("Convenção de Natal", "Convenção de Vendas", "Hotel Mabu - Curitiba",
				Instant.parse("2020-12-02T08:00:00Z"), Instant.parse("2020-12-02T17:00:00Z"));
		EventoInput evdto2 = new EventoInput("Convenção Junina", "Convenção de Vendas", "Chácara Mafalda",
				Instant.parse("2020-06-02T08:00:00Z"), Instant.parse("2020-06-02T12:00:00Z"));

		Evento ev1 = new Evento(evdto1);
		Evento ev2 = new Evento(evdto2);

		/* FALTA CORRIGIR ASSOCIAÇÃO */
		ev1.adicionarAtividade(at1);
		ev1.adicionarAtividade(at2);
		ev2.adicionarAtividade(at2);
		er.saveAll(Arrays.asList(ev1, ev2));
	}

}
