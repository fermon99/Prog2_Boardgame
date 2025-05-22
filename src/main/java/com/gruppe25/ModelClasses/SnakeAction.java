package com.gruppe25.ModelClasses;

/* Class for handling the snake action in snakes and ladders */

public class SnakeAction implements TileAction {
  private final int destinationTileID; 
  private final String description;

  public SnakeAction(int destinationTileID, String description) {
    this.destinationTileID = destinationTileID;
    this.description = description;
  }

  /* Triggers when player lands on a snake tile: get placed on bottom of snake (destination) */
  @Override
  public void perform(Player player) {
    System.out.println(description + "  |  SnakeAction triggered: ");
    player.placeOnTile(player.getBoardGame().getBoard().getTile(destinationTileID));
  }

  public int getDestinationTileID() {
    return destinationTileID;
  }
}