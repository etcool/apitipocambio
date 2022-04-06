package com.bcp.apitipocambio.service;

import java.util.List;
import java.util.Optional;

import com.bcp.apitipocambio.model.User;

public interface UserService {

	public List<User> findAll();

	public Optional<User> findById(Long id);

	public User save(User user);

	public void deleteById(Long id);
	
}
