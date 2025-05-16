package com.gruppe25.Controllers;

import java.util.HashMap;
import java.util.List;

import com.gruppe25.GUIs.GameGUI;
import com.gruppe25.GUIs.NewGameGUI;
import com.gruppe25.GUIs.QuestionGUI;
import com.gruppe25.GUIs.TrivialPursuitGUI;
import com.gruppe25.GUIs.WinnerGUI;
import com.gruppe25.ModelClasses.BoardGame;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.PlayerReader;
import com.gruppe25.ModelClasses.Tile;
import com.gruppe25.ModelClasses.TileActionAdder;

import javafx.stage.Stage;

public class TrivialPursuitController {
  
  private final BoardGame boardgame;
  private TrivialPursuitGUI gui;
  private final QuestionController questionController;
  private List<Player> players;
  private int currentPlayerIndex;
  private final HashMap<Player, Integer> playerScores = new HashMap<>();

  private QuestionGUI questionGUI;
  private TileActionAdder tileActionAdder;
  private Stage stage;

  /* File paths */
  private static final String playerFileName = "src/main/resources/players/SnakeLadderPlayers.csv";
  private static final String boardFileName = "src/main/resources/boards/TrivialPursuitBoardgame.json";

  public TrivialPursuitController() {
    this.questionController = new QuestionController(this);
    this.questionGUI = new QuestionGUI(questionController);
    this.tileActionAdder = new TileActionAdder(questionController);
    this.boardgame = new BoardGame();
    this.boardgame.init(boardFileName, new TileActionAdder(questionController));
    this.gui = new TrivialPursuitGUI(this);
  }

  public void start(Stage stage) {
    this.stage = stage;
    gui.show(stage);
  }

  public void handleNewGame() {
    List<Player> availablePlayers = PlayerReader.readPlayersFromCSV(playerFileName, boardgame);
    this.players = NewGameGUI.showAndWait(availablePlayers);
    this.currentPlayerIndex = 0;

    if (players != null && !players.isEmpty()) {
      initializePlayers(players);
    }
  }

  private void initializePlayers(List<Player> players) {
    int i = 0;
      for (Player player : players) {
        player.setPlayerID(i);
        Tile startTile = boardgame.getBoard().getTile(0);
        player.placeOnTile(startTile);
        playerScores.put(player, 0);
        i++;
      }
      gui.updatePlayerList(players);
      gui.updatePlayerPositions(players);
  }

  public void handleCorrectAnswer(Player player) {
    playerScores.put(player, playerScores.getOrDefault(player, 0) + 1);
    System.out.println("Correct answer - player score is now " + playerScores.get(player));
  }

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

  private void movePlayer(Player player, int roll) {
    player.move(roll);
    player.getCurrentTile().landPlayer(player);
  }

  private void nextPlayer() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }

  public Player getWinner() {
    if (players == null) return null;

    for (Player player : players) {
      if (playerScores.get(player) >= 5) {
        return player;
      }
    }
    return null;
  }

  public void handleWin(Player winner) {
    int choice = WinnerGUI.showAndWait(winner);
    if (choice == 1) {
      handleNewGame();
    } else if (choice == 2) {
      handleBackButton(stage);
    }
  }

  public void handleBackButton(Stage stage) {
    MainMenuController mainMenuController = new MainMenuController(null);
    GameGUI gameGUI = new GameGUI(mainMenuController);
    mainMenuController.setGUI(gameGUI);
    gameGUI.start(stage);
  }

  public void setGUI(TrivialPursuitGUI gui) {
    this.gui = gui;
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
  
  public TileActionAdder getTileActionAdder() {
    return tileActionAdder;
  }

  public QuestionGUI getQuestionGUI() {
    return questionGUI;
  }
}
