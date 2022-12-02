package sleepingQueens;

import java.util.*;

public class EvaluateNumberedCards {
    public boolean play(List<Card> cards) {
        int size = cards.size();
        if (size == 1) return true;
        else if (size >= 2 && size <= 5) {
            Card biggest = cards.get(0);
            for (Card card : cards) {
                if (card.value > biggest.value) biggest = card;
            }
            int left_side = 0;
            int right_side = biggest.value;
            for (Card card : cards) {
                if (!card.equals(biggest)) left_side += card.value;
            }
            return left_side == right_side;
        }
        else return false;
    }
}
