package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class GestaoParticipanteServiceTest {

    GestaoParticipanteService service;

    @MockBean
    ParticipanteRepository repository;

    @BeforeEach
    public void setup() {
        this.service = new GestaoParticipanteService(repository);
    }

    @Test
    @DisplayName("Deve salvar um participante")
    public void salvarParticipante() {
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
    public void naoDeveSalvarParticipanteComCpfJaCadastrado() {
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
