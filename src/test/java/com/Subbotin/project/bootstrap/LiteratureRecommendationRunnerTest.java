package com.Subbotin.project.bootstrap;

import LiteratureRecommendationSystem.LegacyCliFlow;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LiteratureRecommendationRunnerTest {

    @Test
    void runShouldIgnoreNoSuchElementExceptionFromCliFlow() {
        LegacyCliFlow legacyCliFlow = mock(LegacyCliFlow.class);
        doThrow(new NoSuchElementException("No line found")).when(legacyCliFlow).execute();

        LiteratureRecommendationRunner runner = new LiteratureRecommendationRunner(legacyCliFlow);

        assertDoesNotThrow(() -> runner.run());
        verify(legacyCliFlow, times(1)).execute();
    }
}
