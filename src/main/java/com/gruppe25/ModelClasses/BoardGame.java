package com.gruppe25.ModelClasses;

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

  
  public void createBoard(String filepath) {
    board = BoardReader.loadBoard(filepath, this);
  }
  

  public void createDice(String filepath) {
    dice = BoardReader.loadDice(filepath, this);
  }

  public void init(String filepath) {
    createBoard(filepath);
    createDice(filepath);
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
        player.move(steps);
        System.out.println(currentPlayer.getName() + " landed on tile " + currentPlayer.getCurrentTile());

        // Check if player landed on action tile
        Tile landedOn = currentPlayer.getCurrentTile();
        TileAction action = landedOn.getLandAction();
        if (action != null) {
          action.perform(currentPlayer);
          // System.out.println(" | Action triggered! New position is on tile " + currentPlayer.getCurrentTile().getTileID() + "!");
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

  public Board getBoard() {
    return board;
  }

  public Dice getDice() {
    return dice;
  }

}
