package com.gruppe25.ModelClasses;

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
    questionHandler.handleQuestion(player, category);
  }

  public String getCategory() {
    return category;
  }
  
}
