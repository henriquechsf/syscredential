package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.CredenciamentoRepository;
import me.henrique.syscredential.domain.repository.EventoRepository;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import me.henrique.syscredential.domain.services.impl.CredenciarParticipanteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CredenciarParticipanteServiceTest {

    // SUT
    CredenciarParticipanteService service;

    // MOCKS
    @MockBean
    ParticipanteRepository participanteRepository;
    @MockBean
    EventoRepository eventoRepository;
    @MockBean
    CredenciamentoRepository credenciamentoRepository;

    @BeforeEach
    public void setUp() {
        this.service = new CredenciarParticipanteServiceImpl(eventoRepository, participanteRepository, credenciamentoRepository);
    }

    @Test
    @DisplayName("Deve credenciar um participante com sucesso")
    public void credenciarParticipanteTest() {
        // cenário
        int idEvento = 1;
        String credencial = "02805230094";

        Evento evento = createEvento();
        Participante participante = createParticipante();

        Mockito.when(eventoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(evento));
        Mockito.when(participanteRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.of(participante));
        Mockito.when(credenciamentoRepository.findParticipanteCredenciado(evento, participante)).thenReturn(Optional.empty());

        Credenciamento credenciamento = new Credenciamento();
        credenciamento.setParticipante(participante);
        credenciamento.setEvento(evento);
        credenciamento.setInstante(LocalDateTime.now());

        Mockito.when(credenciamentoRepository.save(credenciamento)).thenReturn(Credenciamento.builder().id(1L).build());

        // execução
        Credenciamento sut = service.credenciarParticipante(idEvento, credencial);

        // verificação
//        Assertions.assertThat(sut.getId()).isEqualTo(1L);
//        assertThat(sut.getInstante()).isEqualTo(credenciamento.getInstante());
//        assertThat(sut.getEvento()).isEqualTo(credenciamento.getEvento());
//        assertThat(sut.getParticipante()).isEqualTo(credenciamento.getParticipante());

//        Mockito.verify(credenciamentoRepository, Mockito.times(1)).save(credenciamento);
    }

    private Evento createEvento() {
        return Evento.builder()
                .id(1)
                .titulo("Evento Teste")
                .descricao("Descricao do Evento Teste")
                .local("Local de Eventos")
                .cidade("Umuarama")
                .ativo(true)
                .build();
    }

    private Participante createParticipante() {
        return Participante.builder()
                .id(1)
                .cpf("02805230094")
                .nome("Fulano da Silva")
                .email("fulano@email.com")
                .telefone("(44)98888-7777")
                .camiseta(TamanhoCamiseta.G)
                .ativo(true)
                .regional(Regional.builder().id(1).build())
                .build();
    }

    private Credenciamento createCredenciamento() {
        return Credenciamento.builder()
                .instante(LocalDateTime.now())
                .evento(createEvento())
                .participante(createParticipante())
                .build();
    }
}
