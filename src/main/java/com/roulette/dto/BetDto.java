package com.roulette.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BetDto implements Serializable{
	Long idBet;
	Long idRoulette;
	Long amount;
	Long number;
	String color;
	String state;
	Double gain;
}
