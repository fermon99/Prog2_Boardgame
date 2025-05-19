package com.gruppe25;

import com.gruppe25.Controllers.*;
import com.gruppe25.ModelClasses.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTests {

    private TrivialPursuitController trivialController;
    private SnakeLadderController snakeController;

    @BeforeEach
    void setUp() {
        trivialController = new TrivialPursuitController();
        snakeController = new SnakeLadderController();
    }

    //MainMenuController tests
    @Test
    void testStartUnknownGamePrintsUnknownGameMessage() {
        MainMenuController controller = new MainMenuController(null);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controller.startGame("unknownGame", null);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Unknown game"), "Should print 'Unknown game...' for unknown gameType.");
    }

    //QuestionController test
    @Test
    void testAnswerReturnsTrueWhenCorrect() throws Exception {
        QuestionController controller = new QuestionController(trivialController);
        Question question = new Question("Q", Arrays.asList("A", "B", "C"), "A");

        Method checkAnswer = QuestionController.class.getDeclaredMethod("checkAnswer", Question.class, String.class);
        checkAnswer.setAccessible(true);

        boolean result = (boolean) checkAnswer.invoke(controller, question, "A");

        assertTrue(result);
    }

    @Test
    void testAnswerReturnsFalseWhenIncorrect() throws Exception {
        QuestionController controller = new QuestionController(trivialController);
        Question question = new Question("Q", Arrays.asList("A", "B", "C"), "A");

        Method checkAnswer = QuestionController.class.getDeclaredMethod("checkAnswer", Question.class, String.class);
        checkAnswer.setAccessible(true);

        boolean result = (boolean) checkAnswer.invoke(controller, question, "B");

        assertFalse(result);
    }

    //SnakeLadderController tests

    @Test
    void testSnakeWithoutPlayersPrintsMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        snakeController.handleRollDice();

        assertTrue(outContent.toString().contains("No players are selected"));
    }

    @Test
    void testSnakeReturnsNullWhenNoPlayers() {
        assertNull(snakeController.getWinner());
    }

    //TrivialPursuitController tests
    @Test
    void testCorrectAnswerIncreasesScore() {
        Player mockPlayer = new Player("TestPlayer", null);
        trivialController.handleCorrectAnswer(mockPlayer);
        trivialController.handleCorrectAnswer(mockPlayer);

        Player winner = null;
        //Answer 3 questions correctly to reach 5
        for (int i = 0; i < 3; i++) {
            trivialController.handleCorrectAnswer(mockPlayer);
        }
        winner = trivialController.getWinner();

        assertNotNull(winner);
        assertEquals("TestPlayer", winner.getName());
    }

    @Test
    void testNoWinnerIfNoPlayerAboveScoreLimit() {
        Player mockPlayer = new Player("LowScore", null);
        trivialController.handleCorrectAnswer(mockPlayer);
        trivialController.handleCorrectAnswer(mockPlayer);

        Player winner = trivialController.getWinner();

        assertNull(winner);
    }
}