package com.roulette.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RouletteDto implements Serializable {
	private Long idRoulette;
	private String state;
}
