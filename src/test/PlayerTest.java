import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {
    private Player player;

    void setUp() {
        Game game = mock(Game.class);
        when(game.drawingAndThrashPile).thenReturn(new DrawingAndThrashPile());
        player = new Player(game, 0);
    }

    @Test
    public void testPlayCards() {
        setUp();
        List<Card> playerCards = player.hand.getCards();
        List<Position> positions = new ArrayList<>();
        positions.add(new HandPosition(1, player.playerIndex));
        assertTrue(player.play(positions));
    }
}