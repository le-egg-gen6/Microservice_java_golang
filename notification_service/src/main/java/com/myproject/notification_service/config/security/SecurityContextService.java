package com.myproject.notification_service.config.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 2:14 PM Mon 9/23/2024
 */
@Service
public class SecurityContextService {

	public UserSimpleDetails getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null
			&& authentication.isAuthenticated()
			&& authentication.getPrincipal() instanceof UserSimpleDetails
		) {
			return (UserSimpleDetails) authentication.getPrincipal();
		}
		return null;
	}

	public boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null
			&& authentication.isAuthenticated()
			&& !(authentication instanceof AnonymousAuthenticationToken);
	}



}
