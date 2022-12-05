package sleepingQueens;

import java.util.*;

public class SleepingQueens extends QueenCollection {
    private final Map<Position, Queen> queens;

    public SleepingQueens () {
        queens = new HashMap<>();
        ArrayList<Integer> cardIndexes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {cardIndexes.add(i);}
        Collections.shuffle(cardIndexes, new Random());
        int[] points = {5, 5, 5, 5, 10, 10, 10, 10, 15, 15, 15, 20};
        for (int i = 0; i < 12; i++) {
            addQueen(new Queen(points[cardIndexes.get(i)]));
        }
    }
    @Override
    public void addQueen(Queen queen) {
        queens.put(new SleepingQueenPosition(queens.size()), queen);
    }
    @Override
    public Optional<Queen> removeQueen(Position position) {
        Optional<Queen> removedQueen = Optional.ofNullable(queens.remove(position));
        int removedCardIndex = position.getCardIndex();
        for (Map.Entry<Position, Queen> entry : queens.entrySet()) {
            if (entry.getKey().getCardIndex() > removedCardIndex) {
                entry.getKey().setCardIndex(entry.getKey().getCardIndex() - 1);
            }
        }
        return removedQueen;
    }
    @Override
    public Map<Position, Queen> getQueens() {
        return queens;
    }
}
