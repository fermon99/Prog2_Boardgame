package com.gruppe25;

public class Main {
    public static void main(String[] args) {
        BoardGame boardgame = new BoardGame();

        boardgame.addPlayer(new Player("Filip"));
        boardgame.addPlayer(new Player("Bot 1"));
        boardgame.addPlayer(new Player("Bot 2"));
        boardgame.addPlayer(new Player("Bot 3"));

        boardgame.createBoard(100);
        boardgame.createDice(2);

        boardgame.play();
    }
}