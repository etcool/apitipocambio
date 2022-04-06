package com.bcp.apitipocambio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.apitipocambio.model.Query;
import com.bcp.apitipocambio.repository.QueryRepository;

@Service
public class QueryServiceImpl implements QueryService {
	
	@Autowired
	private QueryRepository repository;

	@Override
	public List<Query> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Query> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Query save(Query query) {
		return repository.save(query);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);		
	}
	
}
