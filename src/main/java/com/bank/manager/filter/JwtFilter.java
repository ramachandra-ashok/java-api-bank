package com.bank.manager.filter;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bank.manager.service.UserService;
import com.bank.manager.utility.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component @Log4j2
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;

    
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
			throws jakarta.servlet.ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        try {
        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtUtility.getUsernameFromToken(token);
        }

        
			if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails
			    = userService.loadUserByUsername(userName);

			    if(jwtUtility.validateToken(token,userDetails)) {
			        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
			                = new UsernamePasswordAuthenticationToken(userDetails,
			                null, ( userDetails).getAuthorities());

			        usernamePasswordAuthenticationToken.setDetails(
			                new WebAuthenticationDetailsSource().buildDetails(request)
			        );

			        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			    }

			}
		} catch (Exception e) {
			response.setHeader("Error", e.getMessage());
			response.setStatus(403);
			Map<String, String> error =new HashMap<>();
			error.put("error_message", e.getMessage());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			new ObjectMapper().writeValue(response.getOutputStream(), e);
		}
        filterChain.doFilter(request, response);
    }

}
