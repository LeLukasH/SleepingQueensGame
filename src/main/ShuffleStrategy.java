import java.util.List;

public interface ShuffleStrategy {
    List<Card> shuffle(List<Card> drawingPile, List<Card> thrashPile, List<Card> cardsToBeDiscarded); // returns drawnCards;
    List<Card> getDrawingPile();
    List<Card> getThrashPile();
}
