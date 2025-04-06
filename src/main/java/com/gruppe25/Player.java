package com.gruppe25;

public class Player {
  private final String name;
  private Tile currentTile;
  private BoardGame boardGame;

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
    //currentTile.landPlayer(this);
    
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

}
