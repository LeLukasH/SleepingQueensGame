package sleepingQueens;

import java.util.*;

public class FirstShuffle implements ShuffleStrategy{
    private List<Card> drawingPile;
    private List<Card> thrashPile;
    @Override
    public List<Card> shuffle(List<Card> drawingPile, List<Card> thrashPile, List<Card> cardsToBeDiscarded) {
        List<Card> drawnCards = new ArrayList<>();
        this.drawingPile = new ArrayList<>(thrashPile);
        Collections.shuffle(this.drawingPile);
        this.drawingPile.addAll(drawingPile);
        for (int i = 0; i < cardsToBeDiscarded.size(); i++) {
            drawnCards.add(this.drawingPile.remove(this.drawingPile.size() - 1));
        }
        this.thrashPile = new ArrayList<>(cardsToBeDiscarded);
        return drawnCards;
    }
    @Override
    public List<Card> getDrawingPile() {
        return drawingPile;
    }
    @Override
    public List<Card> getThrashPile() {
        return thrashPile;
    }
}
