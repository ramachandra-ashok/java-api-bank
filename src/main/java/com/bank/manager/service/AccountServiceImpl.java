package com.bank.manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.manager.doa.AccountDoa;
import com.bank.manager.entities.Account;

@Service
public class AccountServiceImpl implements AccountService {

	
//	List<Account> list;
	@Autowired
	private AccountDoa accountDoa;
	
	public AccountServiceImpl() {
//		list=new ArrayList<>();
//		list.add(new Account(123456789,"ram","This is the first Account fetched"));
//		list.add(new Account(123456,"sush","This is the second Account fetched"));
	}

	@Override
	public List<Account> getAccounts() {
		
		
		return accountDoa.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Account getAccount(long id) {
		
//		Account a=null;
//		for(Account account:list) {
//			if(account.getAccountID()==id) {
//				a=account;
//				break;
//			}
//		}
		
		//return a;
		
		return accountDoa.getOne(id);
	}

	@Override
	public Account addAccount(Account account) {
//		list.add(account);
		accountDoa.save(account);
		return account;
	}

	@Override
	public Account updateAccount(Account account) {
		
//		list.forEach(a->{
//			if(a.getAccountID()==account.getAccountID()) {
//				a.setAccountName(account.getAccountName());
//				a.setAccountDetails(account.getAccountDetails());
//			}
//		});
		
		accountDoa.save(account);
		return account;
	}

	@Override
	public void deleteAccount(long id) {
//		list=this.list.stream()
//                .filter(info -> info.getAccountID() != id)
//                .collect(Collectors.toList());
		@SuppressWarnings("deprecation")
		Account account =accountDoa.getOne(id);//get entinity
		accountDoa.delete(account);
	}

}
