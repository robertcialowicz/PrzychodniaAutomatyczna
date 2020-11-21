package com.iet.przychodnia.apigateway.jwt;

import com.iet.przychodnia.apigateway.jwt.exceptions.JwtExpiredException;
import com.iet.przychodnia.apigateway.jwt.exceptions.JwtInvalidSignatureException;
import com.iet.przychodnia.apigateway.jwt.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

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

        if (authorizationHeader == null) {
            log.warn("No `Authorization` header specified");

            throw new UnauthorizedException("No authorization header is present");
        } else if (!authorizationHeader.startsWith(BEARER_PREFIX)) {
            log.warn("JWT doesn't start with `{}` prefix", BEARER_PREFIX);

            throw new UnauthorizedException("Invalid authorization header format");
        }

        val token = authorizationHeader.replace(BEARER_PREFIX, "");
        val decodedJwt = Jwt.decode(token, RSA.PUBLIC_KEY);

        if (decodedJwt.isEmpty()) {
            throw new JwtInvalidSignatureException(token);
        }

        val jwt = decodedJwt.get();

        if (jwt.isExpired(Instant.now())) {
            throw new JwtExpiredException(jwt.getIssuedAt(), jwt.getExpiresAt());
        }
    }

    private void setAuthenticationContext(boolean authenticated) {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new JwtTokenAuthentication(authenticated));
        SecurityContextHolder.setContext(context);
    }
}
