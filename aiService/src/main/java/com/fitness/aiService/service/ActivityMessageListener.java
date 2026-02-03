package com.fitness.aiService.service;

import com.fitness.aiService.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
//@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private static final Logger log =
            LoggerFactory.getLogger(ActivityMessageListener.class);

    @Value("${rabbitmq.queue.name}")
    private  String queueNamw;

    private final ActivityAIService activityAIService;

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity){
        log.info("$ Received Activity for precessing {}", activity.getId());

        log.info("$ Generated Recommendation {}", activityAIService.generateRecommendation(activity));
    }
}
