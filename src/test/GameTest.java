import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private Game game;
    private final int numberOfPlayers = 2;

    @Before
    public void setUp() {
        game = new Game(numberOfPlayers);
    }

    @Test
    public void testPlay_InvalidPlayerIdx() {
        Optional<GameState> gameState = game.play(numberOfPlayers + 1, new ArrayList<>());
        assertEquals(gameState, Optional.empty());
    }

    @Test
    public void testPlay_NotPlayersTurn() {
        Optional<GameState> gameState = game.play(1, new ArrayList<>());
        assertEquals(gameState, Optional.empty());
    }

    @Test
    public void testPlay_ValidTurn() {
        List<Position> cards = new ArrayList<>();
        Optional<GameState> gameState = game.play(0, cards);
        assertTrue(gameState.isPresent());
    }

}
