package com.bank.manager.module;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.manager.entities.AccountUser;



public class CustomerUserDeatils implements UserDetails {

	/**
	 * 
	 */

	@Autowired
	private AccountUser accountUser;
	
	public CustomerUserDeatils(AccountUser accountUser2) {
		this.accountUser=accountUser2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> set =new HashSet<>();
		set.add(new SimpleGrantedAuthority(this.accountUser.getRoles()));
		return set;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.accountUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.accountUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
