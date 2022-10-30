package com.viettel.account.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
@Configuration
@EnableResourceServer
public class ResourceServerSecurityConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(getTokenStore());
    }

    @Bean
    public TokenStore getTokenStore(){
        return new JwtTokenStore(getTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter getTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String verifyKey = "";
        Resource resource = new ClassPathResource("publickey.txt");
        try {
            verifyKey = new BufferedReader(new InputStreamReader(resource.getInputStream()))
            .lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        converter.setVerifierKey(verifyKey);
        return converter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/actuator/**", "/swagger-ui/**", "/v3-/api-docs/**").permitAll();
    }
}
