package com.gruppe25;

public class Player {
  private final String name;
  private Tile currentTile;

  public Player(String name) {
    this.name = name;
  }

  public void placeOnTile(Tile tile) {
    currentTile = tile;
  }

  public void move(int steps) {
    
  } 

  public String getName() {
    return name;
  }

  public Tile getCurrentTile() {
    return currentTile;
  }

}
