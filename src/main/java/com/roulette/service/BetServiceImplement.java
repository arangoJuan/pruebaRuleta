package com.roulette.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roulette.common.Constants;
import com.roulette.dto.BetDto;
import com.roulette.dto.RouletteDto;
import com.roulette.repository.BetInterface;
import com.roulette.repository.RouletteInterface;

@Service
public class BetServiceImplement implements BetServiceInterface {
	@Autowired
	BetInterface betInterface;
	@Autowired
	RouletteInterface rouletteInterface;

	@Override
	public String performBet(BetDto betDto) {
		String response = validationBet(betDto);
		if (response.equals(Constants.OK.toString())) {
			return betInterface.performBet(betDto);
		}
		return response;
	}
	
	private String validationBet(BetDto betDto) {
		String response=Constants.OK.toString();
		if (betDto.getNumber()>0 && !betDto.getColor().equals("")) {
			return "Solo puede realizar una apuesta por numero o color";
		}
		if (betDto.getAmount()>10000) {
			return "El monto ingresado supera el limite de 10.000 USD";
		}	
		if (betDto.getNumber()<0 || betDto.getNumber()>36) {
			return "El numero registrado no esta entre 0-36";
		}
		response = validateRoulette(betDto.getIdRoulette());
		return response;
	}
	
	private String validateRoulette(Long idRoulette) {
		RouletteDto rouletteDto = rouletteInterface.findByIdRoulette(idRoulette);
		if (!rouletteDto.getState().equals(Constants.OPEN.toString()))
			return "La ruleta seleccionada no se encuentra abierta";
		return Constants.OK.toString();
	}
}
