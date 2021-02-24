package com.k7.httpFacade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;

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

    public <T> T getAuth(String url, Class<T> responseClass, String token) {
        try {
            HttpRequest request = createGetRequestAuth(url, token);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            T contactsResponse = objectMapper.readValue(response.body(), responseClass);
            return contactsResponse;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T post(String url, Object body, Class<T> responseClass) {
        try {
            HttpRequest request = createPostRequest(body, url);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            T statusResponse = objectMapper.readValue(response.body(), responseClass);
            return statusResponse;
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public <T> T postAuth(String url, Object body, Class<T> responseClass, String token) {
        try {
            HttpRequest request = createPostRequestAuth(body, url, token);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            T contactsResponse = objectMapper.readValue(response.body(), responseClass);
            return contactsResponse;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpRequest createGetRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "Application/json")
                .build();
    }

    private HttpRequest createGetRequestAuth(String url, String token) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "Application/json")
                .header("Content-Type", "Application/json")
                .header("Authorization", "Bearer " + token)
                .build();
    }

    private HttpRequest createPostRequest(Object req, String url) {
        try {
            return HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(req)))
                    .header("Accept", "Application/json")
                    .header("Content-Type", "Application/json")
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpRequest createPostRequestAuth(Object req, String url, String token) {
        try {
            return HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(req)))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "Application/json")
                    .header("Content-Type", "Application/json")
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}