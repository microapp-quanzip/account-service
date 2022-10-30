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
public class MailClientConfigurationImpl {

    @Value(value = "${email.clientId}")
    public String clientId;

    @Value(value = "${email.clientSecret}")
    public String clientSecret;

    @Value(value = "${email.accessTokenUri}")
    public String accessTokenUri;

    @Value(value = "${email.scopes}")
    public String scope;

    @Bean
    public RequestInterceptor getMailRequestInterceptor(){
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), getResource());
    }

//    private OAuth2ProtectedResourceDetails getResource() {
//        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
//        details.setClientId(emailClientDTO.getClientId());
//        details.setClientSecret(emailClientDTO.getClientSecret());
////        details.setGrantType(emailClientDTO.getGrantType());
//        details.setAccessTokenUri(emailClientDTO.getAccessTokenUri());
//        details.setScope(Arrays.asList(emailClientDTO.getScopes().split(",")));
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
