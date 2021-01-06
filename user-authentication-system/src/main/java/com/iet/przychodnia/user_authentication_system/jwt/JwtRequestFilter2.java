package com.iet.przychodnia.user_authentication_system.jwt;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.iet.przychodnia.user_authentication_system.Globals.AUTHORIZATION_HEADER;

@Slf4j
public class JwtRequestFilter2 extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.info("Processing authentication");

		boolean authenticated = true;

		try {
			attemptAuthentication(request, response);
		} catch (Exception e) {
			log.error("An internal error occurred while trying to authenticate the user: ", e);

			authenticated = false;
		}

		setAuthenticationContext(authenticated);

		chain.doFilter(request, response);
	}

	public void attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		val authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

		JwtValidator.validate(authorizationHeader);
	}

	private void setAuthenticationContext(boolean authenticated) {
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(new JwtTokenAuthentication(authenticated));
		SecurityContextHolder.setContext(context);
	}
}