import java.util.*;

public class DrawingAndThrashPile {
    private List<Card> drawingPile;
    private List<Card> thrashPile;
    private List<Card> cardsDiscardedThisTurn;
    private ShuffleStrategy shuffleStrategy;

    public DrawingAndThrashPile(boolean shuffleOnStart) {
        drawingPile = getNewPile();
        if (shuffleOnStart) Collections.shuffle(drawingPile, new Random());
        shuffleStrategy = new FirstShuffle();
        thrashPile = new ArrayList<>();
        cardsDiscardedThisTurn = new ArrayList<>();
    }

    public List<Card> discardAndDraw(List<Card> discard) {
        cardsDiscardedThisTurn = new ArrayList<>(discard);
        if (discard.size() > drawingPile.size()) {
            return shuffle(drawingPile, thrashPile, discard);
        }
        thrashPile.addAll(discard);
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < discard.size(); i++) {
            drawnCards.add(drawingPile.remove(drawingPile.size() - 1));
        }
        return drawnCards;
    }

    public void newTurn() {
        cardsDiscardedThisTurn.clear();
    }
    public List<Card> getCardsDiscardedThisTurn() {
        return cardsDiscardedThisTurn;
    }

    private List<Card> shuffle(List<Card> drawingPile, List<Card> thrashPile, List<Card> cardsToBeDiscarded) {
        List<Card> drawnCards = shuffleStrategy.shuffle(drawingPile, thrashPile, cardsToBeDiscarded);
        this.drawingPile = shuffleStrategy.getDrawingPile();
        this.thrashPile = shuffleStrategy.getThrashPile();
        return drawnCards;
    }

    public List<Card> draw5Cards(){
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cards.add(drawingPile.remove(drawingPile.size() - 1));
        }
        return cards;
    }
    public void setShuffleStrategy(ShuffleStrategy shuffleStrategy){
        this.shuffleStrategy = shuffleStrategy;
    }

    protected List<Card> getNewPile(){
        Map<String, Integer> cardCount = new HashMap<>();
        cardCount.put("Kings", 8);
        cardCount.put("Knights", 4);
        cardCount.put("SleepingPotions", 4);
        cardCount.put("Wands", 3);
        cardCount.put("Dragons", 3);
        cardCount.put("NumberedCards", 4);
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < cardCount.get("Kings"); i++) {cards.add(new Card(CardType.KING, 0));}
        for (int i = 0; i < cardCount.get("Knights"); i++) {cards.add(new Card(CardType.KNIGHT, 0));}
        for (int i = 0; i < cardCount.get("SleepingPotions"); i++) {cards.add(new Card(CardType.SLEEPING_POTION, 0));}
        for (int i = 0; i < cardCount.get("Wands"); i++) {cards.add(new Card(CardType.MAGIC_WAND, 0));}
        for (int i = 0; i < cardCount.get("Dragons"); i++) {cards.add(new Card(CardType.DRAGON, 0));}
        for (int i = 0; i < cardCount.get("NumberedCards"); i++) {
            for (int j = 1; j <= 10; j++) {
                cards.add(new Card(CardType.NUMBER, j));
            }
        }
        return cards;
    }

    public List<Card> getDrawingPile(){
        return this.drawingPile;
    }
    public List<Card> getThrashPile(){
        return this.thrashPile;
    }
    public ShuffleStrategy getShuffleStrategy(){
        return this.shuffleStrategy;
    }
}
