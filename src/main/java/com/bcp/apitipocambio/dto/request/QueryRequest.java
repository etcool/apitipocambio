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
public class QueryRequest {
	private BigDecimal amount;
	private String originCurrency;
	private String destinationCurrency;
	private Character type;
}
