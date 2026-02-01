package com.fitness.aiService.service;

import com.fitness.aiService.exception.NotFoundException;
import com.fitness.aiService.model.Recommendation;
import com.fitness.aiService.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;


    public List<Recommendation> getUserRecommendation(String userId) {
        return  recommendationRepository.findAllByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId){
        return  recommendationRepository.findByActivityId(activityId)
                .orElseThrow(() -> new NotFoundException("No recommendation found for this activity " + activityId));
    }
}
