package com.Subbotin.project.config;

import LiteratureRecommendationSystem.LegacyCliFlow;
import LiteratureRecommendationSystem.User.UserFactory;
import LiteratureRecommendationSystem.domain.Literature;
import LiteratureRecommendationSystem.repository.LiteratureRepository;
import LiteratureRecommendationSystem.repository.Repository;
import LiteratureRecommendationSystem.service.InputForFilter;
import LiteratureRecommendationSystem.service.LiteratureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class LiteratureRecommendationConfiguration {

    @Bean
    Repository<Literature> literatureRepository() {
        return new LiteratureRepository();
    }

    @Bean
    LiteratureService literatureService(Repository<Literature> literatureRepository) {
        return new LiteratureService(literatureRepository);
    }

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    UserFactory userFactory(Scanner scanner) {
        return new UserFactory(scanner);
    }

    @Bean
    InputForFilter inputForFilter(Scanner scanner) {
        return new InputForFilter(scanner);
    }

    @Bean
    LegacyCliFlow legacyCliFlow(LiteratureService literatureService, UserFactory userFactory,
                                InputForFilter inputForFilter) {
        return new LegacyCliFlow(literatureService, userFactory, inputForFilter);
    }
}
