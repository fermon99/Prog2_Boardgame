package com.gruppe25;

public class Tile {
  private Tile nextTile;
  private final int tileID;
  private TileAction landAction;

  public Tile(int tileID) {
    this.tileID = tileID;
  }

  public void landPlayer(Player player) {
    if (landAction != null) {
      landAction.perform(player);
    }
  }

  public void setLandAction(TileAction action) {
    this.landAction = action;
  }

  public void leavePlayer(Player player) {

  }

  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

  public Tile getNextTile() {
    return nextTile;
  }

  public int getTileID() {
    return tileID;
  }

  public TileAction getLandAction() {
    return landAction;
  }

  @Override
  public String toString() {
    return  "" + tileID;
  }
}
