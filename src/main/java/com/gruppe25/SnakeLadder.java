package com.gruppe25;

public class SnakeLadder {
  private final Tile startTile;
  private final Tile endTile;
  
  public SnakeLadder(Tile startTile, Tile endTile) {
    this.startTile = startTile;
    this.endTile = endTile;
  }

  public Tile getStartTile() {
    return startTile;
  }

  public Tile getEndTile() {
    return endTile;
  }

}
