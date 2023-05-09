package com.bank.manager.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.manager.entities.AccountUser;

public interface UserDoa extends JpaRepository<AccountUser, String> {
	
	AccountUser findByUsername(String username);
}
