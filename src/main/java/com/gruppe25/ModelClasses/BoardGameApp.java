package com.gruppe25.ModelClasses;

import java.util.List;

import static com.gruppe25.ModelClasses.PlayerReader.readPlayersFromCSV;

public class BoardGameApp {
  String playerFileName;
  String boardFileName;
  BoardGame boardgame;

  List<Player> players;

  public BoardGameApp(String playerFileName, String boardFileName, BoardGame boardgame) {
    this.playerFileName = playerFileName;
    this.boardFileName = boardFileName;
    this.boardgame = boardgame;
  }

  public void init() {
    /* Adding all players from file */
    players = readPlayersFromCSV(playerFileName, boardgame);
    for (Player player : players) {
      System.out.println(player);
      boardgame.addPlayer(player);
    }
  }

  public List<Player> getPlayers() {
    return players;
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
