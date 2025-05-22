package com.gruppe25.ModelClasses;

/* Class for each player object in the game.
 * Features key methods for movement and positions
 */

public class Player {
  private final String name;
  private Tile currentTile;
  private final BoardGame boardGame;
  private int playerID;

  public Player(String name, BoardGame boardGame) {
    this.name = name;
    this.boardGame = boardGame;
  }

  public void placeOnTile(Tile tile) {
    currentTile = tile;
  }

  public void move(int steps) {
    for (int i = 0; i < steps; i++) {
      if (currentTile.getNextTile() == null) {
        break;
      }
      currentTile = currentTile.getNextTile();
    }
  }

  public String getName() {
    return name;
  }

  public Tile getCurrentTile() {
    return currentTile;
  }

  public BoardGame getBoardGame() {
    return boardGame;
  }

  public int getPlayerID() {
    return playerID;
  }

  public void setPlayerID(int playerID) {
    this.playerID = playerID;
  }

  @Override
  public String toString() {
    return name;
  }

}
