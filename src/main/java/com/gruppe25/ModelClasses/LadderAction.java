package com.gruppe25.ModelClasses;

/* Class for handling the ladder action in snakes and ladders */

public class LadderAction implements TileAction {
  private final int destinationTileID; 
  private final String description;

  public LadderAction(int destinationTileID, String description) {
    this.destinationTileID = destinationTileID;
    this.description = description;
  }

  /* Triggers when player lands on a ladder tile: get placed on top of the ladder (destination) */
  @Override
  public void perform(Player player) {
    System.out.println("  |  LadderAction triggered: " + description);
    player.placeOnTile(player.getBoardGame().getBoard().getTile(destinationTileID));
  }

  /* Getters */
  public int getDestinationTileID() {
    return destinationTileID;
  }
}
