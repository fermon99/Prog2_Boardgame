package com.gruppe25.Controllers;

import com.gruppe25.GUIs.WinnerGUI;

/* Controller class for winning screen pop-up.
 * Uses SnakeLadderController to handle new game, but works for both games.
 */

public class WinnerController {

private WinnerGUI gui;
private SnakeLadderController snakeLadderController;

  public WinnerController() {
    this.gui = gui;
  }

  /* Handling new game after winner (uses SnakeLadderController but works for winner pop-up-screen in Trivial Pursuit also) */
  public void handleNewSnakeLadderGame() {
    snakeLadderController.handleNewGame();
  }
}
