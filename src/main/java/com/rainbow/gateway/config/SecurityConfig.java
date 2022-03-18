package com.rainbow.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author yanzhihao
 * @since 2022/3/17
 */
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RequestAuthenticationEntryPoint requestAuthenticationEntryPoint;

    @Autowired
    private ReactiveAuthenticationManager tokenAuthenticationManager;

//    @Autowired
//    private CorsFilter corsFilter;

    @Bean
    public SecurityWebFilterChain webFluxSecurityConfiguration(ServerHttpSecurity http) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());

        http.httpBasic().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/auth/oauth/token/**","/auth/oauth/check_token/**")
                .permitAll()
//                .anyExchange().access()
                .and().exceptionHandling()
                .authenticationEntryPoint(requestAuthenticationEntryPoint)
//                .accessDeniedHandler()
                .and()
//                .addFilterAt((WebFilter) corsFilter, SecurityWebFiltersOrder.CORS)
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }
}
