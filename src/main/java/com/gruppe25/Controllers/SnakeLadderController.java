package com.gruppe25.Controllers;

import java.util.List;

import com.gruppe25.GUIs.GameGUI;
import com.gruppe25.GUIs.NewGameGUI;
import com.gruppe25.GUIs.SnakeLadderGUI;
import com.gruppe25.GUIs.WinnerGUI;
import com.gruppe25.ModelClasses.BoardGame;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.PlayerReader;
import com.gruppe25.ModelClasses.Tile;
import com.gruppe25.ModelClasses.TileActionAdder;

import javafx.stage.Stage;

/* Controller class for handling the Snakes and Ladders game:
 * Handles buttons like roll dice, new game, and back to main menu.
 * Handles win condition and moving players.
 */

public class SnakeLadderController {

  private final BoardGame boardgame;
  private List<Player> players;
  private SnakeLadderGUI gui;
  private int currentPlayerIndex;

  private Stage stage;

  /* File paths */
  private static final String playerFileName = "src/main/resources/players/SnakeLadderPlayers.csv";
  private static final String boardFileName = "src/main/resources/boards/SnakeLadderBoardgame.json";
  //private static final String boardFileName = "src/main/resources/boards/SnakeLadderBoardgameAlt.json"; // Alternate layout


  public SnakeLadderController() {
    this.boardgame = new BoardGame();
    this.boardgame.init(boardFileName, new TileActionAdder(null));

    this.gui = new SnakeLadderGUI(this);
  }

   /* Starting the gui */
  public void start(Stage stage) {
    this.stage = stage;
    gui.show(stage);
  }
  
  /* New game button -> pop-up gui -> select players */
  public void handleNewGame() {
    List<Player> availablePlayers = PlayerReader.readPlayersFromCSV(playerFileName, boardgame);
    this.players = NewGameGUI.showAndWait(availablePlayers);
    this.currentPlayerIndex = 0;

    if (players != null && !players.isEmpty()) {
      initializePlayers(players);
    }
  }

  /* Setting player ids and placing them on start-tile */
  private void initializePlayers(List<Player> players) {
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

  /* Handles players turn -> rolling dice and checks if player trigger win condition */
  public void handleRollDice() {
    if (players == null || players.isEmpty()) {
      System.out.println("No players are selected...");
      return;
    }

    Player currentPlayer = players.get(currentPlayerIndex);
    int roll = boardgame.getDice().roll();
    System.out.println(currentPlayer.getName() + " rolled " + roll);

    movePlayer(currentPlayer, roll);
    gui.updatePlayerPositions(players);  

    /* Win condition */
    if (getWinner() != null) {
      handleWin(getWinner());
      return;
    }
    nextPlayer();
  }

  /* Moves player based on dice roll */
  private void movePlayer(Player player, int roll) {
    player.move(roll);
    player.getCurrentTile().landPlayer(player);
  }

  /* Increments to next player (in a loop) */
  private void nextPlayer() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }

  /* Win condition */
  public Player getWinner() {
    if (players == null) return null;

    for (Player player : players) {
      if (player.getCurrentTile().getTileID() == boardgame.getBoard().getBoardSize() - 1) {
        return player;
      }
    }
    return null;
  }

  /* pop-up winner screen (with main menu or new game buttons) */
  public void handleWin(Player winner) {
    int choice = WinnerGUI.showAndWait(winner);
    if (choice == 1) {
      handleNewGame();
    } else if (choice == 2) {
      handleBackButton(stage);
    }
  }
  
  /* Handles if player wants to go back to main menu */
  public void handleBackButton(Stage stage) {
    MainMenuController mainMenuController = new MainMenuController(null);
    GameGUI gameGUI = new GameGUI(mainMenuController);
    mainMenuController.setGUI(gameGUI);
    gameGUI.start(stage);
  }

  /* Setters */
  public void setGUI(SnakeLadderGUI gui) {
    this.gui = gui;
  }

  /* Getters */
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
