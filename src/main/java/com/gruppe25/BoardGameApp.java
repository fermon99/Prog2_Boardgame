package com.gruppe25;

import java.util.List;

import static com.gruppe25.PlayerReader.readPlayersFromCSV;

public class BoardGameApp {
  public BoardGameApp() {

  }

  public void startGame(String playerFileName, String boardFileName){
    BoardGame boardgame = new BoardGame();

    /* Adding all players from file */
    List<Player> players = readPlayersFromCSV(playerFileName, boardgame);
    for (Player player : players) {
      System.out.println(player);
      boardgame.addPlayer(player);
    }

    boardgame.createBoard(boardFileName);
    boardgame.createDice(boardFileName);

    boardgame.play();
  }
}
