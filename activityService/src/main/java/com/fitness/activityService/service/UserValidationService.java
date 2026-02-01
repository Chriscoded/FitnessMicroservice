package com.fitness.activityService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {
    public final WebClient userServiceWebClient;

    public boolean validateUser(String userId){
        log.info("Calling User Validation API for UserId {}", userId);
        try{
            return Boolean.TRUE.equals(userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());
        } catch (WebClientResponseException e) {
            if(e.getStatusCode() ==HttpStatus.NOT_FOUND)
                throw new RuntimeException("User not found " + userId);
            if(e.getStatusCode() == HttpStatus.BAD_REQUEST)
                throw new RuntimeException("Invalid Request " + userId);
        }

        return false;
    }
}
