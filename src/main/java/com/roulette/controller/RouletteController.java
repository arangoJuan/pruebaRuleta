package com.roulette.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roulette.dto.RouletteDto;
import com.roulette.repository.RouletteInterface;
import com.roulette.service.RouletteServiceInterface;

@RestController
public class RouletteController {
	@Autowired
	RouletteServiceInterface iRouletteService;

	@PostMapping("/createRoulette")
	public ResponseEntity<Long> createRoulette() {
		return ResponseEntity.ok(iRouletteService.createRoulete());
	}
	@GetMapping("/findAllRoulette")
	public ResponseEntity<List<RouletteDto>> findAllRoulette(){
		return ResponseEntity.ok(iRouletteService.findAllRoulette());
	}
	@GetMapping("/findByIdRoulette/{idRoulette}")
	public ResponseEntity<RouletteDto> findByIdRoulette(@PathVariable Long idRoulette){
		return ResponseEntity.ok(iRouletteService.findByIdRoulette(idRoulette));
	}
	@PostMapping("/openRoulette/{idRoulette}")
	public ResponseEntity<String> openRoulette(@PathVariable Long idRoulette){
		return ResponseEntity.ok(iRouletteService.openRoulette(idRoulette));
	}
	@PostMapping("/deleteRoulette/{idRoulette}")
	public ResponseEntity<String> deleteRoulette(@PathVariable Long idRoulette) {
		return ResponseEntity.ok(iRouletteService.deleteRoulette(idRoulette));
	}
}
