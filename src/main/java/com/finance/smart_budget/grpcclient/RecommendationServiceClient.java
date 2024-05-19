package com.finance.smart_budget.grpcclient;

import com.finance.smart_budget.services.RecommendationService;
import com.hakaton.recommender.grpc.RecommendationServiceGrpc;
import com.hakaton.recommender.grpc.RecommendationServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceClient implements RecommendationService {

    @Override
    public String get(String username) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8180)
                .usePlaintext()
                .build();
        RecommendationServiceGrpc.RecommendationServiceBlockingStub stub =
                RecommendationServiceGrpc.newBlockingStub(channel);
        RecommendationServiceOuterClass.RecommendationRequest request =
                RecommendationServiceOuterClass.RecommendationRequest.newBuilder()
                        .setUsername(username)
                        .build();
        RecommendationServiceOuterClass.RecommendationResponse response = stub.get(request);
        String recommendation = response.getRecommendation();
        channel.shutdown();

        return recommendation;
    }

}
