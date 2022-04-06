package com.bcp.apitipocambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcp.apitipocambio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
