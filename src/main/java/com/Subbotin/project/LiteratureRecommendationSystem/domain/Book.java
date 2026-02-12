package LiteratureRecommendationSystem.domain;

public class Book extends Literature {

    private final String author;
    private final int pages;

    public Book(String genre, int ageLimit, String name, String description, int releaseDate, String linkBook, String author, int pages) {
        super(genre, ageLimit, name, description, releaseDate, linkBook);

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Autor nesmie byť prázdny");
        }
        if (pages <= 0) {
            throw new IllegalArgumentException("Počet strán musí byť kladné číslo");
        }

        this.author = author;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }
    public int getPages() {
        return pages;
    }

    @Override
    public String getType() {
        return "Book";
    }
}
