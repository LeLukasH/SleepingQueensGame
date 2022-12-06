import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class DrawingAndThrashPileTest {
    private DrawingAndThrashPile drawingAndThrashPile;

    void setUp() {
        drawingAndThrashPile = new DrawingAndThrashPile();
    }

    @Test
    public void testDrawCards() {
        setUp();
        List<Card> cardList = drawingAndThrashPile.draw5Cards();
        assertEquals(5, cardList.size());
        cardList.remove(cardList.get(2));
        cardList.remove(cardList.get(1));
        drawingAndThrashPile.newTurn();
        List<Card> drawnCards = drawingAndThrashPile.discardAndDraw(cardList);
        assertEquals(3, drawnCards.size());

        List<Card> emptyList = new ArrayList<>();
        assertEquals(0, drawingAndThrashPile.discardAndDraw(emptyList).size());
    }

    @Test
    public void testDiscardedCards() {
        setUp();
        List<Card> cardList = drawingAndThrashPile.draw5Cards();
        cardList.remove(cardList.get(3));
        drawingAndThrashPile.newTurn();
        drawingAndThrashPile.discardAndDraw(cardList);
        assertEquals(cardList, drawingAndThrashPile.getCardsDiscardedThisTurn());
    }

}