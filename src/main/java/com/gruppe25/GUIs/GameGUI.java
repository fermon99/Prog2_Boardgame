package com.gruppe25.GUIs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameGUI extends Application {

  private static Stage mainStage;
  
  @Override
  public void start(Stage primaryStage) {
    mainStage = primaryStage;
    mainMenu();
  }

  public static void mainMenu() {
    Button snakeLadderButton = new Button("Snakes and Ladders");
    Button trivialPursuitButton = new Button("Trivial Pursuit");

    snakeLadderButton.setOnAction(e -> startGame("snakeLadder"));
    trivialPursuitButton.setOnAction(e -> startGame("trivialPursuit"));

    HBox hbox = new HBox(20, snakeLadderButton, trivialPursuitButton);
    hbox.setStyle("-fx-alignment:center");

    Scene selectionScene = new Scene(hbox, 1280, 720);
    mainStage.setTitle("Choose a boardgame");
    mainStage.setScene(selectionScene);
    mainStage.show();
  }

  private static void startGame(String gameType) {
    switch(gameType) {
      case "snakeLadder":
        SnakeLadderGUI snakeLadderGUI = new SnakeLadderGUI();
        mainStage.setScene(snakeLadderGUI.createScene());
        break;
       case "trivialPursuit":
       TrivialPursuitGUI trivialPursuitGUI = new TrivialPursuitGUI();
        mainStage.setScene(trivialPursuitGUI.createScene());
        break;
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
