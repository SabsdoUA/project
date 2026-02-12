package LiteratureRecommendationSystem.domain;

import java.util.UUID;
import java.time.Year;

public abstract class Literature implements Identifiable {

    protected final String id;
    protected String genre;
    protected int ageLimit;
    protected String name;
    protected String description;
    protected int releaseDate;
    protected String linkBook;

    public Literature(String genre, int ageLimit, String name, String description, int releaseDate, String linkBook) {

        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Žáner nesmie byť prázdny");
        }
        if (ageLimit < 0 || ageLimit > 18) {
            throw new IllegalArgumentException("Vekové obmedzenie musí byť v rozsahu 0–18");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Názov nesmie byť prázdny");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Popis nesmie byť prázdny");
        }
        int currentYear = Year.now().getValue();
        if (releaseDate <= 0 || releaseDate > currentYear) {
            throw new IllegalArgumentException("Neplatný rok vydania");
        }
        if (linkBook == null || linkBook.isBlank()) {
            throw new IllegalArgumentException("Odkaz na knihu nesmie byť prázdny");
        }

        this.id = UUID.randomUUID().toString();
        this.genre = genre;
        this.ageLimit = ageLimit;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.linkBook = linkBook;
    }

    public String getId() {
        return id;
    }
    public String getGenre() {
        return genre;
    }
    public int getAgeLimit() {
        return ageLimit;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getReleaseDate() {
        return releaseDate;
    }
    public String getLinkBook() {
        return linkBook;
    }

    public abstract String getType();
}