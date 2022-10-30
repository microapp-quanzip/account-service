package com.viettel.account.client.impl;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Collections;

//@Component
@SuppressWarnings(value = "deprecation")
public class StatisticClientConfiguration {
//    @Autowired
//    @Qualifier(value = "getStatisticClientObject")
//    private ClientDTO statisticClientDTO;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Value(value = "${statistic.clientId}")
    public String clientId;

    @Value(value = "${statistic.clientSecret}")
    public String clientSecret;

    @Value(value = "${statistic.accessTokenUri}")
    public String accessTokenUri;

    @Value(value = "${statistic.scopes}")
    public String scope;

    @Bean
    public RequestInterceptor getStatisticRequestInterceptor(){
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), getResource());
    }
//
//    private OAuth2ProtectedResourceDetails getResource() {
//        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
//        details.setClientId(statisticClientDTO.getClientId());
//        details.setClientSecret(statisticClientDTO.getClientSecret());
////        details.setGrantType(statisticClientDTO.getGrantType());
//        details.setAccessTokenUri(statisticClientDTO.getAccessTokenUri());
//        details.setScope(Arrays.asList(statisticClientDTO.getScopes().split(",")));
//        return details;
//    }

    private OAuth2ProtectedResourceDetails getResource() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setScope(Collections.singletonList(scope));
        return details;
    }
}

