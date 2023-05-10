package com.bank.manager.module;
import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data


public class JwtResponse {

    private String jwtAccessToken;
    private String jwtRefreshToken;
    private Date expirationTime;
	public JwtResponse(String jwtAccessToken,String jwtRefreshToken ,Date date) {
		super();
		this.expirationTime=date;
		this.jwtAccessToken = jwtAccessToken;
		this.jwtRefreshToken = jwtRefreshToken;
	}

	public JwtResponse(Map<String, Object> token) {
		this.jwtAccessToken = (String) token.get("jwtAccessToken");
		this.expirationTime=(Date) token.get("expiry");
		this.jwtRefreshToken=(String) token.get("refresh_token");
	}

	public JwtResponse(String token) {
		this.jwtAccessToken = token;
	}


	public String getJwtRefreshToken() {
		return jwtRefreshToken;
	}

	public void setJwtRefreshToken(String jwtRefreshToken) {
		this.jwtRefreshToken = jwtRefreshToken;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}


	public String getJwtToken() {
		return jwtAccessToken;
	}

	public void setJwtToken(String jwtAccessToken) {
		this.jwtAccessToken = jwtAccessToken;
	}

	@Override
	public String toString() {
		return "JwtResponse [jwtAccessToken=" + jwtAccessToken + ", jwtRefreshToken=" + jwtRefreshToken
				+ ", expirationTime=" + expirationTime + "]";
	}
}
