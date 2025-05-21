package com.gruppe25;

import com.gruppe25.ModelClasses.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ModelTests {

    private BoardGame boardGame;
    private Player player;
    private Tile tile1; 
    private Tile tile2; 
    private Tile tile3;

    @BeforeEach
    void setUp() {
        boardGame = new BoardGame();
        player = new Player("TestPlayer", boardGame);
        tile1 = new Tile(1);
        tile2 = new Tile(2);
        tile3 = new Tile(3);
        tile1.setNextTile(tile2);
        tile2.setNextTile(tile3);
    }

    @Test
    void testDice() {
        Dice dice = new Dice(2);
        int result = dice.roll();
        int roll1 = dice.getDie(1);
        int roll2 = dice.getDie(2);
        assertEquals(result, roll1 + roll2);
    }

    @Test
    void testPlaceOnCorrectTile() {
        player.placeOnTile(tile1);
        assertEquals(tile1, player.getCurrentTile(), "Player should land on tile1.");
    }

    @Test
    void testMovePlayerCorrectNumberOfSteps() {
        player.placeOnTile(tile1);
        player.move(2);
        assertEquals(tile3, player.getCurrentTile(), "Player should end up on tile3 after 2 moves.");
    }

    @Test
    void testStopsAtLastTile() {
        player.placeOnTile(tile2);
        player.move(2);
        assertEquals(tile3, player.getCurrentTile(), "Player should stop on tile3.");
    }

    @Test
    void testGetCorrectName() {
        assertEquals("TestPlayer", player.getName(), "getName should return correct name.");
    }

    @Test
    void testGetBoardGameInstance() {
        assertEquals(boardGame, player.getBoardGame(), "getBoardGame should return the boardGame instance.");
    }

    @Test
    void testIDSetAndGet() {
        player.setPlayerID(7);
        assertEquals(7, player.getPlayerID(), "getPlayerID should return the value set with setPlayerID.");
    }

    @Test
    void testToString() {
        assertEquals("TestPlayer", player.toString(), "toString should return the player's name.");
    }

    @Test
    void testGetNextTile() {
        assertEquals(tile2, tile1.getNextTile(), "tile1 should link to tile2 as its next tile.");
    }

    @Test
    void testPerformLandActionCorrectly() {
        final boolean[] called = {false};

        tile1.setLandAction(p -> called[0] = true);
        tile1.landPlayer(player);

        assertTrue(called[0], "landPlayer should trigger the TileAction.");
    }

    @Test
    void testGetCorrectTileID() {
        assertEquals(1, tile1.getTileID(), "Tile should return correct ID");
    }
    
    @Test
    void testCorrectCategory() {
        final String[] calledCategory = {null};
        QuestionHandler testHandler = (player, category) -> calledCategory[0] = category;
        QuestionAction action = new QuestionAction("History", "Answer a history question.", testHandler);
        action.perform(player);
    
        assertEquals("History", calledCategory[0], "QuestionHandler should be called with correct category.");
    }

}