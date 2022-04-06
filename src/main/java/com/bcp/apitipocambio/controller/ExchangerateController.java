package com.bcp.apitipocambio.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.apitipocambio.dto.request.ExchangerateRequest;
import com.bcp.apitipocambio.dto.request.QueryRequest;
import com.bcp.apitipocambio.dto.response.BaseWebResponse;
import com.bcp.apitipocambio.model.Exchangerate;
import com.bcp.apitipocambio.service.ExchangerateService;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@RestController
@RequestMapping("/api/tipocambio")
public class ExchangerateController {
	
	@Autowired
	private ExchangerateService exchangerateService;
	
	@PostMapping("/query")
	public ResponseEntity<?> converter(
				@RequestBody QueryRequest queryRequest) {
		
		Optional<Exchangerate> exchangerate = exchangerateService.lastExchange(
				queryRequest.getOriginCurrency(), 
				queryRequest.getDestinationCurrency(),
				queryRequest.getType().toString());
		
		Map<String, Object> data = new HashMap<>();		
		
		if (exchangerate.isPresent()) {
			BigDecimal amountResult = queryRequest.getAmount().multiply(exchangerate.get().getAmount());
			data.put("code", "0");
			data.put("amount", amountResult);
			data.put("type", exchangerate.get().getType().equals("C") ? "Compra": "Venta");
			data.put("rate", exchangerate.get().getAmount());
			return ResponseEntity.ok(data);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public Single<ResponseEntity<BaseWebResponse>> add(
				@RequestBody ExchangerateRequest exchangerateRequest) {
		return exchangerateService
				.add(exchangerateRequest)
				.subscribeOn(Schedulers.io())
				.map(s -> ResponseEntity.created(URI.create("/api/tipocambio/" + s))
                .body(BaseWebResponse.successNoData()));
	}
	
	@PutMapping("/{id}")
	public Single<ResponseEntity<BaseWebResponse>> update(
				@PathVariable(value = "id") Long id, 
				@RequestBody ExchangerateRequest updateExchangerate) {
		System.out.println("id" + id);
		return exchangerateService
				.update(id, updateExchangerate)
				.subscribeOn(Schedulers.io())
				.toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
	}
	
}
