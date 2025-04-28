package com.gruppe25.Controllers;

import java.util.List;

import com.gruppe25.GUIs.NewGameGUI;
import com.gruppe25.GUIs.SnakeLadderGUI;
import com.gruppe25.ModelClasses.BoardGame;
import com.gruppe25.ModelClasses.Dice;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.PlayerReader;
import com.gruppe25.ModelClasses.Tile;

public class SnakeLadderController {

  private BoardGame boardgame;
  private List<Player> players;
  private Dice dice;
  private SnakeLadderGUI gui;
  private int currentPlayerIndex;

  public SnakeLadderController(SnakeLadderGUI gui) {
    this.gui = gui;
  }
  
  public void handleNewGame(String playerFileName, BoardGame boardgame) {
    this.boardgame = boardgame;
    List<Player> availablePlayers = PlayerReader.readPlayersFromCSV(playerFileName, boardgame);
    this.players = NewGameGUI.showAndWait(availablePlayers);
    this.currentPlayerIndex = 0;

    if (players != null && !players.isEmpty()) {
      for (Player player : players) {
          Tile startTile = boardgame.getBoard().getTile(0);
          player.placeOnTile(startTile);
      }
      gui.updatePlayerList(players);
      gui.updatePlayerPositions(players);
    }
  }

  public void handleRollDice() {
    Player currentPlayer = players.get(currentPlayerIndex);
    int roll = boardgame.getDice().roll();
    System.out.println(currentPlayer.getName() + " rolled " + roll);

    currentPlayer.move(roll);
    currentPlayer.getCurrentTile().landPlayer(currentPlayer);
    gui.updatePlayerPositions(players);

    /* Next player index logic */
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }
  
  public void handleBackButton() {

  }
}
