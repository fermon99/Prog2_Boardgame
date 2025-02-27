package com.gruppe25;

import java.util.ArrayList;
import java.util.List;

public class BoardGame {
  private Board board;
  private Player currentPlayer;
  private final List<Player> players;
  private Dice dice;

  public BoardGame() {
    players = new ArrayList<>();
  }

  public void addPlayer(Player player) {
    players.add(player);
  }

  public void createBoard(int numberOfTiles) {
    board = new Board();
    for (int i = 0; i < numberOfTiles; i++) {
        Tile tile = new Tile(i);
        board.addTiles(tile);
    }
  }

  public void createDice(int numberOfDice) {
    dice = new Dice(numberOfDice);
  }

  public void play() {
    if (players.isEmpty()) {
      System.out.println("No players are playing...");
      return;
    }

    currentPlayer = players.get(0);

    for (Player player : players) {
      player.placeOnTile(board.getTile(0));
    } 

    while (getWinner() == null) {
      for (Player player : players) {
        currentPlayer = player;
        int steps = dice.roll();
        System.out.println(currentPlayer.getName() + " landed on tile " + currentPlayer.getCurrentTile());
      }
      System.out.println("______________________________");
    }
    System.out.println("The winner is: " + getWinner().getName());
  }

  //need to rewrite to accomodate different boards
  public Player getWinner() {
    if (currentPlayer.getCurrentTile().getTileID() > 100) {
      return currentPlayer;
    } else {
      return null;
    }
  }

}
