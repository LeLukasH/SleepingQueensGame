package sleepingQueens;

import java.util.*;

public abstract class QueenCollection {
    private List<Queen> queenList;

    public void addQueen(Queen queen) {
        queenList.add(queen);
    }
    public Optional<Queen> removeQueen(SleepingQueenPosition position) {

    }
    public Map<Position, Queen> getQueens() {
        
    }
}
