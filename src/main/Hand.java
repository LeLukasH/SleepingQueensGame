import java.util.*;

public class Hand {
    private final int playerIdx;
    private final DrawingAndThrashPile drawingAndThrashPile;
    private final List<Card> pickedCards;
    private final List<Card> cards;

    public Hand(DrawingAndThrashPile drawingAndThrashPile, int playerIndex) {
        this.drawingAndThrashPile = drawingAndThrashPile;
        this.playerIdx = playerIndex;
        pickedCards = new ArrayList<>();
        cards = new ArrayList<>();
        cards.addAll(drawingAndThrashPile.draw5Cards());
    }

    public Optional<List<Card>> pickCards(List<HandPosition> positions) {
        if (positions.isEmpty()) return Optional.empty();
        for (HandPosition position : positions) {
            pickedCards.add(cards.get(position.getCardIndex()));
        }
        return Optional.of(pickedCards);
    }
    public Map<HandPosition, Card> removePickedCardsAndRedraw() {
        cards.removeAll(pickedCards);
        Map<HandPosition, Card> returningMap = new HashMap<>();
        List<Card> drawnCards = drawingAndThrashPile.discardAndDraw(pickedCards);
        for (int i = 0; i < drawnCards.size(); i++) {
            returningMap.put(new HandPosition(i + cards.size(), playerIdx), drawnCards.get(i));
        }
        cards.addAll(drawnCards);
        returnPickedCards();
        return returningMap;
    }

    public void returnPickedCards() {
        pickedCards.clear();
    }

    public HandPosition hasCardOfType(CardType type) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).type.equals(type)) return new HandPosition(i, playerIdx);
        }
        return null;
    }
    public List<Card> getCards() {
        return cards;
    }

    protected List<Card> getPickedCards() {
        return pickedCards;
    }
}
