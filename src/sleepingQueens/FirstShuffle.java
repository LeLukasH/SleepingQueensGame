package sleepingQueens;

import java.util.*;

public class FirstShuffle implements ShuffleStrategy{
    @Override
    public List<List<Card>> shuffle(List<Card> drawingPile, List<Card> thrashPile, List<Card> cardsToBeDiscarded) {
        List<List<Card>> returningList = new ArrayList<>();
        List<Card> returnCards = new ArrayList<>();
        List<Card> newDrawingPile = new ArrayList<>(thrashPile);
        Collections.shuffle(newDrawingPile);
        newDrawingPile.addAll(drawingPile);
        for (int i = 0; i < cardsToBeDiscarded.size(); i++) {
            returnCards.add(newDrawingPile.remove(newDrawingPile.size() - 1));
        }
        List<Card> newThrashPile = new ArrayList<>(cardsToBeDiscarded);
        returningList.add(newDrawingPile);
        returningList.add(newThrashPile);
        returningList.add(returnCards);
        return returningList;
    }
}
