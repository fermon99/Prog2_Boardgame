package com.gruppe25;

public class LadderAction implements TileAction {
  private int destinationTileID; 
  private String description;

  public LadderAction(int destinationTileID, String description) {
    this.destinationTileID = destinationTileID;
    this.description = description;
  }

  @Override
  public void perform(Player player) {
    System.out.println("LadderAction triggered: " + description);
    player.placeOnTile(player.getBoardGame().getBoard().getTile(destinationTileID));
  }
}
