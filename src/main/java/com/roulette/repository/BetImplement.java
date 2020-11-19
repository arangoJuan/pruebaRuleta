package com.roulette.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.roulette.common.ConstantsEnum;
import com.roulette.dto.BetDto;

@Service
public class BetImplement implements BetInterface {
	@Autowired
	RedisTemplate redisTemplate;
	@Override
	public String placeBet(BetDto betDto) {
		try {
			redisTemplate.opsForHash().put(ConstantsEnum.BET.toString(), betDto.getIdBet(), betDto);
			return "Apuesta realizada con exito!";
		} catch (Exception e) {
			return "Error al realizar la apuesta!";
		}
	}	
	
	@Override
	public List<BetDto> findAllBets() {
		return redisTemplate.opsForHash().values(ConstantsEnum.BET.toString());
	}
}
