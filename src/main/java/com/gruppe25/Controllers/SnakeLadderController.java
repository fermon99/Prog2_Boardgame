package com.gruppe25.Controllers;

import java.util.List;

import com.gruppe25.GUIs.NewGameGUI;
import com.gruppe25.GUIs.SnakeLadderGUI;
import com.gruppe25.ModelClasses.Board;
import com.gruppe25.ModelClasses.BoardGame;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.PlayerReader;

public class SnakeLadderController {

  private Board board;
  private List<Player> players;
  private SnakeLadderGUI gui;

  public SnakeLadderController(SnakeLadderGUI gui) {
    this.gui = gui;
  }
  
  public void handleNewGame(String playerFileName, BoardGame boardgame) {
    List<Player> availablePlayers = PlayerReader.readPlayersFromCSV(playerFileName, boardgame);
    List<Player> selectedPlayers = NewGameGUI.showAndWait(availablePlayers);

    if (selectedPlayers != null && !selectedPlayers.isEmpty()) {
      gui.updatePlayerList(selectedPlayers);
    }
  }

  public void handleRollDice() {

  }
  
  public void handleBackButton() {
    
  }
}
