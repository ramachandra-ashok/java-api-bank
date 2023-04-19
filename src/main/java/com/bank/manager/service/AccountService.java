package com.bank.manager.service;
import java.util.List;

import com.bank.manager.entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface AccountService {
	public List<Account> getAccounts();

	public Account getAccount(long id) throws JsonMappingException, JsonProcessingException;

	public Account addAccount(Account account);

	public Account updateAccount(Account account);

	public void deleteAccount(long id);
}
