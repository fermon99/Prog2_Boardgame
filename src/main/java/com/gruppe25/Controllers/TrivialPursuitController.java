package com.gruppe25.Controllers;

import java.util.List;

import com.gruppe25.GUIs.GameGUI;
import com.gruppe25.GUIs.NewGameGUI;
import com.gruppe25.GUIs.TrivialPursuitGUI;
import com.gruppe25.GUIs.WinnerGUI;
import com.gruppe25.ModelClasses.BoardGame;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.PlayerReader;
import com.gruppe25.ModelClasses.Tile;

public class TrivialPursuitController {
  
  private final BoardGame boardgame;
  private List<Player> players;
  private final TrivialPursuitGUI gui;
  private int currentPlayerIndex;

  /* File paths */
  private final String playerFileName = "src/main/resources/players/SnakeLadderPlayers.csv";
  private final String boardFileName = "src/main/resources/boards/TrivialPursuitBoardgame.json";

  public TrivialPursuitController(TrivialPursuitGUI gui) {
    this.gui = gui;
    boardgame = new BoardGame();
  }

  public void handleNewGame() {
    List<Player> availablePlayers = PlayerReader.readPlayersFromCSV(playerFileName, boardgame);
    this.players = NewGameGUI.showAndWait(availablePlayers);
    this.currentPlayerIndex = 0;

    if (players != null && !players.isEmpty()) {
      int i = 0;
      for (Player player : players) {
        player.setPlayerID(i);
        Tile startTile = boardgame.getBoard().getTile(0);
        player.placeOnTile(startTile);
        i++;
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
