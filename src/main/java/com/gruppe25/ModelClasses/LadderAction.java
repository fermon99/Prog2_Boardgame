package com.gruppe25.ModelClasses;

public class LadderAction implements TileAction {
  private final int destinationTileID; 
  private final String description;

  public LadderAction(int destinationTileID, String description) {
    this.destinationTileID = destinationTileID;
    this.description = description;
  }

  @Override
  public void perform(Player player) {
    System.out.println("  |  LadderAction triggered: " + description);
    player.placeOnTile(player.getBoardGame().getBoard().getTile(destinationTileID));
  }

  public int getDestinationTileID() {
    return destinationTileID;
  }
}
