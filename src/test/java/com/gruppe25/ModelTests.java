package com.gruppe25;

import com.gruppe25.ModelClasses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ModelTests {

    private BoardGame boardGame;
    private Player player;
    private Tile tile1, tile2, tile3;

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

    //Dice tests
    @Test
    void testDice() {
        Dice dice = new Dice(2);
        int result = dice.roll();
        int roll1 = dice.getDie(1);
        int roll2 = dice.getDie(2);
        assertEquals(result, roll1 + roll2);
    }

    //Player tests
    @Test
    void testPlaceOnCorrectTile() {
        player.placeOnTile(tile1);
        assertEquals(tile1, player.getCurrentTile(), "Player should be placed on tile1");
    }

    @Test
    void testMovePlayerCorrectNumberOfSteps() {
        player.placeOnTile(tile1);
        player.move(2);
        assertEquals(tile3, player.getCurrentTile(), "Player should end up on tile3 after moving 2 steps");
    }

    @Test
    void testStopsAtLastTile() {
        player.placeOnTile(tile2);
        player.move(2);
        assertEquals(tile3, player.getCurrentTile(), "Player should stop at tile3 (no next tile)");
    }

    @Test
    void testGetCorrectName() {
        assertEquals("TestPlayer", player.getName(), "getName should return correct name");
    }

    @Test
    void testGetBoardGameInstance() {
        assertEquals(boardGame, player.getBoardGame(), "getBoardGame should return the boardGame instance passed to constructor");
    }

    @Test
    void testIDSetAndGet() {
        player.setPlayerID(7);
        assertEquals(7, player.getPlayerID(), "getPlayerID should return the value set with setPlayerID");
    }

    @Test
    void testGetPlayerName() {
        assertEquals("TestPlayer", player.toString(), "toString should return the player's name");
    }

    @Test
    void testMoveAndTriggerTileAction() {
        final boolean[] actionTriggered = {false};

        TileAction testAction = player -> actionTriggered[0] = true;

        tile2.setLandAction(testAction);
        player.placeOnTile(tile1);

        player.move(1);
        tile2.landPlayer(player);

        assertTrue(actionTriggered[0], "TileAction should have been triggered when player landed on tile2");
    }

  
    //Tile tests
    @Test
    void testGetNextTile() {
        assertEquals(tile2, tile1.getNextTile(), "tile1 should link to tile2 as its next tile");
    }

    @Test
    void testPerformActionCorrectly() {
        final boolean[] called = {false};

        tile1.setLandAction(p -> called[0] = true);
        tile1.landPlayer(player);

        assertTrue(called[0], "landPlayer should trigger the TileAction if set");
    }

    @Test
    void testDoNothingIfNoAction() {
        assertDoesNotThrow(() -> tile2.landPlayer(player), "landPlayer should not throw if no action is set");
    }

    @Test
    void testGetCorrectTileID() {
        assertEquals(1, tile1.getTileID(), "Tile should return correct ID");
    }


    //BoardGame tests

    //Add some in

    //Question action tests
    @Test
    void testHandlerWithCorrectCategory() {
        final String[] calledCategory = {null};
        QuestionHandler mockHandler = (player, category) -> calledCategory[0] = category;
    
        QuestionAction action = new QuestionAction("History", "Answer a history question", mockHandler);
    
        action.perform(player);
    
        assertEquals("History", calledCategory[0], "QuestionHandler should be called with correct category");
    }

}