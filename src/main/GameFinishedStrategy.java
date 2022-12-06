package main;

import java.util.Optional;

public interface GameFinishedStrategy {
    public Optional<Integer> isFinished();
}
