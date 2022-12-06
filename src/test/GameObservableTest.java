import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameObservableTest {

    @Test
    public void gameTest() {
        GameObservable gameObservable = new GameObservable();
        for (int i = 0; i < 2; i++) {
            gameObservable.addPlayer(i, new GameObserver() {
                @Override
                public void notify(String message) {

                }
            });
        }
        assertEquals(2, gameObservable.getNumberOfPlayers());
    }
}