package edu.project1;

import java.util.HashSet;

import edu.project1.game.GameSession;
import edu.project1.game.GuessResult;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameSessionTest {

    private GameSession gameSession;
    @Test
    public void gameSessionShouldCreateCorrectUserAnswerFieldInCaseHiddenWordContainsSpaces() {
        String testHiddenWord = "wo rd";
        gameSession = new GameSession(testHiddenWord);
        Assertions.assertThat(gameSession.getUserAnswer()).isEqualTo(new char[] {'_','_',' ','_','_' });
    }

    @BeforeEach
    public void setUpGameSession() {
        String testHiddenWord = "word";
        gameSession = new GameSession(testHiddenWord);
    }
    @Test
    public void gameSessionShouldCreateCorrectFields() {
        Assertions.assertThat(gameSession.getAnswer()).isEqualTo("word");
        Assertions.assertThat(gameSession.getUserAnswer()).isEqualTo(new char[] {'_','_','_','_' });
        char[] testAvailableLetters = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Assertions.assertThat(gameSession.getAvailableLetters()).isEqualTo(new HashSet<>(Arrays.asList(testAvailableLetters)));
        Assertions.assertThat(gameSession.getAvailableAttempts()).isEqualTo(7);
    }

    @Test
    public void tryGuessShouldReturnCorrectGuessResult() {
        GuessResult guessResult = gameSession.tryGuess('w');
        Assertions.assertThat(guessResult).isEqualTo(GuessResult.SUCCESSFUL_GUESS);
        guessResult = gameSession.tryGuess('a');
        Assertions.assertThat(guessResult).isEqualTo(GuessResult.FAILED_GUESS);
    }
    @Test
    public void tryGuessShouldReturnDefeatResultAfterExceedingTheAttemptsNumber() {
        gameSession.tryGuess('z');
        gameSession.tryGuess('s');
        gameSession.tryGuess('b');
        gameSession.tryGuess('e');
        gameSession.tryGuess('h');
        gameSession.tryGuess('z');
        GuessResult guessResult = gameSession.tryGuess('a');
        Assertions.assertThat(guessResult).isEqualTo(GuessResult.DEFEAT);
    }
    @Test
    public void tryGuessShouldReturnWinResultAfterGuessingAllLetters() {
        gameSession.tryGuess('w');
        gameSession.tryGuess('o');
        gameSession.tryGuess('r');
        GuessResult guessResult = gameSession.tryGuess('d');
        Assertions.assertThat(guessResult).isEqualTo(GuessResult.WIN);
    }
    @Test
    public void attemptsShouldDecreaseAfterFailedGuess() {
        gameSession.tryGuess('h');
        Assertions.assertThat(gameSession.getAvailableAttempts()).isEqualTo(6);
    }
    @Test
    public void attemptsShouldNotDecreaseAfterSuccessfulGuess() {
        gameSession.tryGuess('w');
        Assertions.assertThat(gameSession.getAvailableAttempts()).isEqualTo(7);
    }
}
