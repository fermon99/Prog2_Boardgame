package com.gruppe25;

public class BoardGameApp {
  public BoardGameApp() {

  }

  public void startGame() {
    BoardGame boardgame = new BoardGame();

    boardgame.addPlayer(new Player("Filip"));
    boardgame.addPlayer(new Player("Bot 1"));
    boardgame.addPlayer(new Player("Bot 2"));
    boardgame.addPlayer(new Player("Bot 3"));

    boardgame.createBoard(100);//the basic board should be 90 tiles
    boardgame.createDice(2);

    boardgame.play();
  }
}
