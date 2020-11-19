package com.roulette.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.roulette.common.Constants;
import com.roulette.dto.RouletteDto;
@Service
public class RouletteImplement implements RouletteInterface {
	@Autowired
	RedisTemplate redisTemplate;
	@Override
	public Long createRoulete(RouletteDto rouletteDto) {
		try {			
			redisTemplate.opsForHash().put(Constants.ROULETTE.toString(), rouletteDto.getIdRoulette(), rouletteDto);
			return rouletteDto.getIdRoulette();
		} catch (Exception e) {
			return 0L;
		}
	}
	@Override
	public List<RouletteDto> findAllRoulette() {
		return redisTemplate.opsForHash().values(Constants.ROULETTE.toString());
	}
	@Override
	public RouletteDto findByIdRoulette(Long idRoulette) {
		return (RouletteDto) redisTemplate.opsForHash().get(Constants.ROULETTE.toString(), idRoulette);
	}
	@Override
	public String deleteRoulette(Long idRoulette) {
		try {
			redisTemplate.opsForHash().delete(Constants.ROULETTE.toString(), idRoulette);
			return "Se elimino con exito la ruleta: "+idRoulette;
		} catch (Exception e) {
			return "Error al eliminar la ruleta: "+idRoulette;
		}
	}
	@Override
	public String openRoulette(RouletteDto rouletteDto) {
		try {
			redisTemplate.opsForHash().put(Constants.ROULETTE.toString(), rouletteDto.getIdRoulette(), rouletteDto);
			return "Ruleta abierta";
		} catch (Exception e) {
			return "Error al abrir la ruleta";
		}
	}
}
