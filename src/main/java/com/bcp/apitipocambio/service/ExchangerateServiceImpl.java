package com.bcp.apitipocambio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.apitipocambio.dto.request.ExchangerateRequest;
import com.bcp.apitipocambio.model.Exchangerate;
import com.bcp.apitipocambio.model.User;
import com.bcp.apitipocambio.repository.ExchangeRateRepository;
import com.bcp.apitipocambio.repository.UserRepository;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Service
public class ExchangerateServiceImpl implements ExchangerateService {
	
	@Autowired
	private ExchangeRateRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Exchangerate> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Exchangerate> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Single<String> add(ExchangerateRequest exchangerateRequest) {
		return addExchangerateRepo(exchangerateRequest);
	}
	
	private Single<String> addExchangerateRepo(ExchangerateRequest exchangerateRequest) {
		return Single.create(singleSubscriber -> {
			Optional<User> optionalUser = userRepository.findById(exchangerateRequest.getUserId());
			if (!optionalUser.isPresent()) {
				singleSubscriber.onError(new EntityNotFoundException());
			} else {
				Exchangerate newExchangerate = new Exchangerate();
				newExchangerate.setUser(optionalUser.get());
				newExchangerate.setOriginCurrency(exchangerateRequest.getOriginCurrency());
				newExchangerate.setYear(exchangerateRequest.getYear());
				newExchangerate.setMonth(exchangerateRequest.getMonth());
				newExchangerate.setDay(exchangerateRequest.getDay());
				newExchangerate.setType(exchangerateRequest.getType());
				newExchangerate.setDestinationCurrency(exchangerateRequest.getDestinationCurrency());
				newExchangerate.setAmount(exchangerateRequest.getAmount());
				
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime localDateTime = LocalDateTime.now();				
				newExchangerate.setRegistrationDate(localDateTime);
				
				Exchangerate createdExchangerate = repository.save(newExchangerate);
				singleSubscriber.onSuccess(createdExchangerate.getId().toString());
			}
		});
	}
	
	@Override
	public Completable update(Long id, ExchangerateRequest updateExchangerate) {
		return updateExchangerateRepo(id, updateExchangerate);
	}
	
	private Completable updateExchangerateRepo(Long id, ExchangerateRequest updateExchangerate) {
		return Completable.create(completeSubscriber -> {
			Optional<Exchangerate> optionalExchangerate = repository.findById(id);
			
			if (!optionalExchangerate.isPresent()) {
				completeSubscriber.onError(new EntityNotFoundException());
			} else {
				Optional<User> optionalUser = userRepository.findById(updateExchangerate.getUserId());
				
				if (!optionalUser.isPresent()) {				
					completeSubscriber.onError(new EntityNotFoundException());
				} else {
					Exchangerate exchangerate = optionalExchangerate.get();
					User user = optionalUser.get();
					
					exchangerate.setOriginCurrency(updateExchangerate.getOriginCurrency());
					exchangerate.setUser(user);
					exchangerate.setYear(updateExchangerate.getYear());
					exchangerate.setMonth(updateExchangerate.getMonth());
					exchangerate.setDay(updateExchangerate.getDay());
					exchangerate.setType(updateExchangerate.getType());
					exchangerate.setDestinationCurrency(updateExchangerate.getDestinationCurrency());
					exchangerate.setAmount(updateExchangerate.getAmount());					
					LocalDateTime localDateTime = LocalDateTime.now();		
					exchangerate.setUpdateDate(localDateTime);
					
					repository.save(exchangerate);			
					completeSubscriber.onComplete();
				}				
			}
		});
	}

	@Override
	public Optional<Exchangerate> lastExchange(String origin, String destination, String type) {
		return repository.lastExchange(origin, destination, type);
	}

}
