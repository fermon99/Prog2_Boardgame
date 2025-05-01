package com.gruppe25.ModelClasses;

import java.util.Map;

public class TileActionAdder {
  private final QuestionHandler questionHandler;

  public TileActionAdder(QuestionHandler questionHandler) {
    this.questionHandler = questionHandler;
  }

  public TileAction createTileAction(Map<String, Object> actionData) {
    String actionType = (String) actionData.get("actionType");
    String description = (String) actionData.get("description");

    switch (actionType) {
      case "Ladder":
        int destinationTileID = ((Double) actionData.get("destinationTileId")).intValue();
        return new LadderAction(destinationTileID, description);

      case "Snake":
        destinationTileID = ((Double) actionData.get("destinationTileId")).intValue();
        return new SnakeAction(destinationTileID, description);

      case "Question":
        String category = (String) actionData.get("category");
        return new QuestionAction(category, description, questionHandler);

      default:
        throw new IllegalArgumentException("Unknown action type...");
    }
  }
}
