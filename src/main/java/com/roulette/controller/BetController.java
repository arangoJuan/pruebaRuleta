package com.roulette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.roulette.dto.BetDto;
import com.roulette.service.BetServiceInterface;

import lombok.experimental.Helper;

@RestController
public class BetController {
	@Autowired
	BetServiceInterface iBetService;
	
	@PostMapping("/placeBet")
	public String placeBet(@RequestHeader Long idUser, @RequestBody BetDto betDto) {
		if (idUser!=null) 
			return iBetService.placeBet(betDto);
		else
			return "El usuario no se encuetra regitrado";
		
	}
	
	@PostMapping("/closeBet/{idRoulette}")
	public List<BetDto> closeBet(@PathVariable Long idRoulette){
		return iBetService.closeBet(idRoulette);
	}
	
}
