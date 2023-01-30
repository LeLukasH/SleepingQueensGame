import java.util.Map;
import java.util.Optional;

public class MoveQueen {
    private QueenCollection returnQueenCollection;
    private final Map<Integer, Player> players;
    private final SleepingQueens sleepingQueens;

    public MoveQueen(Map<Integer, Player> players, SleepingQueens sleepingQueens) {
        this.players = players;
        this.sleepingQueens = sleepingQueens;
        setReturnQueenCollectionToSleepingQueens();
    }
    public boolean play(Position targetQueen) {
        if (targetQueen instanceof SleepingQueenPosition) {
            Optional<Queen> removedQueen = sleepingQueens.removeQueen(targetQueen);
            if (removedQueen.isEmpty()) return false;
            returnQueenCollection.addQueen(removedQueen.get());
            return true;
        }
        else if (targetQueen instanceof AwokenQueenPosition) {
            int targetPlayerIndex = ((AwokenQueenPosition) targetQueen).getPlayerIndex();
            Optional<Queen> removedQueen = players.get(targetPlayerIndex).getAwokenQueens().removeQueen(targetQueen);
            if (removedQueen.isEmpty()) return false;
            returnQueenCollection.addQueen(removedQueen.get());
            return true;
        }
        else return false;
    }

    public void setReturnQueenCollection(QueenCollection queenCollection) {
        this.returnQueenCollection = queenCollection;
    }
    public void setReturnQueenCollectionToSleepingQueens() {
        this.returnQueenCollection = sleepingQueens;
    }
}
