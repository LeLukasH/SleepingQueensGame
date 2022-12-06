import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameFinishedTest {
    private GameFinished gameFinished;

    void setUp() {
        gameFinished = new GameFinished(new Game(2));
    }

    @Test
    public void isFinished() {
        setUp();
        assertTrue(gameFinished.isFinished().isEmpty());
    }
}