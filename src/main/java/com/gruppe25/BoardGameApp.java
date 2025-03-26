package com.gruppe25;

import static com.gruppe25.PlayerReader.readPlayersFromCSV;

import java.util.List;

public class BoardGameApp {
  public BoardGameApp() {

  }

  public void startGame(String playerFileName){
    BoardGame boardgame = new BoardGame();

    /*boardgame.addPlayer(new Player("Filip"));
    boardgame.addPlayer(new Player("Bot 1"));
    boardgame.addPlayer(new Player("Bot 2"));
    boardgame.addPlayer(new Player("Bot 3"));*/

    List<Player> players = readPlayersFromCSV(playerFileName);
    for (Player player : players) {
      System.out.println(player);
      boardgame.addPlayer(player);
    }

    boardgame.createBoard(90);
    boardgame.createDice(2);

    boardgame.play();
  }
}
