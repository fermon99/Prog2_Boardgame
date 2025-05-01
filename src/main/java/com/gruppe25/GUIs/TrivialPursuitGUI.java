package com.gruppe25.GUIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gruppe25.Controllers.TrivialPursuitController;
import com.gruppe25.ModelClasses.Board;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.QuestionAction;
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

public class TrivialPursuitGUI {
  
  private final TrivialPursuitController controller;
  private ListView<Player> activePlayerListView;

  private Map<Integer, StackPane> tilePanes = new HashMap<>();
  private ArrayList<String> playerColors = new ArrayList<>();

  public TrivialPursuitGUI(TrivialPursuitController controller) {
    this.controller = controller;
  }

  public void show(Stage primaryStage) {
    primaryStage.setTitle("Trivial Pursuit");

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

    /* Board grid */
    GridPane boardGrid = createBoardGrid();
    ScrollPane scrollPane = new ScrollPane(boardGrid);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    /* Layout */
    BorderPane root = new BorderPane();
    root.setLeft(sideBar);
    root.setCenter(scrollPane);

    Scene scene = new Scene(root, 1280, 720);
    primaryStage.setScene(scene);
    primaryStage.show();

    /* Buttons */
    newGameButton.setOnAction(e -> controller.handleNewGame());
    rollDiceButton.setOnAction(e -> controller.handleRollDice());
    backButton.setOnAction(e -> controller.handleBackButton());
  }

  private GridPane createBoardGrid() {
    GridPane boardGrid = new GridPane();
    boardGrid.setHgap(5);
    boardGrid.setVgap(5);
    boardGrid.setStyle("-fx-background-color: rgb(235, 235, 235);");

    int tileSize = 70;
    Board board = controller.getBoardgame().getBoard();

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
        boardGrid.add(tilePane, 0, 0);
      } else {
        Label label = new Label(String.valueOf(tileID));
        tilePane.getChildren().add(label);

        /* Add color to tiles */
        if (tile.getLandAction() != null && tile.getLandAction() instanceof QuestionAction questionAction) {
          String category = questionAction.getCategory();
          if (category.equals("Art and literature")) {
            tilePane.setStyle("-fx-background-color: lightpink; -fx-border-color: black;");
          } else if (category.equals("Science and nature")) {
            tilePane.setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
          } else if (category.equals("Sports and leisure")) {
            tilePane.setStyle("-fx-background-color: orange; -fx-border-color: black;");
          } else if (category.equals("Geography")) {
            tilePane.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
          } else if (category.equals("Entertainment")) {
            tilePane.setStyle("-fx-background-color: lightcoral; -fx-border-color: black;");
          } else if (category.equals("History")) {
            tilePane.setStyle("-fx-background-color: yellow; -fx-border-color: black;");
          } 
        }
        boardGrid.add(tilePane, column, row);
      }
    }
    return boardGrid;
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
    /* Players colors*/
    playerColors.add("red");
    playerColors.add("blue");
    playerColors.add("yellow");
    playerColors.add("green");
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
