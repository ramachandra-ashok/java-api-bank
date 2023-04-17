package com.bank.manager.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.manager.entities.Account;

public interface AccountDoa extends JpaRepository<Account, Long> {

}
