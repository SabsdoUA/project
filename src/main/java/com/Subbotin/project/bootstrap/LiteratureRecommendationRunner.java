package com.Subbotin.project.bootstrap;

import LiteratureRecommendationSystem.LegacyCliFlow;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.cli.enabled", havingValue = "true", matchIfMissing = true)
public class LiteratureRecommendationRunner implements CommandLineRunner {

    private final LegacyCliFlow legacyCliFlow;

    public LiteratureRecommendationRunner(LegacyCliFlow legacyCliFlow) {
        this.legacyCliFlow = legacyCliFlow;
    }

    @Override
    public void run(String... args) {
        legacyCliFlow.execute();
    }
}
