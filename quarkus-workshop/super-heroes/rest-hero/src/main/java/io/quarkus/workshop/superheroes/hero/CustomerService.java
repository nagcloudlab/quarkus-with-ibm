package io.quarkus.workshop.superheroes.hero;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;


// https://stackoverflow.com/questions/5550568/propagation-behaviour-of-transaction

@ApplicationScoped
public class CustomerService {
	
	@Inject
	private AccountService accountService;
	
	@Transactional(value = TxType.REQUIRED)
	public void updateCustomer() {
		///
		accountService.updateAccount();
	}
	

}
