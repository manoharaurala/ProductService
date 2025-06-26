package org.ruby.productservice.commons;

import org.ruby.productservice.controllers.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommonUtil {
    private final RestTemplate restTemplate;

    public AuthCommonUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public UserDto validateToken(String tokenValue) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", tokenValue);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(
                "http://127.0.0.1:8080/auth/validate/",
                HttpMethod.GET,
                httpEntity,
                UserDto.class
        ).getBody();
    }

}
