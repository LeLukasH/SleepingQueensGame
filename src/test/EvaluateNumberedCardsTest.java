import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EvaluateNumberedCardsTest {
    private EvaluateNumberedCards evaluateNumberedCards;

    void setUp() {
        evaluateNumberedCards = new EvaluateNumberedCards();
    }

    @Test
    public void testEvaluation1() {
        setUp();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.NUMBER, 3));
        cards.add(new Card(CardType.NUMBER, 3));
        assertTrue(evaluateNumberedCards.play(cards));
    }

    @Test
    public void testEvaluation2() {
        setUp();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.NUMBER, 1));
        cards.add(new Card(CardType.NUMBER, 2));
        cards.add(new Card(CardType.NUMBER, 3));
        assertTrue(evaluateNumberedCards.play(cards));
    }

    @Test
    public void testEvaluation3() {
        setUp();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.NUMBER, 1));
        cards.add(new Card(CardType.NUMBER, 5));
        cards.add(new Card(CardType.NUMBER, 3));
        assertFalse(evaluateNumberedCards.play(cards));
    }
    @Test
    public void testEvaluation4() {
        setUp();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.NUMBER, 10));
        assertTrue(evaluateNumberedCards.play(cards));
    }

}