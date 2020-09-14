package com.raj.spring.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomHandler implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		User principal = (User) authentication.getPrincipal();
		System.out.println("principal" + principal.getUsername());
		boolean isAdmin = false;
		Iterator<GrantedAuthority> grantedAuthorityIterator = principal.getAuthorities().iterator();
		while (grantedAuthorityIterator.hasNext()) {
			if (grantedAuthorityIterator.next().getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}
		if (isAdmin) {
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			response.sendRedirect(request.getContextPath() + "/Success");
		}

	}

}
