package com.gruppe25;

import com.gruppe25.Controllers.*;
import com.gruppe25.ModelClasses.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.*;


class ControllerTests {

    private TrivialPursuitController trivialController;
    private SnakeLadderController snakeController;

    @BeforeEach
    void setUp() {
        trivialController = new TrivialPursuitController();
        snakeController = new SnakeLadderController();
    }

    @Test
    void testUnknownGamePrintsMessage() {
        MainMenuController controller = new MainMenuController(null);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        controller.startGame("unknownGame", null);
        String output = outContent.toString().trim();
        assertTrue(output.contains("Unknown game"), "Should print 'Unknown game...'.");
    }

    @Test
    void testCorrectAnswerReturnsTrue() throws Exception {
        QuestionController controller = new QuestionController(trivialController);
        Question question = new Question("Q", Arrays.asList("A", "B", "C"), "A");
        Method checkAnswer = QuestionController.class.getDeclaredMethod("checkAnswer", Question.class, String.class);
        checkAnswer.setAccessible(true);
        boolean result = (boolean) checkAnswer.invoke(controller, question, "A");
        assertTrue(result,"Should be True.");
    }

    @Test
    void testIncorrectAnswerReturnsFalse() throws Exception {
        QuestionController controller = new QuestionController(trivialController);
        Question question = new Question("Q", Arrays.asList("A", "B", "C"), "A");
        Method checkAnswer = QuestionController.class.getDeclaredMethod("checkAnswer", Question.class, String.class);
        checkAnswer.setAccessible(true);
        boolean result = (boolean) checkAnswer.invoke(controller, question, "B");
        assertFalse(result,"Should be False.");
    }


    @Test
    void testSnakeWithoutPlayersPrintsMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        snakeController.handleRollDice();
        assertTrue(outContent.toString().contains("No players are selected"));
    }

    @Test
    void testWinnerNullWhenNoPlayers() {
        assertNull(snakeController.getWinner(),"Should be null since there are no players.");
    }
    
}