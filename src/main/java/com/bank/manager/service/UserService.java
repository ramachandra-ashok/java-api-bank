package com.bank.manager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.bank.manager.entities.AccountUser;
import com.bank.manager.module.CustomerUserDeatils;
@Service
public class UserService implements UserDetailsService {

	 @Autowired
	 private UserAccountService userService;
	
	@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		
AccountUser accountUser= this.userService.getUser(userName);

		if(accountUser == null) {
			throw new UsernameNotFoundException("No User Found");
		}

        return new CustomerUserDeatils(accountUser);
    }

}
