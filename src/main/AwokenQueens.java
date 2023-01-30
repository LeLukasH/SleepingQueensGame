import java.util.*;

public class AwokenQueens extends QueenCollection{
    private Map<Position, Queen> queens;
    private final int playerIndex;

    public AwokenQueens (int playerIndex) {
        this.playerIndex = playerIndex;
        queens = new HashMap<>();
    }
    @Override
    public void addQueen(Queen queen) {
        queens.put(new AwokenQueenPosition(queens.size(), playerIndex), queen);
    }

    @Override
    public Optional<Queen> removeQueen(Position position) {
        Optional<Queen> removedQueen = Optional.ofNullable(queens.remove(position));
        int removedCardIndex = position.getCardIndex();
        Map<Position, Queen> map = new HashMap<>();
        for (Map.Entry<Position, Queen> entry : queens.entrySet()) {
            if (entry.getKey().getCardIndex() > removedCardIndex) {
                entry.getKey().setCardIndex(entry.getKey().getCardIndex() - 1);
            }
            map.put(entry.getKey(), entry.getValue());
        }
        this.queens = map;
        return removedQueen;
    }

    @Override
    public Map<Position, Queen> getQueens() {
        return queens;
    }
}
