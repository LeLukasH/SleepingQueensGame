import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DrawingAndThrashPileTest {

    private DrawingAndThrashPile drawingAndThrashPile;

    @Before
    public void setUp() {
        drawingAndThrashPile = new DrawingAndThrashPile(false);
    }

    @Test
    public void discardAndDraw_shouldDiscardCardsAndDrawSameNumber() {
        List<Card> cardsToDiscard = new ArrayList<>();
        cardsToDiscard.add(new Card(CardType.KING, 0));
        cardsToDiscard.add(new Card(CardType.KNIGHT, 0));

        List<Card> drawnCards = drawingAndThrashPile.discardAndDraw(cardsToDiscard);

        Assert.assertEquals(2, drawnCards.size());
        Assert.assertEquals(2, drawingAndThrashPile.getCardsDiscardedThisTurn().size());
    }

    @Test
    public void discardAndDraw_shouldShuffleIfDiscardIsGreaterThanDrawingPileSize() {
        List<Card> cardsToDiscard = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cardsToDiscard.add(new Card(CardType.KING, 0));
        }

        List<Card> drawnCards = drawingAndThrashPile.discardAndDraw(cardsToDiscard);

        Assert.assertEquals(30, drawnCards.size());
        Assert.assertTrue(drawingAndThrashPile.getDrawingPile().size() > 0);
        Assert.assertEquals(30, drawingAndThrashPile.getCardsDiscardedThisTurn().size());
    }

    @Test
    public void newTurn_shouldClearCardsDiscardedThisTurn() {
        List<Card> cardsToDiscard = new ArrayList<>();
        cardsToDiscard.add(new Card(CardType.KING, 0));
        cardsToDiscard.add(new Card(CardType.KNIGHT, 0));
        drawingAndThrashPile.discardAndDraw(cardsToDiscard);

        drawingAndThrashPile.newTurn();

        Assert.assertEquals(0, drawingAndThrashPile.getCardsDiscardedThisTurn().size());
    }

    @Test
    public void draw5Cards_shouldReturn5Cards() {
        List<Card> drawnCards = drawingAndThrashPile.draw5Cards();
        Assert.assertEquals(5, drawnCards.size());
        Assert.assertEquals(drawingAndThrashPile.getNewPile().size() - 5, drawingAndThrashPile.getDrawingPile().size());
    }

    @Test
    public void testGetNewPile() {
        List<Card> newPile = drawingAndThrashPile.getNewPile();
        Assert.assertEquals(newPile.size(), 62);
    }
}