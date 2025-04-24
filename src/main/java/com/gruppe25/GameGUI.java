package com.gruppe25;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameGUI extends Application {
  
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Choose a boardgame");

    Button snakeLadderButton = new Button("Snakes and Ladders");
    Button trivialPursuitButton = new Button("Trivial Pursuit");

    snakeLadderButton.setOnAction(e -> startGame("snakeLadder", primaryStage));

    HBox hbox = new HBox(20, snakeLadderButton, trivialPursuitButton);
    hbox.setStyle("-fx-alignment:center");

    Scene selectionScene = new Scene(hbox, 1280, 720);
    primaryStage.setScene(selectionScene);
    primaryStage.show();
  }

  private void startGame(String gameType, Stage stage) {
    switch(gameType) {
      case "snakeLadder":
        stage.setScene(SnakeLadderGame.createScene());
        break;
      /* case "trivialPursuit":
        stage.setScene(TrivialPursuitGame.createScene());
        break; */
    }
  }

  public class SnakeLadderGame {
    public static Scene createScene() {
      String playerFileName = "src/main/resources/players/SnakeLadderPlayers.csv";
      String boardFileName = "src/main/resources/boards/SnakeLadderBoardgame.json";

      BoardGame boardgame = new BoardGame();

      Board board = BoardReader.loadBoard(boardFileName, boardgame);
      List<Player> players = PlayerReader.readPlayersFromCSV(playerFileName, boardgame);


      /* Sidebar */
      VBox sideBar = new VBox();
      sideBar.setPrefWidth(200);
      sideBar.setStyle("-fx-background-color:rgb(148, 148, 148); -fx-padding: 10;");
      sideBar.getChildren().addAll(new Label("Controls"),
                                   new Button("New game"), 
                                   new Button("Roll dice"),
                                   new Label("Players"),
                                   new Button("Back to selection"));

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
      for (Player player : players) {
        
      }

      /* Dice */

      //BoardGameApp runBoard = new BoardGameApp();

      //runBoard.startGame(playerFileName, boardFileName); 

      return new Scene(root, 1280, 720);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
