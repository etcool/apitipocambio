package com.bcp.apitipocambio.service;

import java.util.List;
import java.util.Optional;

import com.bcp.apitipocambio.model.Query;

public interface QueryService {

	public List<Query> findAll();

	public Optional<Query> findById(Long id);

	public Query save(Query query);

	public void deleteById(Long id);
	
}
