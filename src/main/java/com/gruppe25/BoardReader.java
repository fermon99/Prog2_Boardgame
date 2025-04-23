package com.gruppe25;

import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class BoardReader {
  private static Board board;
  private static Dice dice;

  public static Board loadBoard(String filepath, BoardGame boardGame) {
    try {
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new FileReader(filepath));
      BoardData boardData = gson.fromJson(reader, BoardData.class);

      board = new Board(); // Creating board

      // Add tiles and connecting them
      for (int i = 0; i < boardData.numberOfTiles+1; i++) {
          Tile tile = new Tile(i);
          board.addTiles(tile);
      } for (int i = 0; i <boardData.numberOfTiles; i++) {
        Tile current = board.getTile(i);
        Tile next = board.getTile(i+1);
        current.setNextTile(next);

        for (Map<String, Object> tileMap : boardData.actionTiles) {
          int tileID = ((Double) tileMap.get("tileId")).intValue();

          Map<String, Object> action = (Map<String, Object>) tileMap.get("action");
          String actionType = (String) action.get("actionType");
          int destinationTileID = ((Double) action.get("destinationTileId")).intValue();
          String description = (String) action.get("description");

          if (actionType.equals("Ladder")) {
            board.getTile(tileID).setLandAction(new LadderAction(destinationTileID, description));
          }
          if (actionType.equals("Snake")) {
            board.getTile(tileID).setLandAction(new SnakeAction(destinationTileID, description));
          }
        }
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
}
