package com.iet.przychodnia.apigateway;

import com.iet.przychodnia.apigateway.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/user-authentication-system/api/authenticate",
                                   "/api/user-authentication-system/api/user/register",
                                   "/**/v2/api-docs",
                                   "/**/configuration/ui",
                                   "/**/swagger-resources/**",
                                   "/**/configuration/security",
                                   "/**/swagger-ui.html",
                                   "/**/webjars/**",
                                    "/**/actuator/prometheus",
                                    "/actuator/prometheus");
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.httpBasic().disable().csrf().disable()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.httpBasic().disable().csrf().disable()
//                    .authorizeRequests()
//                    .antMatchers(HttpMethod.POST, "/api/user-authentication-system/api/authenticate").permitAll()
//                    .antMatchers("/api/user/register").permitAll()
//                    .antMatchers("/**/swagger-ui.html", "/**/v2/api-docs", "/**/configuration/**", "/**/swagger-resources/**", "/**/webjars/**").permitAll()
//                    .antMatchers("/**").authenticated();
//    }
}
