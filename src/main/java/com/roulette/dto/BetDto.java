package com.roulette.dto;

import lombok.Data;

@Data
public class BetDto {
	Long idBet;
	Long idRoulette;
	Long amount;
	Long number;
	String color;
}
