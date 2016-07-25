package com.vmezhevikin.blog.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.exception.CantCompleteClientRequestException;
import com.vmezhevikin.blog.model.CurrentUser;

public class SecurityUtil {
	
	public static CurrentUser getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof CurrentUser) {
			return (CurrentUser) principal;
		} else {
			return null;
		}
	}
	
	public static Long getCurrentUserId() {
		CurrentUser currentUser = getCurrentUser();
		if (currentUser == null) {
			return null;
		} else {
			return currentUser.getId();
		}
	}
	
	public static String getCurrentUserName() {
		CurrentUser currentUser = getCurrentUser();
		if (currentUser == null) {
			return null;
		} else {
			return currentUser.getName();
		}
	}
	
	public static void authentificate(Author author) {
		CurrentUser currentUser = new CurrentUser(author);
		Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public static void logoutCurrentUser(HttpServletRequest request) {
		try {
			request.logout();
		} catch (ServletException e) {
			throw new CantCompleteClientRequestException("Can't logout current user", e);
		}
	}
}