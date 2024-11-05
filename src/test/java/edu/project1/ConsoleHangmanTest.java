package edu.project1;

import edu.project1.game.ConsoleHangman;
import edu.project1.handlers.GameOutputHandler;
import edu.project1.handlers.PlayerInputHandler;
import java.util.HashSet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleHangmanTest {

    private static ConsoleHangman consoleHangman;

    @BeforeAll
    public static void setUpConsoleHangman() {
        consoleHangman = new ConsoleHangman();
    }

    @Test
    public void consoleHangmanShouldRunIfTheUserStartPlaying() {
        PlayerInputHandler playerInput = mock(PlayerInputHandler.class);
        when(playerInput.getWordCategory()).thenReturn(1);
        when(playerInput.getAnswer()).thenReturn('N');
        consoleHangman = new ConsoleHangman(new GameOutputHandler(), playerInput);
        Assertions.assertThat(consoleHangman.getIsExit()).isEqualTo(false);
        consoleHangman.run();
    }

    @Test
    public void consoleHangmanShouldNotRunIfTheUserWantsToExit() {
        consoleHangman.setExit(true);
        consoleHangman.run();
        Assertions.assertThat(consoleHangman.getIsExit()).isEqualTo(true);
    }

    @Test
    public void sessionShouldStopIfTheUserWantsToExit() {
        PlayerInputHandler playerInput = mock(PlayerInputHandler.class);
        when(playerInput.getWordCategory()).thenReturn(1);
        when(playerInput.getSupposedLetter(new HashSet<>() {
            {
                for (char c = 'a'; c <= 'z'; c++) {
                    add(c);
                }
            }
        })).thenReturn(' ');
        when(playerInput.getAnswer()).thenReturn('N');
        consoleHangman = new ConsoleHangman(new GameOutputHandler(), playerInput);
        consoleHangman.run();
        Assertions.assertThat(consoleHangman.getIsExit()).isEqualTo(true);
    }
}
