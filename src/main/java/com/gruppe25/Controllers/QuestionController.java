package com.gruppe25.Controllers;

import com.gruppe25.GUIs.QuestionGUI;
import com.gruppe25.ModelClasses.BoardReader;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.Question;
import com.gruppe25.ModelClasses.QuestionHandler;

public class QuestionController implements QuestionHandler {

  private final TrivialPursuitController trivialPursuitController;
  private QuestionGUI questionGUI;
  
  public QuestionController(TrivialPursuitController trivialPursuitController) {
    this.trivialPursuitController = trivialPursuitController;
    this.questionGUI = new QuestionGUI(this);
  }

  @Override
  public void handleQuestion(Player player, String category) {
    Question question = getRandomQuestion(category);
    questionGUI.question(player, category, question);
  }

  public Question getRandomQuestion(String category) {
    String filepath = trivialPursuitController.getBoardFile();
    Question question = BoardReader.loadQuestion(filepath, category);
    System.out.println(question);
    return question;
  }

  public boolean checkAnswer(Question question, String selectedAnswer) {
    return question.getCorrectAnswer().equals(selectedAnswer);
  }
}
