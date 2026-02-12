package LiteratureRecommendationSystem.domain;

public class Manga extends Literature {

    private final String illustrator;
    private final int volumes;
    private final boolean colored;

    public Manga(String genre, int ageLimit, String name, String description, int releaseDate, String linkBook, String illustrator, int volumes, boolean colored) {
        super(genre, ageLimit, name, description, releaseDate, linkBook);

        if (illustrator == null || illustrator.isBlank()) {
            throw new IllegalArgumentException("Ilustrátor nesmie byť prázdny");
        }
        if (volumes <= 0) {
            throw new IllegalArgumentException("Počet zväzkov musí byť kladné číslo");
        }

        this.illustrator = illustrator;
        this.volumes = volumes;
        this.colored = colored;
    }

    public String getIllustrator() {
        return illustrator;
    }
    public int getVolumes() {
        return volumes;
    }
    public boolean isColored() {
        return colored;
    }
    public String getColorDescription() {
        return colored ? "farebná manga" : "čiernobiela manga";
    }

    @Override
    public String getType() {
        return "Manga";
    }
}
