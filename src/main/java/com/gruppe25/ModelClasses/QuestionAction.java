package com.gruppe25.ModelClasses;

/* Class for handling question actions in Trivial Pursuit */

public class QuestionAction implements TileAction {
  private final String category;
  private final String description;
  private final QuestionHandler questionHandler;

  public QuestionAction(String category, String description, QuestionHandler questionHandler) {
    this.category = category;
    this.description = description;
    this.questionHandler = questionHandler;
  }

  @Override
  public void perform(Player player) {
    System.out.println("  |  QuestionAction triggered: " + description);
    questionHandler.handleQuestion(player, category); // QuestionHandler uses current player and landed category (each tile has a category)
  }

  public String getCategory() {
    return category;
  }
  
}
