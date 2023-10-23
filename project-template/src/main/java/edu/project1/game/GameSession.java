package edu.project1.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class GameSession {

    private final String answer;

    private final char[] userAnswer;

    private final Set<Character> availableLetters;

    private static final int MAX_ATTEMPTS = 7;

    private int availableAttempts;

    public GameSession(String hiddenWord) {
        answer = hiddenWord;
        userAnswer = new char[hiddenWord.length()];
        Arrays.fill(userAnswer, '_');
        int spacePosition = answer.indexOf(' ');
        while (spacePosition != -1) {
            userAnswer[spacePosition] = ' ';
            spacePosition = answer.indexOf(' ', spacePosition + 1);
        }
        availableLetters = new HashSet<>() {{
            for (char c = 'a'; c <= 'z'; c++) {
                add(c);
            }
        }};
        availableAttempts = MAX_ATTEMPTS;
    }

    private void changeUserAnswer(char guessedLetter) {
        int position = answer.indexOf(guessedLetter);
        while (position != -1) {
            userAnswer[position] = guessedLetter;
            position = answer.indexOf(guessedLetter, position + 1);
        }
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public Set<Character> getAvailableLetters() {
        return availableLetters;
    }

    public int getAvailableAttempts() {
        return availableAttempts;
    }

    public String getAnswer() {
        return answer;
    }

    @NotNull
    public GuessResult tryGuess(char letter) {
        availableLetters.remove(letter);
        if (answer.contains(String.valueOf(letter))) {
            changeUserAnswer(letter);
            if (!Arrays.toString(userAnswer).contains(String.valueOf('_'))) {
                return GuessResult.WIN;
            } else {
                return GuessResult.SUCCESSFUL_GUESS;
            }
        } else {
            availableAttempts--;
            if (availableAttempts == 0) {
                return GuessResult.DEFEAT;
            } else {
                return GuessResult.FAILED_GUESS;
            }
        }
    }

    @NotNull
    public GuessResult giveUp() {
        return GuessResult.DEFEAT;
    }
}
