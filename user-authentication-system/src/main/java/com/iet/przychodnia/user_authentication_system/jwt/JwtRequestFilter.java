package com.iet.przychodnia.user_authentication_system.jwt;

import com.iet.przychodnia.user_authentication_system.exceptions.JwtExpiredException;
import com.iet.przychodnia.user_authentication_system.exceptions.JwtInvalidSignatureException;
import com.iet.przychodnia.user_authentication_system.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Slf4j
public class JwtRequestFilter extends AbstractAuthenticationProcessingFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public JwtRequestFilter(AuthenticationManager authenticationManager) {
        super("");

        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!this.requiresAuthentication(request, response)) {
            chain.doFilter(request, response);
        } else {
            log.debug("Processing authentication");

            boolean success = true;
            Authentication authResult = null;

            try {
                authResult = this.attemptAuthentication(request, response);
            } catch (Exception e) {
                log.error("An internal error occurred while trying to authenticate the user.", e);
                success = false;
            }

            if (authResult != null && success) {
                this.successfulAuthentication(request, response, chain, authResult);
            } else {
                this.unsuccessfulAuthentication(request, response, new AuthenticationException("DUPA") { //TODO
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                });
            }

            chain.doFilter(request, response);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
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

        return getAuthenticationManager().authenticate(new JwtTokenAuthentication(true));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
    }
}
