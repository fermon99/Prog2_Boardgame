package com.gruppe25.GUIs;

import com.gruppe25.Controllers.MainMenuController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/* View class for main menu */

public class GameGUI {

  private MainMenuController mainMenuController;
  private Stage mainStage;

  public GameGUI(MainMenuController mainMenuController) {
    this.mainMenuController = mainMenuController;
  }
  
  /* Sets the stage and show main menu */
  public void start(Stage primaryStage) {
    this.mainStage = primaryStage;
    showMainMenu();
  }

  /* Creates the layout of the main menu GUI */
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
}
