package com.gruppe25;

public class Main {
    public static void main(String[] args) {
        BoardGameApp runBoard = new BoardGameApp();

        String playerFileName = "src/main/resources/players/SnakeLadderPlayers.csv";
        String boardFileName = "src/main/resources/boards/SnakeLadderBoardgame.json";
        runBoard.startGame(playerFileName, boardFileName); 
    }
}