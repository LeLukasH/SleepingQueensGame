package sleepingQueens;

import java.util.*;

public class FirstDraw implements ShuffleStrategy{
    @Override
    public List<List<Card>> shuffle(List<Card> drawingPile, List<Card> thrashPile, List<Card> cardsToBeDiscarded) {
        List<List<Card>> returningList = new ArrayList<>();
        List<Card> returnCards = new ArrayList<>();
        int needToBeDrawn = cardsToBeDiscarded.size() - drawingPile.size();
        thrashPile.addAll(cardsToBeDiscarded);
        for (int i = 0; i < drawingPile.size(); i++) {
            returnCards.add(drawingPile.remove(drawingPile.size() - 1));
        }
        List<Card> newDrawingPile = new ArrayList<>(thrashPile);
        Collections.shuffle(newDrawingPile);
        for (int i = 0; i < needToBeDrawn; i++) {
            returnCards.add(newDrawingPile.remove(newDrawingPile.size() - 1));
        }
        List<Card> newThrashPile = new ArrayList<>();
        returningList.add(newDrawingPile);
        returningList.add(newThrashPile);
        returningList.add(returnCards);
        return returningList;
    }
}
