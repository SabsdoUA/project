package LiteratureRecommendationSystem.User;

import java.util.Scanner;

public class UserFactory {

    private final Scanner scanner;

    public UserFactory(Scanner scanner) {
        this.scanner = scanner;
    }

    public User create() {
        System.out.println("\nAhoj! \n" +
                "Som tvoj osobný systém odporúčania literatúry. Pomôžem ti s výberom literatúry na čítanie. \n" +
                "Položím ti niekoľko jednoduchých otázok a potom ti odporučím literatúru, \n" +
                "ktorá by sa ti mohla páčiť. ʕ ᵔᴥᵔ ʔ\n");

        System.out.println("Zadajte svoje meno: ");
        String name = scanner.nextLine();

        System.out.println("Koľko máš rokov?: ");
        int age = readInt();

        return new User(name, age);
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
}
