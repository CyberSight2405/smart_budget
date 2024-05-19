package com.finance.smart_budget.controllers;

import com.finance.smart_budget.entity.User;
import com.finance.smart_budget.services.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    ResponseEntity<String> get(@AuthenticationPrincipal User user) {
        String recommendation = recommendationService.get(user.getUsername());
        return ResponseEntity.ok(recommendation);
    }

}
