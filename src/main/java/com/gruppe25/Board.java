package com.gruppe25;

import java.util.HashMap;
import java.util.Map;

public class Board {
  private final Map<Integer, Tile> tiles;

  public Board() {
    tiles = new HashMap<>();
  }

  public void addTiles(Tile tile) {
    tiles.put(tile.getTileID(), tile);
  }

  public Tile getTile(int tileID) {
    return tiles.get(tileID);
  }

  public int getBoardSize() {
    return tiles.size();
  }

}
