package com.gruppe25.Controllers;

import com.gruppe25.GUIs.WinnerGUI;

public class WinnerController {

private WinnerGUI gui;
private SnakeLadderController snakeLadderController;

  public WinnerController() {
    this.gui = gui;
  }

  public void handleNewSnakeLadderGame() {
    snakeLadderController.handleNewGame();
  }
}
