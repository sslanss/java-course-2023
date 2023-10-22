package edu.project1;

import edu.project1.handlers.PlayerInputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerInputHandlerTest {

    private static PlayerInputHandler playerInputHandler;

    private ByteArrayInputStream inContent;

    @Test
    public void getCharShouldReturnCharValue() {
        inContent = new ByteArrayInputStream("f\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertEquals(playerInputHandler.getChar(new HashSet<>(){{
            add('f');
        }}),'f');
    }

    @Test
    public void getCharShouldThrowsExcInCaseInputValueIsAString() {
        inContent = new ByteArrayInputStream("ff\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertThrows(InputMismatchException.class, () -> playerInputHandler.getChar(new HashSet<>(){{
            add('f');
        }}));
    }

    @Test
    public void getCharShouldThrowsExcInCaseInputValueIsANumber() {
        inContent = new ByteArrayInputStream("1\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertThrows(IllegalArgumentException.class, () -> playerInputHandler.getChar(new HashSet<>(){{
            add('f');
        }}));
    }
    @Test
    public void getCharShouldReturnDefaultCharValueInCaseOfExitInput() {
        inContent = new ByteArrayInputStream("exit\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertEquals(playerInputHandler.getChar(new HashSet<>(){{
            add('f');
        }}),' ');
    }

    @Test
    public void getCharShouldThrowExcInCaseLetterIsOutOfRange() {
        inContent = new ByteArrayInputStream("f\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertThrows(IllegalArgumentException.class, () -> playerInputHandler.getChar(new HashSet<>(){{
            add('a');
            add('b');
            add('c');
        }}));
    }
    @Test
    public void getIntShouldReturnIntValue() {
        inContent = new ByteArrayInputStream("1\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertEquals(playerInputHandler.getInt(1,3),1);
        inContent = new ByteArrayInputStream("3\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertEquals(playerInputHandler.getInt(1,3),3);
    }

    @Test
    public void getIntShouldThrowsExcInCaseInputValueIsNotANumber() {
        inContent = new ByteArrayInputStream("f\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertThrows(IllegalArgumentException.class, () -> playerInputHandler.getInt(1, 3));
    }

    @Test
    public void getIntShouldThrowExcInCaseLetterIsOutOfRange() {
        inContent = new ByteArrayInputStream("5\n".getBytes());
        playerInputHandler = new PlayerInputHandler( new Scanner(inContent));
        Assertions.assertThrows(IllegalArgumentException.class, () -> playerInputHandler.getInt(1, 3));
    }

}
