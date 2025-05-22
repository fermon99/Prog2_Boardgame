package com.gruppe25.ModelClasses;

import java.util.List;

/* Class for question objects in Trivial Pursuit
 * Information about the given question is stored here
 */

public class Question {

  private final String questionText;
  private final List<String> options;
  private final String correctAnswer;

  public Question(String questionText, List<String> options, String correctAnswer) {
    this.questionText = questionText;
    this.options = options;
    this.correctAnswer = correctAnswer;
  }

  public String getQuestionText() {
    return questionText;
  }

  public List<String> getOptions() {
    return options;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  @Override
  public String toString() {
    return "Question{" +
            "questionText='" + questionText + '\'' +
            ", options=" + options +
            ", correctAnswer='" + correctAnswer + '\'' +
            '}';
  }
  
}
