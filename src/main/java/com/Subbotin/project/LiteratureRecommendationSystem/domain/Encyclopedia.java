package LiteratureRecommendationSystem.domain;

public class Encyclopedia extends Literature {

    private final String field;
    private final String edition;

    public Encyclopedia(String genre, int ageLimit, String name, String description, int releaseDate, String linkBook, String field, String edition) {
        super(genre, ageLimit, name, description, releaseDate, linkBook);

        if (field == null || field.isBlank()) {
            throw new IllegalArgumentException("Oblasť encyklopédie nesmie byť prázdna");
        }
        if (edition == null || edition.isBlank()) {
            throw new IllegalArgumentException("Vydanie encyklopédie nesmie byť prázdne");
        }

        this.field = field;
        this.edition = edition;
    }

    public String getField() {
        return field;
    }
    public String getEdition() {
        return edition;
    }

    @Override
    public String getType() {
        return "Encyclopedia";
    }
}
