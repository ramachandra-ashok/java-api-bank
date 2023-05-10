package com.bank.manager.utility;

import io.jsonwebtoken.Claims;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bank.manager.doa.UserDoa;
import com.bank.manager.entities.AccountUser;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtility implements Serializable {

    private static final long serialVersionUID = 234234523523L;

    public static final long JWT_ACCESS_TOKEN_VALIDITY = 1* 60 * 60;
    
    public static final long JWT_REFRESH_TOKEN_VALIDITY = 30* 60 * 60;
    
    Map<String, Object> claims = new HashMap<>();
    Map<String, Object> result = new HashMap<>();
    @Autowired
	private  UserDoa userDoa;
    

    @Value("${jwt.secret}")
    private String secretKey;
    
    

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    //generate token for user
    public Map<String, Object> generateToken(UserDetails userDetails,AccountUser accountUser) {
        accountUser.setTokenExpiry(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY * 1000));
        result.put("jwtAccessToken", doGenerateAccessToken(userDetails.getUsername()));
        result.put("refresh_token", doGenerateRefreshToken(userDetails.getUsername()));
        accountUser.setRefresh_token((String)result.get("refresh_token"));
        userDoa.save(accountUser);
        result.put("expiry", new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY * 1000));
        return result;
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    
    public String doGenerateAccessToken(String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
    private String doGenerateRefreshToken(String subject) {

        return Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
    

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }





}
