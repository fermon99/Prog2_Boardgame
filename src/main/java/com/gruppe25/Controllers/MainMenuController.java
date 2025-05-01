package com.gruppe25.Controllers;

import com.gruppe25.GUIs.GameGUI;
import com.gruppe25.GUIs.SnakeLadderGUI;
import com.gruppe25.GUIs.TrivialPursuitGUI;
import com.gruppe25.ModelClasses.BoardGame;

import javafx.stage.Stage;

public class MainMenuController {

  private final GameGUI gameGUI;

  public MainMenuController() {
    this.gameGUI = new GameGUI();
    this.gameGUI.setMainMenuController(this);
  }

  public void showMainMenu() {
    gameGUI.show();
  }

  public void startGame(String gameType, Stage stage) {
    switch(gameType) {
      case "snakeLadder":
        BoardGame snakeLadderBoardgame = new BoardGame();
        SnakeLadderGUI snakeLadderGUI = new SnakeLadderGUI();
        stage.setScene(snakeLadderGUI.createScene());
        break;
      case "trivialPursuit":
        BoardGame trivialPursuitBoardgame = new BoardGame();
        TrivialPursuitController trivialPursuitController = new TrivialPursuitController(trivialPursuitBoardgame, new TrivialPursuitGUI(null), new QuestionController(null));
        TrivialPursuitGUI trivialPursuitGUI = new TrivialPursuitGUI(trivialPursuitController);
        trivialPursuitController.setGUI(trivialPursuitGUI);
        trivialPursuitGUI.show(stage);
        break;
      default:
        System.out.println("Unknown game...");
    }
  }

  public void exitApplication() {
    System.exit(0);
  }
}
