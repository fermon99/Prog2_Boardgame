package com.gruppe25.ModelClasses;

import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class BoardReader {
  private static Board board;
  private static Dice dice;
  private static Question question;

  public static Board loadBoard(String filepath, TileActionAdder tileActionAdder) {
    try {
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new FileReader(filepath));
      BoardData boardData = gson.fromJson(reader, BoardData.class);

      Board board = new Board();

      /* Add tiles and connect them */
      for (int i = 0; i < boardData.numberOfTiles+1; i++) {
          Tile tile = new Tile(i);
          board.addTiles(tile);
      } 
      for (int i = 0; i <boardData.numberOfTiles; i++) {
        Tile current = board.getTile(i);
        Tile next = board.getTile(i+1);
        current.setNextTile(next);

        for (Map<String, Object> tileMap : boardData.actionTiles) {
            int tileID = ((Double) tileMap.get("tileId")).intValue();
            Map<String, Object> actionData = (Map<String, Object>) tileMap.get("action");
  
            TileAction tileAction = tileActionAdder.createTileAction(actionData);
            board.getTile(tileID).setLandAction(tileAction);
          }
        }
      if (boardData.loopableBoard == true) {
        Tile current = board.getTile(boardData.numberOfTiles);
        current.setNextTile(board.getTile(1));
      }
      return board;

    } catch (Exception e) {
      System.out.println("Error - something happened when loading in .json");
      e.printStackTrace();
      return board;
    }
  }

  public static Dice loadDice(String filepath, BoardGame boardGame) {
    try {
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new FileReader(filepath));
      BoardData boardData = gson.fromJson(reader, BoardData.class);

      dice = new Dice(boardData.numberOfDice);
      return dice;
    } catch (Exception e) {
      System.out.println("Error - something happened when loading in .json");
      e.printStackTrace();
      return dice;
    }
  }

  public static Question loadQuestion(String filepath, String category) {
    try {
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new FileReader(filepath));
      QuestionData questionData = gson.fromJson(reader, QuestionData.class);

      List<Question> questionList = questionData.questions.get(category);

      if (questionList.isEmpty()) {
        System.out.println("No questions found for " + category);
        return null;
      }

      Random random = new Random();
      return questionList.get(random.nextInt(questionList.size()));
    } catch (Exception e) {
      System.out.println("Error . something happened when loading in .json");
      e.printStackTrace();
      return null;
    }
  }
}
