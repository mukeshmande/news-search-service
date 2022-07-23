package com.news.search.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.search.common.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.HttpURLConnection;
import java.net.URI;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RestTemplateConfig {

    public static <T> T getNewsFromProviderAPI(Class<T> returnObjectClass, String url, String query, int pageNumber, String api_key){
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api-key", "{api-key}")
                .queryParam("q", "{q}")
                .queryParam("page", "{page}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("api-key", api_key);
        params.put("q", query);
        params.put("page", String.valueOf(pageNumber));


        HttpEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        T returnObject = null;
        try {
//            String jsonResponse = objectMapper.writeValueAsString(response.getBody());
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            returnObject = objectMapper.readValue(response.getBody(), returnObjectClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
         
        return returnObject;
    }


}
