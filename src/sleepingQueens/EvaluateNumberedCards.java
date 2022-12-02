package sleepingQueens;

import java.util.*;

public class EvaluateNumberedCards {
    private Player player;

    public EvaluateNumberedCards(Player player) {
        this.player = player;
    }
    public boolean play(List<Card> cards) {

        if (check(cards)) {
            List<HandPosition> positions = new ArrayList<>();
            for (Map.Entry<Integer, Optional<Card>> entry : player.getPlayerState().cards.entrySet() ) {
                if (entry.getValue().isPresent()) {
                    if (cards.contains(entry.getValue().get())) {
                        positions.add(new HandPosition(entry.getKey(), player.playerIndex));
                    }
                }
            }
            if (positions.size() != cards.size()) {
                System.out.println("You does not have those cards...");
                return false;
            }
            player.hand.pickCards(positions);
            player.hand.removePickedCardsAndRedraw();
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean check(List<Card> cards) {
        int size = cards.size();
        if (size == 1) return true;
        else if (size >= 2 && size <= 5) {
            if (!equationIsSatisfying(cards)) {
                System.out.println("Error in discard cards (WRONG EQUATION)...");
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    private static boolean equationIsSatisfying(List<Card> cards) {
        Card biggest = cards.get(0);
        for (Card card : cards) {
            if (card.value > biggest.value) biggest = card;
        }
        int left_side = 0;
        int right_side = biggest.value;
        for (Card card : cards) {
            if (!card.equals(biggest)) {
                left_side += card.value;
            }
        }
        return left_side == right_side;
    }
}
