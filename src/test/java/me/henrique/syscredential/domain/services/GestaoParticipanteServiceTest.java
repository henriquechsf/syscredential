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
    public void setUp() {
        this.service = new GestaoParticipanteService(repository);
    }

    @Test
    @DisplayName("Deve salvar um participante")
    public void shouldSaveParticipantTest() {
        // cenario
        Participante participante =  createParticipanteDTO();
        Mockito.when(repository.save(participante)).thenReturn(createParticipanteMockComID());

        // execução
        Participante sut = service.save(participante);

        // verificação
        assertThat(sut.getId()).isNotNull();
        assertThat(sut.getCpf()).isEqualTo(participante.getCpf());
        assertThat(sut.getNome()).isEqualTo(participante.getNome());
        assertThat(sut.getEmail()).isEqualTo(participante.getEmail());
        assertThat(sut.getTelefone()).isEqualTo(participante.getTelefone());
        assertThat(sut.getCamiseta()).isEqualTo(participante.getCamiseta());
        assertThat(sut.getRegional().getId()).isEqualTo(participante.getRegional().getId());

        Mockito.verify(repository, Mockito.times(1)).save(participante);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar um participante com CPF já cadastrado")
    public void shouldNotSaveAParticipantWithDuplicatedCpf() {
        // cenario
        Participante participante = createParticipanteDTO();
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
    @DisplayName("Deve obter um participante pelo CPF")
    public void getParticipantByCpfTest() {
        // cenario
        String cpf = "02805230094";
        Participante participante = createParticipanteDTO();
        Mockito.when(repository.findByCpf(cpf)).thenReturn(Optional.of(participante));

        // execução
        Participante sut = service.getByCpf(cpf);

        // verificação
        assertThat(sut).isNotNull();
        assertThat(sut.getId()).isEqualTo(participante.getId());
        assertThat(sut.getCpf()).isEqualTo(participante.getCpf());
        assertThat(sut.getNome()).isEqualTo(participante.getNome());

        Mockito.verify(repository, Mockito.times(1)).findByCpf(cpf);
    }

    @Test
    @DisplayName("Deve atualizar um participante")
    public void updateParticipantTest() {
        // cenario
        int id = 1;
        Participante participante = createParticipanteDTO();
        participante.setId(id);

        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(repository.save(participante)).thenReturn(createParticipanteMockComID());

        // execução
        Participante sut = service.update(id, participante);

        // verificação
        assertThat(sut.getId()).isEqualTo(participante.getId());
        assertThat(sut.getCpf()).isEqualTo(participante.getCpf());
        assertThat(sut.getNome()).isEqualTo(participante.getNome());

        Mockito.verify(repository, Mockito.times(1)).save(participante);
    }

    @Test
    @DisplayName("Deve obter um participante por Id")
    public void getParticipantByIdTest() {
        // cenario
        int id = 1;
        Participante participante = createParticipanteDTO();
        participante.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(participante));

        // execução
        Participante sut = service.getById(id);

        // verificação
        assertThat(sut).isNotNull();
        assertThat(sut.getId()).isEqualTo(participante.getId());
        assertThat(sut.getCpf()).isEqualTo(participante.getCpf());
        assertThat(sut.getNome()).isEqualTo(participante.getNome());

        Mockito.verify(repository, Mockito.times(1)).findById(id);
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
    @DisplayName("Deve excluir um participante")
    public void deleteParticipantTest() {
        // cenario
        int id = 1;
        Participante participante = createParticipanteDTO();
        participante.setId(id);
        Mockito.when(repository.existsById(Mockito.anyInt())).thenReturn(true);

        // execução
        Assertions.assertDoesNotThrow(() -> service.delete(id));

        //verificação
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar um exceção ao excluir um participante com ID inexistente")
    public void deleteInvalidParticipantTest() {
        int id = 1;

        Assertions.assertThrows(EntityNotFoundException.class, () -> service.delete(id));

        Mockito.verify(repository, Mockito.never()).deleteById(id);
    }


    private Participante createParticipanteDTO() {
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

    private Participante createParticipanteMockComID() {
        Participante participante = createParticipanteDTO();
        participante.setId(1);
        return participante;
    }
}
