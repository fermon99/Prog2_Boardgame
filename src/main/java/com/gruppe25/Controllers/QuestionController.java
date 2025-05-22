package com.gruppe25.Controllers;

import com.gruppe25.GUIs.QuestionGUI;
import com.gruppe25.ModelClasses.BoardReader;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.Question;
import com.gruppe25.ModelClasses.QuestionHandler;

/* Controller class for question pop-up (using the question handler) */

public class QuestionController implements QuestionHandler {

  private final TrivialPursuitController trivialPursuitController;
  private QuestionGUI questionGUI;
  private String result;
  
  public QuestionController(TrivialPursuitController trivialPursuitController) {
    this.trivialPursuitController = trivialPursuitController;
    this.questionGUI = new QuestionGUI(this);
  }

  /* Method for acquiring question and checking if correct */
  @Override
  public void handleQuestion(Player player, String category) {
    Question question = getRandomQuestion(category);
    result = questionGUI.question(player, category, question);
    if (checkAnswer(question, result) == true) {
      trivialPursuitController.handleCorrectAnswer(player);
    } 
  }

  /* Gets random question from the board reader */
  public Question getRandomQuestion(String category) {
    String filepath = trivialPursuitController.getBoardFile();
    Question question = BoardReader.loadQuestion(filepath, category);
    System.out.println(question);
    return question;
  }

  /* Checks if the answer matches the correct answer from the question-object */
  private boolean checkAnswer(Question question, String selectedAnswer) {
    return question.getCorrectAnswer().equals(selectedAnswer);
  }
}
