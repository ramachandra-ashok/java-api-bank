package com.bank.manager.controller;
import com.bank.manager.entities.*;
import com.bank.manager.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class MyController {
	
	
	@Autowired
	private AccountService accountService;
	@GetMapping("/home")
	public String Home() {
	return "This is the home methond congrats";
 }
	//get the accounts
	@GetMapping("/accounts")
		public List<Account> getAccounts(){
			return this.accountService.getAccounts();
		}
	
	@GetMapping("/accounts/{id}")
	public Account getAccount(@PathVariable String id) throws JsonMappingException, JsonProcessingException, NumberFormatException{
		return this.accountService.getAccount(Long.parseLong(id));
	}
	
	@PostMapping(path = "/accounts",consumes = "application/json")
	public Account createAccount(@RequestBody Account account) {
		return this.accountService.addAccount(account);
	}
	
	@PutMapping("/accounts")
	public Account updateAccount(@RequestBody Account account) {
		return this.accountService.updateAccount(account);
	}
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<HttpStatus> updateAccount(@PathVariable String id) {
		
		try {
			 this.accountService.deleteAccount(Long.parseLong(id));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
