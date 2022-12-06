import java.util.*;

public class AwokenQueens extends QueenCollection{
    private final Map<Position, Queen> queens;
    private final Player player;

    public AwokenQueens (Player player) {
        this.player = player;
        queens = new HashMap<>();
    }
    @Override
    public void addQueen(Queen queen) {
        queens.put(new AwokenQueenPosition(queens.size(), player.playerIndex), queen);
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
