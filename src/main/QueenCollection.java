import java.util.*;

public abstract class QueenCollection {
    abstract void addQueen(Queen queen);
    abstract Optional<Queen> removeQueen(Position position);
    abstract Map<Position, Queen> getQueens();
}
