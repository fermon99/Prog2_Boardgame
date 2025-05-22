package com.gruppe25.Controllers;

import com.gruppe25.GUIs.GameGUI;

import javafx.stage.Stage;

/* Controller class for Main Menu
 * Handles button presses (choosing game)
 */

public class MainMenuController {

  private GameGUI gameGUI;

  public MainMenuController(GameGUI gameGUI) {
    this.gameGUI = gameGUI;
  }

  public void startGame(String gameType, Stage stage) {
    switch(gameType) {
      case "snakeLadder":
        SnakeLadderController snakeLadderController = new SnakeLadderController();
        snakeLadderController.start(stage);
        break;
      case "trivialPursuit":
        TrivialPursuitController trivialPursuitController = new TrivialPursuitController();
        trivialPursuitController.start(stage);
        break;
      default:
        System.out.println("Unknown game...");
    }
  }

  public void setGUI(GameGUI gui) {
    this.gameGUI = gui;
  }
}
