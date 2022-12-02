package sleepingQueens;

import java.util.*;

public class Hand {
    public int playerIdx;
    private final Player player;
    private List<Card> pickedCards;
    private List<Card> cards;

    public Hand(Player player) {
        this.player = player;
        playerIdx = player.playerIndex;
        pickedCards = new ArrayList<>();
        cards = new ArrayList<>();
        cards.addAll(player.game.drawingAndThrashPile.draw5Cards());
    }

    public Optional<List<Card>> pickCards(List<HandPosition> positions) {

    }
    public Map<HandPosition, Card> removePickedCardsAndRedraw() {

    }
    public void returnPickedCards() {

    }
    public HandPosition hasCardOfType(CardType type) {

    }
    public List<Card> getCards() {
        return cards;
    }
}
