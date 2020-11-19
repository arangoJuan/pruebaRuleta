package com.roulette.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roulette.common.ConstantsEnum;
import com.roulette.dto.RouletteDto;
import com.roulette.repository.RouletteInterface;
@Service
public class RouletteServiceImplements implements RouletteServiceInterface {
	@Autowired
	RouletteInterface rouletteInterface;

	@Override
	public Long createRoulete() {
		RouletteDto rouletteDto = new RouletteDto();
		rouletteDto.setIdRoulette(getNextIdRoulette());
		rouletteDto.setState(ConstantsEnum.CLOSE.toString());
		
		return rouletteInterface.createRoulete(rouletteDto);
	}

	@Override
	public List<RouletteDto> findAllRoulette() {
		return rouletteInterface.findAllRoulette();
	}

	@Override
	public RouletteDto findByIdRoulette(Long idRoulette) {
		return rouletteInterface.findByIdRoulette(idRoulette);
	}

	@Override
	public String deleteRoulette(Long idRoulette) {
		return rouletteInterface.deleteRoulette(idRoulette);
	}

	@Override
	public String openRoulette(Long idRoulette) {
		RouletteDto rouletteDto = new RouletteDto();
		rouletteDto.setIdRoulette(idRoulette);
		rouletteDto.setState(ConstantsEnum.OPEN.toString());
		
		return rouletteInterface.openRoulette(rouletteDto);
	}
	@Override
	public String closeRoulette(Long idRoulette) {
		RouletteDto rouletteDto = new RouletteDto();
		rouletteDto.setIdRoulette(idRoulette);
		rouletteDto.setState(ConstantsEnum.CLOSE.toString());
		
		return rouletteInterface.openRoulette(rouletteDto);
	}
	private Long getNextIdRoulette() {
		List<RouletteDto> listRoulette = findAllRoulette();
		int sizeList = listRoulette.size();
		if (sizeList>0)
			return listRoulette.get(sizeList-1).getIdRoulette()+1;
		else
			return 1L;
	}

}
