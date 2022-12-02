package sleepingQueens;

public interface Position {

    int getCardIndex();
    void setCardIndex(int cardIndex);
    /*
    private final Optional<HandPosition> handPosition;
    private final Optional<SleepingQueenPosition> sleepingQueenPosition;
    private final Optional<AwokenQueenPosition> awokenQueenPosition;

    public Position(HandPosition h) {
        handPosition = Optional.ofNullable(h);
        sleepingQueenPosition = Optional.empty();
        awokenQueenPosition = Optional.empty();
    }
    public Position(SleepingQueenPosition s) {
        handPosition = Optional.empty();
        sleepingQueenPosition = Optional.ofNullable(s);
        awokenQueenPosition = Optional.empty();
    }
    public Position(AwokenQueenPosition a) {
        handPosition = Optional.empty();
        sleepingQueenPosition = Optional.empty();
        awokenQueenPosition = Optional.ofNullable(a);
    }

    public Optional<HandPosition> getHandPosition() {return handPosition;}
    public Optional<SleepingQueenPosition> getSleepingQueenPosition() {return sleepingQueenPosition;}
    public Optional<AwokenQueenPosition> getAwokenQueenPosition() {return awokenQueenPosition;}
     */
}
