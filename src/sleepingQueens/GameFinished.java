package sleepingQueens;

import java.util.Optional;

public class GameFinished implements GameFinishedStrategy{
    @Override
    public Optional<Integer> isFinished() {
        return Optional.empty();
    }
}
