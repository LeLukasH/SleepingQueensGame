import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class HandTest {
    private int playerIndex;
    private Hand hand;
    private List<HandPosition> positions;

    @Before
    public void setUp() {
        DrawingAndThrashPile drawingAndThrashPile = new DrawingAndThrashPile(false);
        playerIndex = 0;
        hand = new Hand(drawingAndThrashPile, playerIndex);
        positions = new ArrayList<>();
        positions.add(new HandPosition(0, playerIndex));
        positions.add(new HandPosition(1, playerIndex));
    }

    @Test
    public void pickCardsTest() {
        Optional<List<Card>> result = hand.pickCards(positions);
        assertTrue(result.isPresent());
        List<Card> pickedCards = result.get();
        assertEquals(pickedCards.size(), 2);
    }

    @Test
    public void removePickedCardsAndRedrawTest() {
        hand.pickCards(positions);
        Map<HandPosition, Card> result = hand.removePickedCardsAndRedraw();
        assertEquals(result.size(), 2);
        assertEquals(hand.getCards().size(), 5);
    }

    @Test
    public void returnPickedCardsTest() {
        hand.pickCards(positions);
        hand.returnPickedCards();
        assertTrue(hand.getPickedCards().isEmpty());
    }

    @Test
    public void hasCardOfTypeTest() {
        HandPosition result = hand.hasCardOfType(CardType.NUMBER);
        assertNotNull(result);
        assertEquals(result.getCardIndex(), 0);
        assertEquals(result.getPlayerIndex(), playerIndex);
    }
}