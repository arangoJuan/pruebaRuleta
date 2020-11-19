package com.roulette.service;


import java.util.List;

import com.roulette.dto.BetDto;

public interface BetServiceInterface {
	public String placeBet(BetDto betDto);
	public List<BetDto> closeBet(Long idRoulette);
}
