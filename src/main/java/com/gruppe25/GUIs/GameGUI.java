package com.gruppe25.GUIs;

import com.gruppe25.Controllers.MainMenuController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameGUI extends Application {

  private MainMenuController mainMenuController;
  private Stage mainStage;

  public GameGUI() {}

  public void setMainMenuController(MainMenuController mainMenuController) {
    this.mainMenuController = mainMenuController;
  }
  
  @Override
  public void start(Stage primaryStage) {
    this.mainStage = primaryStage;
    showMainMenu();
  }

  public void showMainMenu() {
    Button snakeLadderButton = new Button("Snakes and Ladders");
    Button trivialPursuitButton = new Button("Trivial Pursuit");

    snakeLadderButton.setOnAction(e -> mainMenuController.startGame("snakeLadder", mainStage));
    trivialPursuitButton.setOnAction(e -> mainMenuController.startGame("trivialPursuit", mainStage));

    HBox hbox = new HBox(20, snakeLadderButton, trivialPursuitButton);
    hbox.setStyle("-fx-alignment:center");

    Scene selectionScene = new Scene(hbox, 1280, 720);
    mainStage.setTitle("Choose a boardgame");
    mainStage.setScene(selectionScene);
    mainStage.show();
  }

  public void show() {
    launch();
  }
}
