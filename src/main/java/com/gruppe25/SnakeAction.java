package com.gruppe25;

public class SnakeAction implements TileAction {
  private final int destinationTileID; 
  private final String description;

  public SnakeAction(int destinationTileID, String description) {
    this.destinationTileID = destinationTileID;
    this.description = description;
  }

  @Override
  public void perform(Player player) {
    System.out.println(description + "  |  SnakeAction triggered: ");
    player.placeOnTile(player.getBoardGame().getBoard().getTile(destinationTileID));
  }

  public int getDestinationTileID() {
    return destinationTileID;
  }
}