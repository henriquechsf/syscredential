package me.henrique.syscredential.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

	@Autowired
	private ParticipanteRepository participanteRepository;
	
	@GetMapping
	public List<Participante> listar() {
		return participanteRepository.findAll();
	}
}
