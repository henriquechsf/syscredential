package me.henrique.syscredential.config;

import me.henrique.syscredential.api.dto.request.*;
import me.henrique.syscredential.domain.enums.Perfil;
import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.*;
import me.henrique.syscredential.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	BCryptPasswordEncoder bcrypt;

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

		Usuario user1 = new Usuario(new UsuarioRequest("Carlos Henrique", "carlos.ferreira", bcrypt.encode("1234")));
		user1.addPerfil(Perfil.ROLE_ADMIN);

		Usuario user2 = new Usuario(new UsuarioRequest("Daniele Guerra", "daniele.guerra", bcrypt.encode("123456")));

		usuarioRepository.saveAll(Arrays.asList(user1, user2));

		Regional reg1 = Regional.builder().cod(159).nome("Umuarama").sigla("PRO-UMU").build();
		Regional reg2 = Regional.builder().cod(117).nome("Maringá").sigla("PRO-MAR").build();

		regionalRepository.saveAll(Arrays.asList(reg1, reg2));

		Participante p1 = Participante.builder().cpf("02805230094").nome("Fulano da Silva").email("fulano@email.com").telefone("(44)98888-7777").camiseta(TamanhoCamiseta.G).ativo(true).regional(reg1).build();
		Participante p2 = Participante.builder().cpf("98673541093").nome("Ciclano de Souza").email("ciclano@email.com").telefone("(44)99999-7788").camiseta(TamanhoCamiseta.P).ativo(true).regional(reg2).build();
		Participante p3 = Participante.builder().cpf("04446568981").nome("Carlos Henrique de S. Ferreira").email("henrique@email.com").telefone("(44)98888-7777").camiseta(TamanhoCamiseta.G).ativo(true).regional(reg1).build();

		participanteRepository.saveAll(Arrays.asList(p1, p2, p3));

		Evento ev1 = Evento.builder()
				.titulo("Natal Mágico 2020")
				.descricao("Convenção de Vendas Natal 2020")
				.local("Hotel Mabu").cidade("Curitiba")
				.inicio(LocalDateTime.parse("2021-05-28T19:00"))
				.termino(LocalDateTime.parse("2021-05-28T22:00"))
				.ativo(true)
				.build();

		Evento ev2 = Evento.builder()
				.titulo("Arraiá Junino 2021")
				.descricao("Convenção de Vendas Junina 2021")
				.local("Cataratas")
				.cidade("Foz do Iguaçú")
				.inicio(LocalDateTime.parse("2021-05-28T19:00"))
				.termino(LocalDateTime.parse("2021-05-28T22:00"))
				.ativo(false)
				.build();

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
