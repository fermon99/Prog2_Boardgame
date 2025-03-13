package com.gruppe25;

import java.util.HashMap;
import java.util.Map;

public class Board {
  private final Map<Integer, Tile> tiles;
  private final Map<Tile, Tile> snakeLadders;

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

  public void addSnakeLadder(Tile startTile, Tile endTile) {
    snakeLadders.put(startTile, endTile);
  }

  public Map<Tile, Tile> getSnakeLadders() {
    return snakeLadders;
  }

  public int getBoardSize() {
    return tiles.size();
  }

}
