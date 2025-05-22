package com.gruppe25.ModelClasses;

import java.util.HashMap;
import java.util.Map;

/* Class for creating the board using tile objects.
 */

public class Board {
  private final Map<Integer, Tile> tiles;
  private final Map<Tile, Tile> snakeLadders; // remove?

  public Board() {
    tiles = new HashMap<>();
    snakeLadders = new HashMap<>();
  }

  public void addTiles(Tile tile) {
    tiles.put(tile.getTileID(), tile);
  }

  public Tile getTile(int tileID) {
    return tiles.get(tileID);
  }

  public void addSnakeLadder(Tile startTile, Tile endTile) { // remove?
    snakeLadders.put(startTile, endTile);
  }

  public Map<Tile, Tile> getSnakeLadders() { // remove?
    return snakeLadders;
  }

  public int getBoardSize() {
    return tiles.size();
  }

  public Map<Integer, Tile> getTiles() {
    return tiles;
  }

}
