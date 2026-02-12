package LiteratureRecommendationSystem.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

final class GenreCatalog {

    static final Map<LiteratureService.LiteratureType, List<String>> GENRES;

    static {
        EnumMap<LiteratureService.LiteratureType, List<String>> map =
                new EnumMap<>(LiteratureService.LiteratureType.class);
        map.put(LiteratureService.LiteratureType.BOOK,
                List.of("Detektívka", "Klasika", "Román", "Rozprávka", "Sci-fi"));
        map.put(LiteratureService.LiteratureType.MANGA,
                List.of("Josei", "Seinen", "Shojo", "Shonen", "Sports"));
        map.put(LiteratureService.LiteratureType.ENCYCLOPEDIA, List.of(
                "Architektúra", "Astronómia", "Biologia", "Chémia", "Dinosaurus",
                "Ekonomika", "Film", "Filozofia", "Fyzika", "Geografia",
                "História", "Hudba", "Jazykoveda", "Kuchárska", "Literatúra",
                "Matematika", "Medicína", "Mytológia", "Náboženstvo", "Právo",
                "Príroda", "Psychológia", "Šport", "Technika", "Umenie",
                "Veda", "Vojenstvo", "Zdravie", "Zoológia"));
        GENRES = Map.copyOf(map);
    }

    private GenreCatalog() {
    }
}