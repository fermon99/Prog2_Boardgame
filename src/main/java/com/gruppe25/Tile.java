package com.gruppe25;

public class Tile {
  private Tile nextTile;
  private final int tileID;
  /*private TileAction landAction; */

  public Tile(int tileID) {
    this.tileID = tileID;
  }

  public void landPlayer(Player player) {

  }

  public void leavePlayer(Player player) {

  }

  public void setNextTile(Tile nextTile) {

  }

  public Tile getNextTile() {
    return nextTile;
  }

  public int getTileID() {
    return tileID;
  }

  @Override
  public String toString() {
    return  "" + tileID;
  }
}
