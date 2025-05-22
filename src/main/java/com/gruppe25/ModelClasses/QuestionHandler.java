package com.gruppe25.ModelClasses;

/* Interface for handling what question from what category the player will get when landing on question-tile */

public interface QuestionHandler {
  void handleQuestion(Player player, String category);
}
