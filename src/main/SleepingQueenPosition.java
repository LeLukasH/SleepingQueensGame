import java.util.Objects;

public class SleepingQueenPosition implements Position{
    private int cardIndex;

    public SleepingQueenPosition(int cardIndex) {
        this.cardIndex = cardIndex;
    }
    public int getCardIndex() {return cardIndex;}
    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SleepingQueenPosition that = (SleepingQueenPosition) o;
        return cardIndex == that.cardIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardIndex);
    }
}
