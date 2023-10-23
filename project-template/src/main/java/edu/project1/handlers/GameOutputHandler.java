package edu.project1.handlers;

import edu.project1.game.GuessResult;
import java.util.Set;

public class GameOutputHandler {

    public void displayGuessResult(GuessResult result) {
        System.out.println(result.getComment());
    }

    public void displayGameStatus(char[] userAnswer, Set<Character> availableLetters, int attempts) {
        System.out.println("\nAvailable letters to choose:");
        for (var letter : availableLetters) {
            System.out.print(letter + " ");
        }
        System.out.println();
        System.out.println("The hidden word is:");
        for (var symbol : userAnswer) {
            System.out.print(symbol + " ");
        }
        System.out.println();
        System.out.println("You have " + attempts + " attempts left.\n");
    }
}
