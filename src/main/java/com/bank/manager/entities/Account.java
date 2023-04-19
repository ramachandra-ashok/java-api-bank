package com.bank.manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Account {

	@Id
	private long accountID;
	private String accountName;
	private String accountDetails;
	public Account(long accountID, String accountName, String accountDetails) {
		super();
		this.accountID = accountID;
		this.accountName = accountName;
		this.accountDetails = accountDetails;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getAccountID() {
		return accountID;
	}
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(String accountDetails) {
		this.accountDetails = accountDetails;
	}
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountName=" + accountName + ", accountDetails=" + accountDetails
				+ "]";
	}
	
	
}
