package com.gruppe25;

import com.gruppe25.Controllers.MainMenuController;
import com.gruppe25.GUIs.GameGUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    MainMenuController mainMenuController = new MainMenuController(null);
    GameGUI gameGUI = new GameGUI(mainMenuController);
    mainMenuController.setGUI(gameGUI);
    gameGUI.start(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }
}