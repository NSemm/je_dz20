package com.k7.httpFacade;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonHttpFacade {
    HttpClient httpClient = HttpClient.newBuilder().build();
    ObjectMapper objectMapper = new ObjectMapper();

    public <T> T get(String url, Class<T> responseClass) {



        HttpRequest request = createGetRequest(url);
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            T userResponse = objectMapper.readValue(response.body(), responseClass);
            return userResponse;

        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    private HttpRequest createGetRequest (String url){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "Application/json")
                .build();
    }
}