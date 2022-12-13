import java.util.Optional;

public class MoveQueen {
    private final QueenCollection returnQueenCollection;
    private final Player player;

    public MoveQueen(Player player, QueenCollection returnQueenCollection) {
        this.returnQueenCollection = returnQueenCollection;
        this.player = player;
    }
    public boolean play(Position targetQueen) {
        if (targetQueen instanceof SleepingQueenPosition) {
            Optional<Queen> removedQueen = player.getGame().getSleepingQueens().removeQueen(targetQueen);
            if (removedQueen.isEmpty()) return false;
            returnQueenCollection.addQueen(removedQueen.get());
            return true;
        }
        else if (targetQueen instanceof AwokenQueenPosition) {
            int targetPlayerIndex = ((AwokenQueenPosition) targetQueen).getPlayerIndex();
            Optional<Queen> removedQueen = player.getGame().getPlayers().get(targetPlayerIndex).getAwokenQueens().removeQueen(targetQueen);
            if (removedQueen.isEmpty()) return false;
            returnQueenCollection.addQueen(removedQueen.get());
            return true;
        }
        else return false;
    }
}
