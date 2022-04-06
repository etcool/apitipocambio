package com.bcp.apitipocambio.service;

import java.util.List;
import java.util.Optional;

import com.bcp.apitipocambio.dto.request.ExchangerateRequest;
import com.bcp.apitipocambio.model.Exchangerate;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface ExchangerateService {
	
	Single<String> add(ExchangerateRequest exchangerateRequest);
	
	Completable update(Long id, ExchangerateRequest exchangerateRequest);
	
	public Optional<Exchangerate> lastExchange(String origin, String destination, String type);

	public List<Exchangerate> findAll();

	public Optional<Exchangerate> findById(Long id);

	public void deleteById(Long id);
	
}
