package edu.project1;

import edu.project1.game.GuessResult;
import edu.project1.handlers.GameOutputHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameOutputHandlerTest {
    private static GameOutputHandler gameOutputHandler;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    @BeforeAll
    public static void setUpAll() {
        gameOutputHandler = new GameOutputHandler();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    public void displayGuessResultShouldPrintCorrectGuessResultComment() {
        GuessResult guessResult = GuessResult.SUCCESSFUL_GUESS;
        gameOutputHandler.displayGuessResult(guessResult);
        Assertions.assertEquals("You are right!", outputStream.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
