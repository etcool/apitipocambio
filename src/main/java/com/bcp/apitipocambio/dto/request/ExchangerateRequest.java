package com.bcp.apitipocambio.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangerateRequest {
	private Long userId;
	private String originCurrency;
	private int year;
	private int month;
	private int day;
	private Character type;
	private String destinationCurrency;
	private BigDecimal amount;
}
