package com.gruppe25.ModelClasses;

/* Used for testing and simulation
 * (No longer in use)
 */

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
