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
    board = new Board(); // Creating board

    // Add tiles and connecting them
    for (int i = 0; i < numberOfTiles+1; i++) {
        Tile tile = new Tile(i);
        board.addTiles(tile);
    } for (int i = 0; i <numberOfTiles; i++) {
      Tile current = board.getTile(i);
      Tile next = board.getTile(i+1);
      current.setNextTile(next);
    }

    // Add ladders
    board.addSnakeLadder(board.getTile(2), board.getTile(40));
    board.addSnakeLadder(board.getTile(8), board.getTile(16));
    board.addSnakeLadder(board.getTile(36), board.getTile(52));
    board.addSnakeLadder(board.getTile(47), board.getTile(66));
    board.addSnakeLadder(board.getTile(68), board.getTile(86));

    // Add snakes
    board.addSnakeLadder(board.getTile(24), board.getTile(5));
    board.addSnakeLadder(board.getTile(33), board.getTile(3));
    board.addSnakeLadder(board.getTile(53), board.getTile(41));
    board.addSnakeLadder(board.getTile(64), board.getTile(27));
    board.addSnakeLadder(board.getTile(88), board.getTile(70));

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
      player.placeOnTile(board.getTile(50));
    } 

    while (getWinner() == null) {
      for (Player player : players) {
        currentPlayer = player;
        int steps = dice.roll();
        player.move(steps);
        System.out.println(currentPlayer.getName() + " landed on tile " + currentPlayer.getCurrentTile());

        // Check if player landed on action tile
        Tile landedOn = currentPlayer.getCurrentTile();
        if (board.getSnakeLadders().containsKey(landedOn)) {
          Tile newTile = board.getSnakeLadders().get(landedOn);
          currentPlayer.placeOnTile(board.getTile(newTile.getTileID()));
          System.out.println(" | This is a ladder or snake! New position is on tile " + newTile.getTileID() + "!");
        }
      }
      System.out.println("______________________________");
    }
    System.out.println("The winner is: " + getWinner().getName());
  }

  public Player getWinner() {
    for (Player player : players) {
      if (player.getCurrentTile().getTileID() == board.getBoardSize() - 1) {
          return player;
      }
    }
    return null;  
  }

}
