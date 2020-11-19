package com.roulette.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roulette.common.ConstantsEnum;
import com.roulette.dto.BetDto;
import com.roulette.dto.RouletteDto;
import com.roulette.repository.BetInterface;
import com.roulette.repository.RouletteInterface;

@Service
public class BetServiceImplement implements BetServiceInterface {
	@Autowired
	BetInterface betInterface;
	@Autowired
	RouletteServiceInterface rouletteServiceInterface;
	private final int maxAmount = 10000;
	private final int minAmount = 0;

	@Override
	public String placeBet(BetDto betDto) {
		String response = validationBet(betDto);
		if (response.equals(ConstantsEnum.OK.toString())) {
			betDto.setIdBet(getNextIdBet());
			return betInterface.placeBet(betDto);
		}
		return response;
	}
	@Override
	public List<BetDto> closeBet(Long idRoulette) {
		rouletteServiceInterface.closeRoulette(idRoulette);
		List<BetDto> listBetDto = betInterface.findAllBets();
		
		return getClosedBetsList(listBetDto, idRoulette);
	}
	
	private List<BetDto> getClosedBetsList(List<BetDto> listBetDto, Long idRoulette){
		List<BetDto> listBets = new ArrayList<BetDto>();
		int winningNumber = getWinningNumber();
		String winningColor = getWinningColor(winningNumber);		
		int index =0;
		for (BetDto betDto : listBetDto) {
			Long idRouletteBet = betDto.getIdRoulette();
			if (idRouletteBet.toString().equals(idRoulette.toString())) 
				listBets.add(listBetDto.get(index));				
			index++;	
		}
		
		return getWinners(listBetDto, winningNumber, winningColor);
	}
	
	private List<BetDto> getWinners(List<BetDto> listBets, int winningNumber, String winningColor){
		//List<BetDto> listWinners = new ArrayList<BetDto>();
		for (BetDto betDto : listBets) {
			Long numberBet=betDto.getNumber()==null?-1:betDto.getNumber();
			String colorBet=betDto.getColor()==null?"":betDto.getColor().toUpperCase(); 
			if(numberBet==winningNumber) {
				betDto.setGain((double) (betDto.getAmount()*5));
				betDto.setState("Ganador");
			}
			if (colorBet.equals("ROJO") || colorBet.equals("NEGRO")) {
				betDto.setGain(betDto.getAmount()*1.8);
				betDto.setState("Ganador");
			}
				
		}
		return listBets;
	}
	
	private int getWinningNumber() {
		return (int) (Math.random()*36);
	}
	
	private String getWinningColor(int winningNumber) {
		String winningColor = "";
		if (winningNumber%2==0) {
			winningColor = "ROJO";
		}
		else
			winningColor = "BLACK";
		
		return winningColor;
	}

	private String validateRoulette(Long idRoulette) {
		RouletteDto rouletteDto = rouletteServiceInterface.findByIdRoulette(idRoulette);
		if (rouletteDto == null)
			return "La ruleta seleccionada no existe";
		if (!rouletteDto.getState().equals(ConstantsEnum.OPEN.toString()))
			return "La ruleta seleccionada no se encuentra abierta";

		return ConstantsEnum.OK.toString();
	}

	private Long getNextIdBet() {
		List<BetDto> listBetDto = betInterface.findAllBets();
		int sizeList = listBetDto.size();
		if (sizeList > 0)
			return listBetDto.get(sizeList - 1).getIdRoulette() + 1;
		else
			return 1L;
	}

	private String validationBet(BetDto betDto) {
		if (betDto.getNumber() != null && betDto.getColor() != null)
			return "Solo puede realizar una apuesta por numero o color";
		else
			return validateAmountBet(betDto);
	}

	private String validateAmountBet(BetDto betDto) {
		if (betDto.getAmount() == null)
			return "Debe de ingresar un valor";
		if (betDto.getAmount() > maxAmount)
			return "El monto ingresado supera el limite de: " + maxAmount + " USD";
		if (betDto.getAmount() < minAmount)
			return "El monto ingresadoes menor a: " + minAmount;
		
		return validateNumberBet(betDto);
	}

	private String validateNumberBet(BetDto betDto) {
		if (betDto.getNumber() != null) {
			if (betDto.getNumber() < 0 || betDto.getNumber() > 36)
				return "El número registrado no esta entre 0-36";
		}
		
		return validateColotBet(betDto);
	}

	private String validateColotBet(BetDto betDto) {
		if (betDto.getColor()!= null) {
			if (!betDto.getColor().toUpperCase().equals("ROJO") && !betDto.getColor().toUpperCase().equals("NEGRO"))
				return "El color ingresado no es valido";
		}
		
		return validateRoulette(betDto.getIdRoulette());
	}
}
