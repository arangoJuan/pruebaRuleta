package com.roulette.repository;

import java.util.List;

import com.roulette.dto.BetDto;

public interface BetInterface {
	public String placeBet(BetDto betDto);
	public List<BetDto> findAllBets();
}
