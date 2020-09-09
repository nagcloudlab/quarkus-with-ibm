package io.quarkus.workshop.superheroes.hero;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@ApplicationScoped
public class AccountService {
	
	@Transactional(value = TxType.MANDATORY)
	public void updateAccount() {
		
	}

}
