package com.gruppe25;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
      /* Sidebar */
      VBox sideBar = new VBox();
      sideBar.setPrefWidth(200);
      sideBar.setStyle("-fx-background-color:rgb(148, 148, 148); -fx-padding: 10;");
      sideBar.getChildren().addAll(new Label("Controls"), new Button("New game"), new Button("Back to selection"));

      /* Main content area */
      Pane mainArea = new Pane();
      mainArea.setStyle("-fx-background-color:rgb(235, 235, 235)");

      /* Layout */
      BorderPane root = new BorderPane();
      root.setLeft(sideBar);
      root.setCenter(mainArea);

      BoardGameApp runBoard = new BoardGameApp();

      String playerFileName = "src/main/resources/players/SnakeLadderPlayers.csv";
      String boardFileName = "src/main/resources/boards/SnakeLadderBoardgame.json";
      runBoard.startGame(playerFileName, boardFileName); 

      return new Scene(root, 1280, 720);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
