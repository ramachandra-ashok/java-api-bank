package com.bank.manager.service;
import java.util.List;

import com.bank.manager.entities.*;

public interface AccountService {
	public List<Account> getAccounts();

	public Account getAccount(long id);

	public Account addAccount(Account account);

	public Account updateAccount(Account account);

	public void deleteAccount(long id);
}
