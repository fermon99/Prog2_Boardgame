package com.gruppe25.Controllers;

import java.util.List;

import com.gruppe25.GUIs.GameGUI;
import com.gruppe25.GUIs.NewGameGUI;
import com.gruppe25.GUIs.SnakeLadderGUI;
import com.gruppe25.GUIs.WinnerGUI;
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

  /* File paths */
  private String playerFileName = "src/main/resources/players/SnakeLadderPlayers.csv";
  private String boardFileName = "src/main/resources/boards/SnakeLadderBoardgame.json";

  public SnakeLadderController(SnakeLadderGUI gui) {
    this.gui = gui;
    boardgame = new BoardGame();
  }
  
  public void handleNewGame() {
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

    /* Win condition */
    if (getWinner() != null) {
      handleWin(getWinner());
    }

    /* Next player index logic */
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }
  
  public void handleBackButton() {
    GameGUI.mainMenu();
  }

  public void handleWin(Player winner) {
    int choice = WinnerGUI.showAndWait(winner);
    if (choice == 1) {
      handleNewGame();
    } else if (choice == 2) {
      handleBackButton();
    }
  }

  public Player getWinner() {
    if (players == null) return null;

    for (Player player : players) {
      if (player.getCurrentTile().getTileID() == boardgame.getBoard().getBoardSize() - 1) {
        return player;
      }
    }
    return null;
  }

  public String getPlayerFile() {
    return playerFileName;
  }

  public String getBoardFile() {
    return boardFileName;
  }

  public BoardGame getBoardgame() {
    return boardgame;
  }

  public List<Player> getPlayers() {
    return players;
  }
}
