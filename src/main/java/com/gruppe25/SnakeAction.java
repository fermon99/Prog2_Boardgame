package com.gruppe25;

public class SnakeAction implements TileAction {
  private int destinationTileID; 
  private String description;

  public SnakeAction(int destinationTileID, String description) {
    this.destinationTileID = destinationTileID;
    this.description = description;
  }

  @Override
  public void perform(Player player) {
    System.out.println("SnakeAction triggered: " + description);
    player.placeOnTile(player.getBoardGame().getBoard().getTile(destinationTileID));
  }
}