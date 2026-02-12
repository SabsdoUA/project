package LiteratureRecommendationSystem.service;

import LiteratureRecommendationSystem.domain.Literature;
import LiteratureRecommendationSystem.repository.Repository;

import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LiteratureService {

    private final Repository<Literature> repository;

    public LiteratureService(Repository<Literature> repository) {
        this.repository = Objects.requireNonNull(repository, "repository");
    }

    public List<Literature> recommend(DataForFilter filter, int limit) {
        Objects.requireNonNull(filter, "filter");
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit musí byť kladné číslo.");
        }
        if (isPureRandom(filter)) {
            return pickRandomRecommendation(filter, limit);
        }

        UserPreferences preferences = UserPreferences.from(filter);
        Predicate<Literature> eligibility = buildEligibility(preferences);

        return repository.findAll().stream().filter(eligibility)
                .sorted(Comparator.comparingInt(Literature::getReleaseDate).reversed()
                        .thenComparing(Literature::getName)).limit(limit)
                .collect(Collectors.toList());
    }

    private boolean isPureRandom(DataForFilter filter) {
        return filter.getSelectionLiterature() == 4 && filter.getGenre() == 0;
    }

    private List<Literature> pickRandomRecommendation(DataForFilter filter, int limit) {
        int userAge = filter.getUser().getAge();
        List<Literature> eligible = repository.findAll().stream()
                .filter(literature -> literature.getAgeLimit() <= userAge)
                .collect(Collectors.toList());
        if (eligible.isEmpty()) {
            return List.of();
        }
        java.util.Collections.shuffle(eligible);
        return eligible.stream().limit(limit).collect(Collectors.toList());
    }

    private Predicate<Literature> buildEligibility(UserPreferences preferences) {

        Predicate<Literature> byAge = literature -> literature.getAgeLimit() <= preferences.userAge()
                && literature.getAgeLimit() == preferences.ageRating();

        Predicate<Literature> byType = preferences.type()
                .<Predicate<Literature>>map(type -> literature -> type.matches(literature))
                .orElse(literature -> true);

        Predicate<Literature> byGenre = preferences.genre()
                .<Predicate<Literature>>map(genre -> literature -> genre.equalsIgnoreCase(literature.getGenre()))
                .orElse(literature -> true);

        Predicate<Literature> byRecency = literature ->
                toRecencyCategory(literature.getReleaseDate()) == preferences.recency();

        return byAge.and(byType).and(byGenre).and(byRecency);
    }

    private static RecencyCategory toRecencyCategory(int releaseYear) {
        int age = Math.max(0, Year.now().getValue() - releaseYear);
        if (age <= 2) {
            return RecencyCategory.NEW;
        }
        return age <= 4 ? RecencyCategory.MID : RecencyCategory.OLD;
    }

    private record UserPreferences(Optional<LiteratureType> type, Optional<String> genre,
                                   int ageRating, RecencyCategory recency, int userAge) {
        private static UserPreferences from(DataForFilter filter) {
            Optional<LiteratureType> type = LiteratureType.fromSelection(filter.getSelectionLiterature());
            return new UserPreferences(type, resolveGenre(type, filter.getGenre()),
                    filter.getAgeRating(), filter.getRecency(), filter.getUser().getAge());
        }
    }

    enum LiteratureType {
        BOOK(1, "Book"),
        MANGA(2, "Manga"),
        ENCYCLOPEDIA(3, "Encyclopedia");

        private final int selectionCode;
        private final String typeName;

        LiteratureType(int selectionCode, String typeName) {
            this.selectionCode = selectionCode;
            this.typeName = typeName;
        }

        boolean matches(Literature literature) {
            return literature.getType().equalsIgnoreCase(typeName);
        }

        static Optional<LiteratureType> fromSelection(int selection) {
            if (selection == 4) {
                return Optional.empty();
            }
            for (LiteratureType type : values()) {
                if (type.selectionCode == selection) {
                    return Optional.of(type);
                }
            }
            throw new IllegalArgumentException("Neplatná voľba typu literatúry: " + selection);
        }
    }

    private static Optional<String> resolveGenre(Optional<LiteratureType> type, int genreSelection) {
        if (genreSelection <= 0 || type.isEmpty()) {
            return Optional.empty();
        }
        List<String> genres = GenreCatalog.GENRES.getOrDefault(type.get(), List.of());
        if (genreSelection > genres.size()) {
            return Optional.empty();
        }
        return Optional.ofNullable(genres.get(genreSelection - 1));
    }
}