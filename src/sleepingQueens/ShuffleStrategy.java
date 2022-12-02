package sleepingQueens;

import java.util.List;

public interface ShuffleStrategy {
    // List<List<Card>> = newDrawingPile, newThrashPile, drawnCards
    List<List<Card>> shuffle(List<Card> drawingPile, List<Card> thrashPile, List<Card> cardsToBeDiscarded);
}
