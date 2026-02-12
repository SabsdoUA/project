package LiteratureRecommendationSystem.service;

import LiteratureRecommendationSystem.User.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputForFilter {

    private final Scanner scanner;

    public InputForFilter(Scanner scanner) {
        this.scanner = scanner;
    }

    public DataForFilter create(User user) {
        System.out.println(
                "\nVyberte si typ literatúry, ktorú si želáte čítať.\n" +
                        "Prosím, zadajte číslo, ktoré zodpovedá zvolenej možnosti:\n" +
                        "1. Knihy\n" +
                        "2. Manga\n" +
                        "3. Encyklopédie\n" +
                        "4. Náhodný výber literatúry");
        int selectionLiterature = readLiteratureSelection();

        if (selectionLiterature == 4) {
            int ageRating = Math.min(user.getAge(), 18);
            return new DataForFilter(selectionLiterature, ageRating, 0, RecencyCategory.NEW, user);
        }

        System.out.println(
                "\nZadajte vekové hodnotenie literatúry, ktorú si želáte čítať.\n" +
                        "Prosím, zadajte iba číslo od 0 do 18 (napríklad: 12, 16, 18).");
        int ageRating = readAgeRating(user);

        Map<Integer, List<String>> genresByType = Map.of(
                1, GenreCatalog.GENRES.getOrDefault(LiteratureService.LiteratureType.BOOK, List.of()),
                2, GenreCatalog.GENRES.getOrDefault(LiteratureService.LiteratureType.MANGA, List.of()),
                3, GenreCatalog.GENRES.getOrDefault(LiteratureService.LiteratureType.ENCYCLOPEDIA, List.of()));
        int genre = 0;

        List<String> availableGenres = genresByType.get(selectionLiterature);

        if (availableGenres == null) {
            System.out.println("Neplatná voľba typu literatúry.");
            genre = 0;
        } else {
            System.out.println("\nVyberte si žáner (zadajte číslo):");
            for (int i = 0; i < availableGenres.size(); i++) {
                System.out.println((i + 1) + ". " + availableGenres.get(i));
            }

            int genreChoice = readInt();
            genre = (genreChoice >= 1 && genreChoice <= availableGenres.size()) ? genreChoice : 0;

            if (genre == 0) {
                System.out.println("Neplatná voľba žánru. Žáner nebude použitý.");
            }
        }

        System.out.println(
                "\nVyberte obdobie podľa roku vydania:\n" +
                        "1. Nová literatúra (posledné 2 roky)\n" +
                        "2. Stredná literatúra (3–4 roky)\n" +
                        "3. Stará literatúra (5 rokov a viac)");

        RecencyCategory recency = readRecency();

        return new DataForFilter(selectionLiterature, ageRating, genre, recency, user);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Prosím, zadajte iba číslo.");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private RecencyCategory readRecency() {
        while (true) {
            int recencyChoice = readInt();
            switch (recencyChoice) {
                case 1:
                    return RecencyCategory.NEW;
                case 2:
                    return RecencyCategory.MID;
                case 3:
                    return RecencyCategory.OLD;
                default:
                    System.out.println("Neplatná voľba obdobia podľa roku vydania. Skúste znova.");
            }
        }
    }

    private int readLiteratureSelection() {
        while (true) {
            int selection = readInt();
            if (selection >= 1 && selection <= 4) {
                return selection;
            }
            System.out.println("Neplatná voľba typu literatúry. Skúste to znovu.");
        }
    }

    private int readAgeRating(User user) {
        while (true) {
            int ageRating = readInt();
            if (ageRating < 0 || ageRating > 18) {
                System.out.println("Vekové hodnotenie musí byť v rozsahu od 0 do 18. Skúste znova.");
                continue;
            }
            if (ageRating > user.getAge()) {
                System.out.println("Nemôžete vybrať vyššie vekové hodnotenie než je váš vek. Skúste znova.");
                continue;
            }
            return ageRating;
        }
    }
}
