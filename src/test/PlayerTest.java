import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PlayerTest {
    private Player player;

    void setUp() {
        Game game = new Game(1);
        player = new Player(game, 0);
    }

    @Test
    public void testPlayCards() {
        setUp();
        List<Position> positions = new ArrayList<>();
        positions.add(new HandPosition(1, player.getPlayerIndex()));
        assertTrue(player.play(positions));
    }
}