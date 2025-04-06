package com.gruppe25;

import java.util.List;

import static com.gruppe25.PlayerReader.readPlayersFromCSV;

public class BoardGameApp {
  public BoardGameApp() {

  }

  public void startGame(String playerFileName /*, String boardFileName */){
    BoardGame boardgame = new BoardGame();

    /*boardgame.addPlayer(new Player("Filip"));
    boardgame.addPlayer(new Player("Bot 1"));
    boardgame.addPlayer(new Player("Bot 2"));
    boardgame.addPlayer(new Player("Bot 3"));*/

    /* Adding all players from file */
    List<Player> players = readPlayersFromCSV(playerFileName, boardgame);
    for (Player player : players) {
      System.out.println(player);
      boardgame.addPlayer(player);
    }

    boardgame.createBoard(90);
    boardgame.createDice(2);

    boardgame.play();
  }
}
