package LiteratureRecommendationSystem.repository;

import LiteratureRecommendationSystem.domain.Literature;

public class LiteratureRepository extends InMemoryRepository<Literature> {
    public LiteratureRepository() {
        loadDefaultSeed();
    }
    public int loadDefaultSeed() {
        return LiteratureRepositorySeed.populate(this);
    }
}