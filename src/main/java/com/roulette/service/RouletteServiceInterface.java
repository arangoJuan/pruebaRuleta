package com.roulette.service;

import java.util.List;

import com.roulette.dto.RouletteDto;

public interface RouletteServiceInterface {
	public Long createRoulete();
	public List<RouletteDto> findAllRoulette();
	public RouletteDto findByIdRoulette(Long idRoulette);
	public String deleteRoulette(Long idRoulette);
	public String openRoulette(Long idRoulette);
}
