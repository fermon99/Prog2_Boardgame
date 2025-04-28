package com.gruppe25.GUIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gruppe25.Controllers.SnakeLadderController;
import com.gruppe25.ModelClasses.Board;
import com.gruppe25.ModelClasses.BoardGame;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.PlayerReader;
import com.gruppe25.ModelClasses.Tile;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SnakeLadderGUI {

  private SnakeLadderController controller;
  private ListView<Player> activePlayerListView;

  private Map<Integer, StackPane> tilePanes = new HashMap<>();
  private List<Player> players;
  private BoardGame boardgame;
  private int currentPlayerIndex;

  public SnakeLadderGUI() {
    controller = new SnakeLadderController(this);
  }

  public Scene createScene() {
      /* File paths */
      String playerFileName = "src/main/resources/players/SnakeLadderPlayers.csv";
      String boardFileName = "src/main/resources/boards/SnakeLadderBoardgame.json";

      /* Create board */
      BoardGame boardgame = new BoardGame();
      boardgame.createBoard(boardFileName);

      /* Create dice */
      boardgame.createDice(boardFileName);

      Board board = boardgame.getBoard();
      List<Player> players = PlayerReader.readPlayersFromCSV(playerFileName, boardgame);

      /* Sidebar */
      VBox sideBar = new VBox();
      sideBar.setPrefWidth(200);
      sideBar.setStyle("-fx-background-color:rgb(148, 148, 148); -fx-padding: 10;");

      Button newGameButton = new Button("New game");
      Button rollDiceButton = new Button("Roll dice");
      Button backButton = new Button("Back to selection");

      Label activePlayersLabel = new Label("Active players");
      activePlayerListView = new ListView<>();

      sideBar.getChildren().addAll(new Label("Controls"),
                                   newGameButton, 
                                   rollDiceButton,
                                   activePlayersLabel,
                                   activePlayerListView,
                                   backButton);

      /* Main content area */
      Pane mainArea = new Pane();
      mainArea.setStyle("-fx-background-color:rgb(235, 235, 235)");

      /* Boardgrid */
      GridPane boardGrid = new GridPane();
      boardGrid.setHgap(5);
      boardGrid.setVgap(5);
      boardGrid.setStyle("-fx-background-color: rgb(235, 235, 235);");

      int columns = 9;
      int tileSize = 70;

      for (Tile tile : board.getTiles().values()) {
        int tileID = tile.getTileID();

        int row = (tileID - 1 )/ columns;
        int column = (tileID - 1) % columns;

        /* For zig-zag pattern */
        if (row % 2 == 1) {
          column = columns - 1 - column;
        }

        StackPane tilePane = new StackPane();
        tilePane.setPrefSize(tileSize, tileSize);
        tilePane.setStyle("-fx-border-color: black; -fx-background-color: white;");

        tilePanes.put(tileID, tilePane);

        if (tileID == 0) {
          Label startLabel = new Label("Start");
          tilePane.getChildren().add(startLabel);
          boardGrid.add(tilePane, 0, 10);
        } else {
          Label label = new Label(String.valueOf(tileID));
          tilePane.getChildren().add(label);

          if (tile.getLandAction() != null) {
            String type = tile.getLandAction().getClass().getSimpleName();
            if (type.equals("LadderAction")) {
              tilePane.setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
            } else if (type.equals("SnakeAction")) {
              tilePane.setStyle("-fx-background-color: lightcoral; -fx-border-color: black;");
            }
          }
          boardGrid.add(tilePane, column, 9 - row);
        }
      }

      ScrollPane scrollPane = new ScrollPane(boardGrid);
      scrollPane.setFitToWidth(true);
      scrollPane.setFitToHeight(true);

      /* Layout */
      BorderPane root = new BorderPane();
      root.setLeft(sideBar);
      root.setCenter(mainArea);
      root.setCenter(scrollPane);

      /* Players */
      ArrayList<String> playerColors = new ArrayList<>();
      playerColors.add("red");
      playerColors.add("blue");
      playerColors.add("yellow");
      playerColors.add("green");

      /* Dice */

      /* New game button */
      newGameButton.setOnAction(e -> controller.handleNewGame(playerFileName, boardgame));

      /* Roll dice button */
      rollDiceButton.setOnAction(e -> controller.handleRollDice());
      
      /* Back button */
      backButton.setOnAction(e -> controller.handleBackButton());

      // BoardGameApp runBoard = new BoardGameApp(playerFileName, boardFileName, boardgame);
      // runBoard.startGame(playerFileName, boardFileName); 

      return new Scene(root, 1280, 720);
    }

    public void updatePlayerList(List<Player> players) {
      activePlayerListView.getItems().setAll(players);
    }

    public void updatePlayerPositions(List<Player> players) {
      for (StackPane pane : tilePanes.values()) {
        if (pane.getChildren().size() > 1) {
          pane.getChildren().remove(1, pane.getChildren().size());
        }
      }

      /* Add players to tiles */
      for (Player player : players) {
        Tile tile = player.getCurrentTile();
        if (tile != null) {
          StackPane pane = tilePanes.get(tile.getTileID());
          if (pane != null) {
            Label playerLabel = new Label(player.getName());
            playerLabel.setStyle("-fx-font-size:10px; -fx-text-fill:blue;");
            pane.getChildren().add(playerLabel);
          }
        }
      }
    }
}
