package com.gruppe25.GUIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gruppe25.Controllers.TrivialPursuitController;
import com.gruppe25.ModelClasses.Board;
import com.gruppe25.ModelClasses.BoardGame;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.Tile;

import javafx.geometry.Pos;
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
import javafx.scene.paint.Color;

public class TrivialPursuitGUI {
  
  private TrivialPursuitController controller;
  private ListView<Player> activePlayerListView;

  private Map<Integer, StackPane> tilePanes = new HashMap<>();
  private List<Player> players;
  private BoardGame boardgame;
  private int currentPlayerIndex;
  private ArrayList<String> playerColors = new ArrayList<>();


  public TrivialPursuitGUI() {
    controller = new TrivialPursuitController(this);
  }

  public Scene createScene() {
    /* File paths */
    String playerFileName = controller.getPlayerFile();
    String boardFileName = controller.getBoardFile();

    /* Create board */
    boardgame = controller.getBoardgame();
    boardgame.createBoard(boardFileName);

    /* Create dice */
    boardgame.createDice(boardFileName);

    Board board = boardgame.getBoard();
    players = controller.getPlayers();

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

    int tileSize = 70;

    for (Tile tile : board.getTiles().values()) {
      int tileID = tile.getTileID();

      int row = 0;
      int column = 0;

      /* Square loop pattern */
      if (tileID >= 1 && tileID <= 9) {
        row = 0;
        column = (tileID - 1) + 1;
      } else if (tileID >= 10 && tileID <= 18) {
        row = tileID - 10;
        column = 9 + 1;
      } else if (tileID >= 19 && tileID <= 27) {
        row = 9;
        column = (9 + 1) - (tileID - 19);
      } else if (tileID >= 28 && tileID <= 36) {
        row = 9 - (tileID - 28);
        column = 1;
      }

      StackPane tilePane = new StackPane();
      tilePane.setPrefSize(tileSize, tileSize);
      tilePane.setStyle("-fx-border-color: black; -fx-background-color: white;");

      tilePanes.put(tileID, tilePane);

      if (tileID == 0) {
        Label startLabel = new Label("Start");
        tilePane.getChildren().add(startLabel);
        boardGrid.add(tilePane, 1, 0);
      } else {
        Label label = new Label(String.valueOf(tileID));
        tilePane.getChildren().add(label);

        if (tile.getLandAction() != null) {
          String type = tile.getLandAction().getClass().getSimpleName();
          if (type.equals("QuestionAction")) {
            tilePane.setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
          } else if (type.equals("SnakeAction")) {
            tilePane.setStyle("-fx-background-color: lightcoral; -fx-border-color: black;");
          }
        }
        boardGrid.add(tilePane, column, row);
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
    playerColors.add("red");
    playerColors.add("blue");
    playerColors.add("yellow");
    playerColors.add("green");

    /* Dice */

    /* New game button */
    newGameButton.setOnAction(e -> controller.handleNewGame());

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
    /* Removes player from previous position */
    for (StackPane pane : tilePanes.values()) {
      if (pane.getChildren().size() > 1) {
        pane.getChildren().remove(1, pane.getChildren().size());
      }
    }

    /* Add players to tiles */
    int i = 0;
    for (Player player : players) {
      Tile tile = player.getCurrentTile();
      if (tile != null) {
        StackPane pane = tilePanes.get(tile.getTileID());
        if (pane != null) {
          Color color = Color.web(playerColors.get(i));
          PlayerMarker marker = new PlayerMarker(player, color);
          
          StackPane.setAlignment(marker, switch (i) {
            case 0 -> Pos.TOP_LEFT;
            case 1 -> Pos.TOP_RIGHT;
            case 2 -> Pos.BOTTOM_LEFT;
            case 3 -> Pos.BOTTOM_RIGHT;
            default -> Pos.CENTER;
          });
          pane.getChildren().add(marker);
        }
      }
      i++;
    }
  }
}
