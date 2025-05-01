package com.gruppe25.Controllers;

import com.gruppe25.ModelClasses.BoardReader;
import com.gruppe25.ModelClasses.Question;

public class QuestionController {

  private final TrivialPursuitController trivialPursuitController;
  
  public QuestionController(TrivialPursuitController trivialPursuitController) {
    this.trivialPursuitController = trivialPursuitController;
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
