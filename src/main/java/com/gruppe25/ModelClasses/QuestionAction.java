package com.gruppe25.ModelClasses;

public class QuestionAction implements TileAction {
  private final String category;
  private final String description;

  public QuestionAction(String category, String description) {
    this.category = category;
    this.description = description;
  }

  @Override
  public void perform(Player player) {
    System.out.println("  |  QuestionAction triggered: " + description);
  }

  public String getCategory() {
    return category;
  }
  
}
