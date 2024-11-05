package edu.project1.handlers;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("RegexpSinglelineJava")
public class PlayerInputHandler {

    private final Scanner scanner;

    public PlayerInputHandler() {
        scanner = new Scanner(System.in);
    }

    public PlayerInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    private boolean isCharUnused(Set<Character> availableLetters, char symbol) {
        return availableLetters.contains(symbol);
    }

    @SuppressWarnings("MagicNumber")
    public int getWordCategory() {
        System.out.println("\nChoose the category: ");
        System.out.println("1 - Animals\n2 - Countries\n3 - Fillings\n4 - Transport");
        return getCorrectInt(1, 4);
    }

    public char getAnswer() {
        System.out.println("Would you like to start the new game? (Y/y, N/n)");
        Set<Character> correctAnswers = new HashSet<>() {{
            add('y');
            add('Y');
            add('n');
            add('N');
        }};
        return getCorrectChar(correctAnswers);
    }

    public char getSupposedLetter(Set<Character> availableLetters) {
        return getCorrectChar(availableLetters);
    }

    private char getCorrectChar(Set<Character> availableLetters) {
        char userInputSymbol = ' ';
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                userInputSymbol = getChar(availableLetters);
                isValidInput = true;
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return userInputSymbol;
    }

    public char getChar(Set<Character> availableLetters) {
        char userInputSymbol = ' ';
        System.out.print("Enter a letter: ");
        String input = scanner.next();
        if (input.equalsIgnoreCase("exit")) {
            return userInputSymbol;
        } else if (input.length() != 1) {
            throw new InputMismatchException("Invalid input. Enter exactly one character.");
        } else {
            userInputSymbol = input.charAt(0);
            if (!Character.isLetter(userInputSymbol)) {
                throw new IllegalArgumentException("Not a letter.");
            }
            if (!isCharUnused(availableLetters, userInputSymbol)) {
                throw new IllegalArgumentException("This letter had already been used.");
            }
            return userInputSymbol;
        }
    }

    private int getCorrectInt(int min, int max) {
        boolean isValidInput = false;
        int userInputSymbol = 0;
        while (!isValidInput) {
            try {
                userInputSymbol = getInt(min, max);
                isValidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return userInputSymbol;
    }

    public int getInt(int min, int max) {
        int userInputNumber = 0;
        System.out.print("Enter a number from " + min + " to " + max + " : ");
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new IllegalArgumentException("Not a number.");
        } else {
            userInputNumber = scanner.nextInt();
            if (userInputNumber < min || userInputNumber > max) {
                throw new IllegalArgumentException("The number out of range. Please, try again.");
            }
        }
        return userInputNumber;
    }
}
