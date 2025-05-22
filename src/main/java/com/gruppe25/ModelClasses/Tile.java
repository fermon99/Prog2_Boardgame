package com.gruppe25.ModelClasses;

/* Class for each tile object that makes up the board */

public class Tile {
  private Tile nextTile;
  private final int tileID;
  private TileAction landAction;

  public Tile(int tileID) {
    this.tileID = tileID;
  }

  /* Registers a player landing on this tile. If action tile -> perform (TileAction) */
  public void landPlayer(Player player) {
    if (landAction != null) {
      landAction.perform(player);
    }
  }

  public void setLandAction(TileAction action) {
    this.landAction = action;
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
