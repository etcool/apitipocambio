package com.bcp.apitipocambio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bcp.apitipocambio.model.Exchangerate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<Exchangerate, Long> {
	
	@Query(value = "SELECT * FROM exchangerate "
			+ "WHERE origin_currency=:origin AND destination_currency=:destination AND type=:type "
			+ "ORDER BY year, month, day limit 1", 
			nativeQuery = true)	
	Optional<Exchangerate> lastExchange(
			@Param("origin") String origin, 
			@Param("destination") String destination,
			@Param("type") String type);
}
