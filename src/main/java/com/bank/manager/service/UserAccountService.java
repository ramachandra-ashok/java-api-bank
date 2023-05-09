package com.bank.manager.service;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.manager.doa.UserDoa;
import com.bank.manager.entities.AccountUser;
import com.bank.manager.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserAccountService {
	
	Logger logger=LoggerFactory.getLogger(UserAccountService.class);
	
	@Autowired
	private  UserDoa userDoa;

	public AccountUser saveUser(AccountUser account) {
		logger.info("New user saved "+account);
		return userDoa.save(account);
	}


	public void addRoleToUser(String username, String roleName) {
		AccountUser accountUser =userDoa.findByUsername(username);
		accountUser.setRoles(roleName);
		
	}

	public AccountUser getUser(String username) {
		logger.info("Got user data "+username);
	    return userDoa.findByUsername(username);
	}

	
	public List<AccountUser> getUsers() {
		
		return userDoa.findAll();
	}

	
	public AccountUser updateAccount(AccountUser updatedAccount) {
		
		String userID = String.valueOf(updatedAccount.getUserId());
	    userDoa.findById(userID).orElseThrow(() -> new ResourceNotFoundException("AccountUser", "id", userID));
	    if (updatedAccount.getUsername() == null) {
	    	updatedAccount.setUsername(userDoa.getById(userID).getUsername());
	    }
	    if (updatedAccount.getPassword() == null) {
	    	updatedAccount.setPassword(userDoa.getById(userID).getPassword());
	    }
	    if (updatedAccount.getRoles() == null) {
	    	updatedAccount.setRoles(userDoa.getById(userID).getRoles());
	    }
	    if (updatedAccount.getName() == null) {
	    	updatedAccount.setName(userDoa.getById(userID).getName());
	    }
	    System.out.println("userDoa.getById(userID).getTokenExpiry():- "+userDoa.getById(userID).getTokenExpiry());
	    
	    updatedAccount.setTokenExpiry(userDoa.getById(userID).getTokenExpiry());
	 
	    
	    logger.info(" user updated "+updatedAccount);
	    return userDoa.save(updatedAccount);
	}



	public void deleteAccount(String id) {
		AccountUser account =userDoa.getOne(id);
			userDoa.delete(account);
		
	}


	public AccountUser getAccountByUsername(String username) {
		
		return null;
	}
	


}
