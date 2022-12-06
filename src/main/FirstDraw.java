import java.util.*;

public class FirstDraw implements ShuffleStrategy{
    private List<Card> drawingPile;
    private List<Card> thrashPile;
    @Override
    public List<Card> shuffle(List<Card> drawingPile, List<Card> thrashPile, List<Card> cardsToBeDiscarded) {
        List<Card> drawnCards = new ArrayList<>();
        int needToBeDrawn = cardsToBeDiscarded.size() - drawingPile.size();
        thrashPile.addAll(cardsToBeDiscarded);
        for (int i = 0; i < drawingPile.size(); i++) {
            drawnCards.add(drawingPile.remove(drawingPile.size() - 1));
        }
        this.drawingPile = new ArrayList<>(thrashPile);
        Collections.shuffle(this.drawingPile);
        for (int i = 0; i < needToBeDrawn; i++) {
            drawnCards.add(this.drawingPile.remove(this.drawingPile.size() - 1));
        }
        this.thrashPile = new ArrayList<>();
        return drawnCards;
    }
    public List<Card> getDrawingPile() {
        return drawingPile;
    }
    public List<Card> getThrashPile() {
        return thrashPile;
    }
}
