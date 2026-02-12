package LiteratureRecommendationSystem.service;

import LiteratureRecommendationSystem.User.User;

public class DataForFilter {
    private final int selectionLiterature;
    private final int ageRating;
    private final int genre;
    private final RecencyCategory recency;
    private final User user;

    public DataForFilter(int selectionLiterature, int ageRating, int genre, RecencyCategory recency, User user) {
        if (selectionLiterature < 1 || selectionLiterature > 4) {
            throw new IllegalArgumentException("Zadajte prosím číslo od 1 do 4, ktoré zodpovedá vybranému typu literatúry.");
        }
        if (user == null) {
            throw new IllegalArgumentException("Používateľ nesmie byť null.");
        }
        if (ageRating < 0 || ageRating > 18) {
            throw new IllegalArgumentException("Vekové hodnotenie musí byť v rozsahu od 0 do 18.");
        }
        if (ageRating > user.getAge()) {
            throw new IllegalArgumentException("Používateľ je príliš mladý na zvolené vekové hodnotenie literatúry.");
        }
        if (recency == null) {
            throw new IllegalArgumentException("Voľba obdobia (recency) nesmie byť null.");
        }

        int maxGenre;
        switch (selectionLiterature) {
            case 1, 2 -> maxGenre = 5;
            case 3 -> maxGenre = 29;
            case 4 -> maxGenre = 0;
            default -> throw new IllegalArgumentException("Neplatná voľba typu literatúry.");
        }

        if (genre < 0 || genre > maxGenre) {
            throw new IllegalArgumentException("Neplatná voľba žánru pre zvolený typ literatúry.");
        }
        if (selectionLiterature == 4 && genre != 0) {
            throw new IllegalArgumentException("Pri náhodnom výbere literatúry (4) musí byť žáner 0.");
        }

        this.selectionLiterature = selectionLiterature;
        this.ageRating = ageRating;
        this.genre = genre;
        this.recency = recency;
        this.user = user;
    }

    public int getSelectionLiterature() { return selectionLiterature; }
    public int getAgeRating() { return ageRating; }
    public int getGenre() { return genre; }
    public RecencyCategory getRecency() { return recency; }
    public User getUser() { return user; }
}
