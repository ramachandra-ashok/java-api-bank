package com.bank.manager.controller;

import com.bank.manager.entities.*;
import com.bank.manager.service.UserAccountService;
import com.bank.manager.service.UserService;
import com.bank.manager.utility.JWTUtility;
import com.bank.manager.module.JwtRequest;
import com.bank.manager.module.JwtResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class MainController {
	
	@Autowired
	private JWTUtility jwtUtility;
	
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private UserAccountService userService;
	
	@Autowired
	private UserService service;

	
	@GetMapping("/home")
	public String Home() {
	return "This is the home methond congrats";
 }
	//get the accounts
	@GetMapping("/user")
		public AccountUser getAccounts(@RequestHeader(name = "Authorization") String token){
		token = token.substring(7);
        String userName = jwtUtility.getUsernameFromToken(token);
			return this.userService.getUser(userName);
		}
	
	@GetMapping("/user/{id}")
	public AccountUser getAccount(@PathVariable String id) throws JsonMappingException, JsonProcessingException, NumberFormatException{
		return this.userService.getUser(id);
	}
	
	@PostMapping(path = "/accounts",consumes = "application/json")
	public AccountUser createAccount(@RequestBody AccountUser account) {
		return this.userService.saveUser(account);
	}
	
	@PutMapping("/accounts")
	public AccountUser updateAccount(@RequestBody AccountUser account) {
		System.out.println("here in upadte "+ account);
		return this.userService.updateAccount(account);
	}

	
	@GetMapping("/userAccounts")
	public ResponseEntity<AccountUser>getUsers(@RequestHeader(name = "Authorization") String token){
		token = token.substring(7);
        String userName = jwtUtility.getUsernameFromToken(token);
		return ResponseEntity.ok().body(this.userService.getUser(userName));
	}
	
	@PostMapping(path = "/userAccount/save",consumes = "application/json")
	public ResponseEntity<AccountUser> createUserAccount(@RequestBody AccountUser account) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String hashedPassword = passwordEncoder.encode(account.getPassword());
		account.setPassword(hashedPassword);
		return ResponseEntity.ok().body(userService.saveUser(account));
	}

	
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
    	
    	 try {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                             jwtRequest.getUsername(),
                             jwtRequest.getPassword()
                     )
             );
         } catch (BadCredentialsException e) {
             throw new Exception("INVALID_CREDENTIALS", e);
         }

         final UserDetails userDetails
                 =  service.loadUserByUsername(jwtRequest.getUsername());
         AccountUser accountUser=this.userService.getUser(jwtRequest.getUsername());
      final   Map<String, Object>  token = jwtUtility.generateToken(userDetails,accountUser);
         return  new JwtResponse(token);
        
    }
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<HttpStatus> updateAccount(@PathVariable String id) {
		
		try {
			 this.userService.deleteAccount(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
