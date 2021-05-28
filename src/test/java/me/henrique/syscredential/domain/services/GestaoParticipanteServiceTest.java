package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class GestaoParticipanteServiceTest {

    // SUT
    GestaoParticipanteService service;

    @MockBean
    ParticipanteRepository repository;

    @BeforeEach
    public void setup() {
        this.service = new GestaoParticipanteService(repository);
    }

    @Test
    @DisplayName("Deve salvar um participante")
    public void shouldSaveParticipantTest() {
        // cenario
        Participante participante =  createParticipante();

        Mockito.when(repository.save(participante))
                .thenReturn(
                        Participante.builder()
                            .id(1)
                            .cpf("02805230094")
                            .nome("Fulano da Silva")
                            .email("fulano@email.com")
                            .telefone("(44)98888-7777")
                            .camiseta(TamanhoCamiseta.G)
                            .ativo(true)
                            .regional(Regional.builder().id(1).build())
                            .build()
                );

        // execução
        Participante participanteSalvo = service.save(participante);

        // verificação
        assertThat(participanteSalvo.getId()).isNotNull();
        assertThat(participanteSalvo.getCpf()).isEqualTo("02805230094");
        assertThat(participanteSalvo.getNome()).isEqualTo("Fulano da Silva");
        assertThat(participanteSalvo.getEmail()).isEqualTo("fulano@email.com");
        assertThat(participanteSalvo.getTelefone()).isEqualTo("(44)98888-7777");
        assertThat(participanteSalvo.getCamiseta()).isEqualTo(TamanhoCamiseta.G);
        assertThat(participanteSalvo.getRegional().getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar um participante com CPF já cadastrado")
    public void shouldNotSaveAParticipantWithDuplicatedCpf() {
        // cenario
        Participante participante = createParticipante();
        Mockito.when(repository.existsByCpf(Mockito.anyString())).thenReturn(true);

        // execução
        Throwable exception = catchThrowable(() -> service.save(participante));

        // verificações
        assertThat(exception)
                .isInstanceOf(DomainException.class)
                .hasMessage("CPF já cadastrado");

        Mockito.verify(repository, Mockito.never()).save(participante);
    }

    @Test
    @DisplayName("Deve lançar uma excessão ao buscar um participante com ID não cadastrado")
    public void participantNotFoundException() {
        // cenario
        int id = 1;
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // execução
        Throwable exception = catchThrowable(() -> service.getById(id));

        // verificação
        assertThat(exception)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("ID não encontrado");
    }

    @Test
    @DisplayName("Deve obter um participante por Id")
    public void getParticipantByIdTest() {
        // cenario
        int id = 1;
        Participante participante = createParticipante();
        participante.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(participante));

        // execução
        Participante foundParticipant = service.getById(id);

        // verificação
        assertThat(foundParticipant).isNotNull();
        assertThat(foundParticipant.getId()).isEqualTo(id);
        assertThat(foundParticipant.getNome()).isEqualTo(participante.getNome());
    }

    @Test
    @DisplayName("Deve obter um participante pelo CPF")
    public void getParticipantByCpfTest() {
        // cenario
        String cpf = "02805230094";
        Participante participante = createParticipante();
        Mockito.when(repository.findByCpf(cpf)).thenReturn(Optional.of(participante));

        // execução
        Participante foundParticipant = service.getByCpf(cpf);

        // verificação
        assertThat(foundParticipant).isNotNull();
        assertThat(foundParticipant.getId()).isEqualTo(participante.getId());
        assertThat(foundParticipant.getCpf()).isEqualTo(participante.getCpf());
        assertThat(foundParticipant.getNome()).isEqualTo(participante.getNome());

        Mockito.verify(repository, Mockito.times(1)).findByCpf(cpf);
    }

    @Test
    @DisplayName("Deve excluir um participante")
    public void deleteParticipantTest() {
        Participante participante = Participante.builder().id(1).build();

        Mockito.when(repository.existsById(Mockito.anyInt())).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> service.delete(1));
        Mockito.verify(repository, Mockito.times(1)).deleteById(1);
    }

    private Participante createParticipante() {
        return Participante.builder()
                .cpf("02805230094")
                .nome("Fulano da Silva")
                .email("fulano@email.com")
                .telefone("(44)98888-7777")
                .camiseta(TamanhoCamiseta.G)
                .ativo(true)
                .regional(Regional.builder().id(1).build())
                .build();
    }
}
