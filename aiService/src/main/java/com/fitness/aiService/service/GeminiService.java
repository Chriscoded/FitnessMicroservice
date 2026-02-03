package com.fitness.aiService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GeminiService {

    private final WebClient webClient;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    @Value("${gemini.api.url}")
    private String geminiAPIUrl;
    @Value("${gemini.api.key}")
    private String geminiAPIKey;


    public String getAnswer(String question){
        log.info("inside get answer, in Gemini Service");
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[]{
                                Map.of("text", question)
                        })
                }
        );

//        Map<String, Object> requestBody = Map.of(
//                "contents", List.of(
//                        Map.of("text", "Explain how AI works in a few words")
//                )
//        );

//        Map<String, Object> requestBody = Map.of(
//                "prompt", "Explain how AI works in a few words"
//        );

        String response = webClient.post()
                .uri(geminiAPIUrl)
                .header("x-goog-api-key", geminiAPIKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }
}
