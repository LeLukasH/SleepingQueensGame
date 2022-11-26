package sleepingQueens;

import java.util.Optional;

public class Position {
    private Optional<HandPosition> handPosition;
    private Optional<SleepingQueenPosition> sleepingQueenPosition;
    private Optional<AwokenQueenPosition> awokenQueenPosition;

    public Position(HandPosition h) {handPosition = Optional.ofNullable(h);}
    public Position(SleepingQueenPosition s) {sleepingQueenPosition = Optional.ofNullable(s);}
    public Position(AwokenQueenPosition a) {awokenQueenPosition = Optional.ofNullable(a);}

    public Optional<HandPosition> getHandPosition() {return handPosition;}
    public Optional<SleepingQueenPosition> getSleepingQueenPosition() {return sleepingQueenPosition;}
    public Optional<AwokenQueenPosition> getAwokenQueenPosition() {return awokenQueenPosition;}
}
