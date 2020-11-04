package me.henrique.syscredential.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class ParticipanteControllerTest {

    static String PARTICIPANTE_API = "/participantes";

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Deve criar um participante com sucesso.")
    public void createPartipanteTest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(PARTICIPANTE_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content("");

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("cpf").value("99988877766"))
            .andExpect(MockMvcResultMatchers.jsonPath("nome").value("Jon Doe"))
            .andExpect(MockMvcResultMatchers.jsonPath("email").value("jondoe@email.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("telefone").value("44999998888"))
            .andExpect(MockMvcResultMatchers.jsonPath("camiseta").value("G"))
            .andExpect(MockMvcResultMatchers.jsonPath("regional").value("Umuarama"))
        ;
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do participante.")
    public void createInvalidParticipanteTest() {

    }
}
