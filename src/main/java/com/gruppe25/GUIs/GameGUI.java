package com.gruppe25.GUIs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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
        SnakeLadderGUI snakeLadderGUI = new SnakeLadderGUI();
        stage.setScene(snakeLadderGUI.createScene());
        break;
      /* case "trivialPursuit":
        stage.setScene(TrivialPursuitGame.createScene());
        break; */
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
