package com.gruppe25.Controllers;

import com.gruppe25.GUIs.GameGUI;
import com.gruppe25.GUIs.SnakeLadderGUI;
import com.gruppe25.GUIs.TrivialPursuitGUI;
import com.gruppe25.ModelClasses.BoardGame;
import com.gruppe25.ModelClasses.QuestionHandler;
import com.gruppe25.ModelClasses.TileActionAdder;

import javafx.stage.Stage;

public class MainMenuController {

  private GameGUI gameGUI;
  private static final String snakeLadderFileName = "src/main/resources/boards/SnakeLadderBoardgame.json";

  public MainMenuController(GameGUI gameGUI) {
    this.gameGUI = gameGUI;
  }

  public void startGame(String gameType, Stage stage) {
    switch(gameType) {
      case "snakeLadder":
        BoardGame snakeLadderBoardgame = new BoardGame();
        snakeLadderBoardgame.createBoard(snakeLadderFileName, );
        SnakeLadderController snakeLadderController = new SnakeLadderController(snakeLadderBoardgame, null);
        SnakeLadderGUI snakeLadderGUI = new SnakeLadderGUI(snakeLadderController);
        snakeLadderController.setGUI(snakeLadderGUI);
        snakeLadderGUI.show(stage);
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

  public void setGUI(GameGUI gui) {
    this.gameGUI = gui;
  }
}
