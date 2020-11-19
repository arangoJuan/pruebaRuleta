package com.roulette.repository;

import java.util.List;
import com.roulette.dto.RouletteDto;

public interface RouletteInterface {
	public Long createRoulete(RouletteDto rouletteDto);
	public List<RouletteDto> findAllRoulette();
	public RouletteDto findByIdRoulette(Long idRoulette);
	public String deleteRoulette(Long idRoulette);
	public String openRoulette(RouletteDto rouletteDto);
}
