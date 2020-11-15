package me.henrique.syscredential.api.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import me.henrique.syscredential.api.dto.EventoInput;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.services.GestaoEventoService;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Controller
@RequestMapping("/")
public class EventoController {

	@Autowired
	private GestaoEventoService service;

	@GetMapping("/eventos")
	public ModelAndView getEventos() {
		ModelAndView mv = new ModelAndView("eventos");
		List<Evento> eventos = service.listar();
		mv.addObject("eventos", eventos);
		return mv;
	}

	@CrossOrigin
	@GetMapping("/api/eventos")
	public List<Evento> listar() {
		return service.listar();
	}

	@CrossOrigin
	@GetMapping("/api/eventos/{id}")
	public ResponseEntity<Evento> listarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.listarPorId(id));
	}

	@Transactional
	@CrossOrigin
	@PostMapping("/api/eventos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evento> adicionar(@Valid @RequestBody EventoInput dto) {
		Evento evento = new Evento(dto);
		return ResponseEntity.ok(service.salvar(evento));
	}

	@Transactional
	@CrossOrigin
	@PutMapping("/api/eventos/{id}")
	public ResponseEntity<Evento> atualizar(@PathVariable Integer id, @RequestBody EventoInput dto) {
		Evento evento = new Evento(dto);
		return ResponseEntity.ok(service.atualizar(id, evento));
	}

	@CrossOrigin
	@DeleteMapping("/api/eventos/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
}
