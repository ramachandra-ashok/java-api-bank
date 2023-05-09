package com.bank.manager.module;
import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data


public class JwtResponse {

    private String jwtToken;
    private Date expirationTime;
	public JwtResponse(String jwtToken,Date date) {
		super();
		this.expirationTime=date;
		this.jwtToken = jwtToken;
	}

	public JwtResponse(Map<String, Object> token) {
		this.jwtToken = (String) token.get("token");
		this.expirationTime=(Date) token.get("expiry");
	}

	public Date getDate() {
		return expirationTime;
	}

	public void setDate(Date date) {
		this.expirationTime = date;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
