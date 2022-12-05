package sleepingQueens;

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
            Optional<Queen> removedQueen = player.game.sleepingQueens.removeQueen(targetQueen);
            if (removedQueen.isEmpty()) return false;
            returnQueenCollection.addQueen(removedQueen.get());
            return true;
        }
        else if (targetQueen instanceof AwokenQueenPosition) {
            int targetPlayerIndex = ((AwokenQueenPosition) targetQueen).getPlayerIndex();
            Optional<Queen> removedQueen = player.game.players.get(targetPlayerIndex).awokenQueens.removeQueen(targetQueen);
            if (removedQueen.isEmpty()) return false;
            returnQueenCollection.addQueen(removedQueen.get());
            return true;
        }
        else return false;
    }
}
