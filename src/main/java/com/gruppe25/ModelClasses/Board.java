package com.gruppe25.ModelClasses;

import java.util.HashMap;
import java.util.Map;

/* Class for creating the board using tile objects.
 */

public class Board {
  private final Map<Integer, Tile> tiles;

  public Board() {
    tiles = new HashMap<>();
  }

  /* Adding tiles to board map */
  public void addTiles(Tile tile) {
    tiles.put(tile.getTileID(), tile);
  }

  /* getters */
  public Tile getTile(int tileID) {
    return tiles.get(tileID);
  }

  public int getBoardSize() {
    return tiles.size();
  }

  public Map<Integer, Tile> getTiles() {
    return tiles;
  }

}
