package com.bank.manager.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AccountUser {
	
	@Id  
    private int userId;
	private String name;
    private String username;
    private String password;
    private java.util.Date tokenExpiry;
    private String roles ;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public java.util.Date getTokenExpiry() {
		return tokenExpiry;
	}
	public void setTokenExpiry(java.util.Date date) {
		this.tokenExpiry = date;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public AccountUser() {
        // Default constructor
    }
	public AccountUser(int userId, String name, String username, String password, Date tokenExpiry, String roles) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.tokenExpiry = tokenExpiry;
		this.roles = roles;
	}
	

	public AccountUser(String username, String password, String roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", tokenExpiry=" + tokenExpiry + ", roles=" + roles + "]";
	}

    
}

