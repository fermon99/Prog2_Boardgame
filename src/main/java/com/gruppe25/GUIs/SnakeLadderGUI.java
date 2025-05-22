package com.gruppe25.GUIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gruppe25.Controllers.SnakeLadderController;
import com.gruppe25.ModelClasses.Board;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* View class for Snakes and Ladders game */

public class SnakeLadderGUI {

  private SnakeLadderController controller;
  private ListView<Player> activePlayerListView;

  private Map<Integer, StackPane> tilePanes = new HashMap<>();
  private ArrayList<String> playerColors = new ArrayList<>();

  public SnakeLadderGUI(SnakeLadderController controller) {
    this.controller = controller;
  }

  /* Sets stage and shows the Snakes and Ladders GUI */
  public void show(Stage primaryStage) {
    /* Sidebar */
    VBox sideBar = new VBox();
    sideBar.setPrefWidth(200);
    sideBar.setStyle("-fx-background-color:rgb(148, 148, 148); -fx-padding: 10;");

    Button newGameButton = new Button("New game");
    Button rollDiceButton = new Button("Roll dice");
    Button backButton = new Button("Back to selection");

    Label activePlayersLabel = new Label("Active players");
    activePlayerListView = new ListView<>();
    activePlayerListView.setPrefHeight(4*32);

    sideBar.getChildren().addAll(new Label("Controls"),
                                 newGameButton, 
                                 rollDiceButton,
                                 activePlayersLabel,
                                 activePlayerListView,
                                 backButton);

    /* Board grid */
    GridPane boardGrid = createBoardGrid();
    ScrollPane scrollPane = new ScrollPane(boardGrid);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    /* layout */
    BorderPane root = new BorderPane();
    root.setLeft(sideBar);
    root.setCenter(scrollPane);

    Scene scene = new Scene(root, 1280, 720);
    primaryStage.setScene(scene);
    primaryStage.show();

    newGameButton.setOnAction(e -> controller.handleNewGame());
    rollDiceButton.setOnAction(e -> controller.handleRollDice());
    backButton.setOnAction(e -> controller.handleBackButton(primaryStage));
  }

  /* Method for creating the visual representation of the board */
  private GridPane createBoardGrid() {
    GridPane boardGrid = new GridPane();
    boardGrid.setHgap(5);
    boardGrid.setVgap(5);
    boardGrid.setStyle("-fx-background-color: rgb(235, 235, 235);");

    int columns = 9;
    int tileSize = 70;
    Board board = controller.getBoardgame().getBoard();
    int boardSize = board.getBoardSize();
    int maxRow = (boardSize - 1) / columns;

    for (Tile tile : board.getTiles().values()) {
      int tileID = tile.getTileID();

      int row = (tileID - 1 )/ columns;
      int column = (tileID - 1) % columns;

      /* Zig-zag pattern logic */
      if (row % 2 == 1) {
        column = columns - 1 - column;
      }

      StackPane tilePane = new StackPane();
      tilePane.setPrefSize(tileSize, tileSize);
      tilePane.setStyle("-fx-border-color: black; -fx-background-color: white;");

      tilePanes.put(tileID, tilePane);

      /* Start tile */
      if (tileID == 0) {
        Label startLabel = new Label("Start");
        tilePane.getChildren().add(startLabel);
        boardGrid.add(tilePane, 0, maxRow + 1);
      } else { 
        Label label = new Label(String.valueOf(tileID));
        tilePane.getChildren().add(label);

        /* Coloring the action tiles based on action */
        if (tile.getLandAction() != null) {
          String type = tile.getLandAction().getClass().getSimpleName();
          if (type.equals("LadderAction")) {
            tilePane.setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
          } else if (type.equals("SnakeAction")) {
            tilePane.setStyle("-fx-background-color: lightcoral; -fx-border-color: black;");
          }
        }
        boardGrid.add(tilePane, column, maxRow - row);
      }
    }
    return boardGrid;
  }

  /* Updates players ListView in sidebar */
  public void updatePlayerList(List<Player> players) {
    activePlayerListView.getItems().setAll(players);
  }

  /* Updates player positions visually on the board */
  public void updatePlayerPositions(List<Player> players) {
    /* Removes player from previous position */
    for (StackPane pane : tilePanes.values()) {
      if (pane.getChildren().size() > 1) {
        pane.getChildren().remove(1, pane.getChildren().size());
      }
    }

    /* Add players to tiles */
    /* Player colors */
    playerColors.add("red");
    playerColors.add("blue");
    playerColors.add("yellow");
    playerColors.add("green");

    /* Adds color to player markers and places them in each corner of tile */
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
