package com.Subbotin.project.bootstrap;

import LiteratureRecommendationSystem.LegacyCliFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@ConditionalOnProperty(name = "app.cli.enabled", havingValue = "true", matchIfMissing = false)
public class LiteratureRecommendationRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LiteratureRecommendationRunner.class);

    private final LegacyCliFlow legacyCliFlow;

    public LiteratureRecommendationRunner(LegacyCliFlow legacyCliFlow) {
        this.legacyCliFlow = legacyCliFlow;
    }

    @Override
    public void run(String... args) {
        try {
            legacyCliFlow.execute();
        } catch (NoSuchElementException ex) {
            log.warn("CLI input stream ended unexpectedly. Skipping legacy CLI flow. " +
                    "Run with interactive stdin or disable it using --app.cli.enabled=false");
        }
    }
}
