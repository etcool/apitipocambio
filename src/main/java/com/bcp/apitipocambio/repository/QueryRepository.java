package com.bcp.apitipocambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcp.apitipocambio.model.Query;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
	

}
